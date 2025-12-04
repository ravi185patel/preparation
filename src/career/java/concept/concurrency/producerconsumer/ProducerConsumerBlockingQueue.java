package career.java.concept.concurrency.producerconsumer;

import java.util.concurrent.*;
/*
Use BlockingQueue unless you need to demonstrate primitives or build custom behaviour.

Use Semaphore when you need permit-based control or want to demonstrate token-passing.

Use wait/notify or ReentrantLock+Condition when you must implement custom conditions or want fine-grained control.
 */
public class ProducerConsumerBlockingQueue {

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> q;
        private final int id;
        private final int count;

        Producer(BlockingQueue<Integer> q, int id, int count) { this.q = q; this.id = id; this.count = count; }

        @Override
        public void run() {
            try {
                for (int i = 0; i < count; i++) {
                    int item = id * 1000 + i;
                    q.put(item); // blocks if full // all these function are locking mechanism so all need to be in try and catch block
                    System.out.printf("Producer-%d produced %d (size=%d)%n", id, item, q.size());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> q;
        private final int id;

        Consumer(BlockingQueue<Integer> q, int id) { this.q = q; this.id = id; }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Integer item = q.take(); // blocks if empty
                    System.out.printf("Consumer-%d consumed %d (size=%d)%n", id, item, q.size());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(5);

        ExecutorService prodPool = Executors.newFixedThreadPool(2);
        ExecutorService consPool = Executors.newFixedThreadPool(2);

        prodPool.submit(new Producer(q, 1, 10));
        prodPool.submit(new Producer(q, 2, 10));

        consPool.submit(new Consumer(q, 1));
        consPool.submit(new Consumer(q, 2));

        prodPool.shutdown();
//        prodPool.awaitTermination(30, TimeUnit.SECONDS);

        // wait to let consumers finish
        Thread.sleep(4000);

        consPool.shutdownNow();
//        consPool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Done (BlockingQueue).");
    }
}
