package career.java.concept.concurrency.producerconsumer;

import java.util.*;
import java.util.concurrent.*;

/*
✔ slots = "How many can PRODUCER write?"
✔ items = "How many can CONSUMER read?"
✔ mutex = "Make sure only ONE PERSON touches the buffer at a time."

“Producer-Consumer using semaphores requires 3 semaphores:
• slots to count empty spaces (capacity control),
• items to count filled spaces (empty control),
• mutex to protect the critical section.
Each semaphore solves a different concurrency problem. Removing any one breaks correctness.”

 */
public class ProducerConsumerSemaphore {
    static class BoundedBuffer<T> {
        private final Object[] buf;
        private int putPos = 0, takePos = 0;
        private final Semaphore slots;
        private final Semaphore items;
        private final Semaphore mutex = new Semaphore(1);

        public BoundedBuffer(int capacity) {
            buf = new Object[capacity];
            slots = new Semaphore(capacity); // free slots initially
            items = new Semaphore(0);        // items initially 0
        }

        public void put(T x) throws InterruptedException {
            slots.acquire();        // wait for free slot
            mutex.acquire();        // enter critical section
            try {
                buf[putPos] = x;
                putPos = (putPos + 1) % buf.length;
            } finally {
                mutex.release();
            }
            items.release();        // signal item available
        }

        @SuppressWarnings("unchecked")
        public T take() throws InterruptedException {
            items.acquire();        // wait for available item
            mutex.acquire();
            try {
                T x = (T) buf[takePos];
                buf[takePos] = null;
                takePos = (takePos + 1) % buf.length;
                return x;
            } finally {
                mutex.release();
                slots.release();   // signal free slot
            }
        }
    }

    static class Producer implements Runnable {
        private final BoundedBuffer<Integer> b;
        private final int id, count;
        Producer(BoundedBuffer<Integer> b, int id, int count) { this.b = b; this.id = id; this.count = count; }
        @Override
        public void run() {
            try {
                for (int i=0;i<count;i++) {
                    int v = id*1000 + i;
                    b.put(v);
                    System.out.printf("P%d put %d%n", id, v);
                    Thread.sleep(120);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    static class Consumer implements Runnable {
        private final BoundedBuffer<Integer> b;
        private final int id;
        Consumer(BoundedBuffer<Integer> b, int id) { this.b = b; this.id = id; }
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Integer v = b.take();
                    System.out.printf("C%d took %d%n", id, v);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> buf = new BoundedBuffer<>(5);
        ExecutorService p = Executors.newFixedThreadPool(2);
        ExecutorService c = Executors.newFixedThreadPool(2);

        p.submit(new Producer(buf,1,10));
        p.submit(new Producer(buf,2,10));
        c.submit(new Consumer(buf,1));
        c.submit(new Consumer(buf,2));

//        p.shutdown(); p.awaitTermination(30, TimeUnit.SECONDS);
        Thread.sleep(4000);
//        c.shutdownNow(); c.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Done (Semaphore).");
    }
}
