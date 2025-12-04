package career.java.concept.concurrency.producerconsumer;

import java.util.*;

public class ProducerConsumerWaitNotify {

    static class Buffer {
        private final Deque<Integer> q = new ArrayDeque<>();
        private final int capacity;

        Buffer(int capacity) { this.capacity = capacity; }

        public synchronized void put(int x) throws InterruptedException {
            while (q.size() == capacity) wait();
            q.addLast(x);
            notifyAll(); // wake consumers
        }

        public synchronized int take() throws InterruptedException {
            while (q.isEmpty()) wait();
            int v = q.removeFirst();
            notifyAll(); // wake producers
            return v;
        }

        public synchronized int size() { return q.size(); }
    }

    static class Producer implements Runnable {
        private final Buffer b; private final int id, count;
        Producer(Buffer b, int id, int count) { this.b=b; this.id=id; this.count=count; }
        @Override public void run() {
            try {
                for (int i=0;i<count;i++) {
                    int v = id*1000 + i;
                    b.put(v);
                    System.out.printf("P%d put %d (size=%d)%n", id, v, b.size());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    static class Consumer implements Runnable {
        private final Buffer b; private final int id;
        Consumer(Buffer b, int id) { this.b=b; this.id=id; }
        @Override public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int v = b.take();
                    System.out.printf("C%d took %d (size=%d)%n", id, v, b.size());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Buffer buf = new Buffer(5);

        Thread p1 = new Thread(new Producer(buf,1,10));
        Thread p2 = new Thread(new Producer(buf,2,10));
        Thread c1 = new Thread(new Consumer(buf,1));
        Thread c2 = new Thread(new Consumer(buf,2));

        p1.start(); p2.start(); c1.start(); c2.start();

        p1.join(); p2.join();
        Thread.sleep(4000);
        c1.interrupt(); c2.interrupt();
        c1.join(); c2.join();
        System.out.println("Done (wait/notify).");
    }
}
