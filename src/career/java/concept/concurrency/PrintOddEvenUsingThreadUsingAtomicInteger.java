package career.java.concept.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;


/*
AtomicInteger is faster than synchronized because it uses non-blocking, lock-free operations (CAS)
that avoid thread blocking, context switching, and OS scheduling, while synchronized always involves monitor lock acquisition
which is slower under contention.

3️⃣ How AtomicInteger uses CAS

AtomicInteger is built on:
volatile int value
Unsafe class (or VarHandle in newer Java)
CAS (compare-and-set) loops

Steps
Read the current value → oldValue
Calculate newValue
Try CAS
If CAS fails (someone else changed it), retry
If CAS succeeds, we are done

6️⃣ CAS has 3 important issues (interview)
❌ 1. ABA Problem : Value changes A → B → A, and CAS sees only “A == A”.
❌ 2. Spin loop cost: If many threads keep failing CAS, they retry and retry → CPU cost increases.
❌ 3. Cannot update multiple variables : CAS is only for one memory location.
But for simple counters, CAS is perfect.


✓ AtomicInteger is faster only for simple atomic operations

Examples:
increment counter
update value
compare and swap

❗ But synchronized is required if you modify multiple shared variables or execute a group of statements atomically.

CAS :
CAS atomically updates a value only if it matches an expected value.
AtomicInteger uses a CAS retry loop to implement lock-free, thread-safe operations like increment,
ensuring high performance without using synchronized or locks.

 */

public class PrintOddEvenUsingThreadUsingAtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger num = new AtomicInteger(1);
        final int LIMIT = 20;

        Thread odd = new Thread(() -> {
            while (true) {
                int val = num.get();
                if (val > LIMIT) break;
                if ((val & 1) == 1) {
                    if (num.compareAndSet(val, val + 1)) {
                        System.out.println("Odd:  " + val);
                    } else {
                        Thread.yield(); // backoff on contention
                    }
                } else {
                    Thread.yield();
                }
            }
        });

        Thread even = new Thread(() -> {
            while (true) {
                int val = num.get();
                if (val > LIMIT) break;
                if ((val & 1) == 0) {
                    if (num.compareAndSet(val, val + 1)) {
                        System.out.println("Even: " + val);
                    } else {
                        Thread.yield();
                    }
                } else {
                    Thread.yield();
                }
            }
        });

        odd.start();
        even.start();
        odd.join();
        even.join();
        System.out.println("Done");
    }
}
