package career.java.concept.concurrency;

import java.util.concurrent.*;

/*
Why does CompletableFuture use an object lock (synchronized/wait/notify) internally?
Because CompletableFuture must support blocking operations (like get(), join(), get(long timeout)), and blocking always requires a monitor lock + wait/notify mechanism.
CompletableFuture is mostly asynchronous, but some of its methods must block, so internally it uses a lock object to coordinate state changes.

lock + wait/notify mechanism perform on object or class level.


There are only THREE ways to block a thread safely:
wait() / notify() → requires an object monitor
Lock + Condition.await()
park() / unpark() (low-level, used by ForkJoinPool)

Blocking a thread in Java requires an object monitor, so CompletableFuture uses an internal object lock with wait/notify to suspend threads until the future completes.
The lock ensures correct coordination between producer and waiting consumer threads with minimal overhead.

 */
public class CompletableFutureOddEven {

    private static final Object lock = new Object();
    private static int num = 1;
    private static final int MAX = 20;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<Void> odd = CompletableFuture.runAsync(() -> {
            try {
                while (true) {
                    synchronized (lock) {
                        // wait while it's not odd and not done
                        while (num <= MAX && (num % 2 == 0)) {
                            lock.wait();
                        }
                        if (num > MAX) {
                            lock.notifyAll(); // wake peer so it can exit
                            break;
                        }
                        System.out.println("Odd:  " + num);
                        num++;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, executor);

        CompletableFuture<Void> even = CompletableFuture.runAsync(() -> {
            try {
                while (true) {
                    synchronized (lock) {
                        // wait while it's not even and not done
                        while (num <= MAX && (num % 2 != 0)) {
                            lock.wait();
                        }
                        if (num > MAX) {
                            lock.notifyAll();
                            break;
                        }
                        System.out.println("Even: " + num);
                        num++;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, executor);

        // wait for both to finish
        CompletableFuture.allOf(odd, even).join();
        executor.shutdown();
        System.out.println("Done");

        // two independent task
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("T1: " + i);
                // simulate work
                try { Thread.sleep(10); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> {
            for (int i = 101; i <= 110; i++) {
                System.out.println("T2: " + i);
                try { Thread.sleep(15); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        // Wait for completion so main doesn't exit early (optional)
        CompletableFuture.allOf(t1, t2).join();
        System.out.println("Both done");
    }
}
