package career.java.concept.concurrency.lockpkg;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
The class ReentrantLock is a mutual exclusion lock with the same basic behavior as the implicit monitors accessed via the synchronized keyword
but with extended capabilities. As the name suggests this lock implements reentrant characteristics just as implicit monitors.

The method tryLock() as an alternative to lock() tries to acquire the lock without pausing the current thread.
The boolean result must be used to check if the lock has actually been acquired before accessing any shared mutable variables.
 */
class Counter {
    private Lock lock; // Lock to protect our counter
    private int count; // Integer to hold count

    public Counter(Lock myLock) {
        this.lock = myLock;
    }

    public final int getCount() {
        lock.lock();
        try {
            count++;
            return count;
        } finally {
            lock.unlock();
        }

    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        // Let's create a counter and shared it between three threads
        // Since Counter needs a lock to protect its getCount() method
        // we are giving it a ReentrantLock.
        final Counter myCounter = new Counter(new ReentrantLock());
        final Counter myCounter1 = new Counter(new ReentrantLock());

        /*
        1. Instead of sharing a lock, they provide different locks to each thread.
        This often happens to them unknowingly because they usually put the lock and guarded block inside Runnable,
        and they pass two different instances of Runnable to two different threads like where SimpleLock is a Runnable.
        Since here myLock is an instance variable, each instance of SimpleLock has its own myLock instance,
        which means firstThread and secondThread are using different locks and they can run protected code simultaneously.

         */

        // Task to be executed by each thread
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.printf("Count at thread %s is %d %n",
                        Thread.currentThread().getName(), myCounter.getCount());
            }
        };

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.printf("Count at thread %s is %d %n",
                        Thread.currentThread().getName(), myCounter1.getCount());
            }
        };

        // Creating three threads
        Thread t1 = new Thread(r, "T1");
        Thread t2 = new Thread(r, "T2");
        Thread t3 = new Thread(r, "T3");
        Thread t4 = new Thread(r1, "T4");

        //starting all threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
