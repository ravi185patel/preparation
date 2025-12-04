package career.java.concept.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


class SequenceSemaphoreLock{
    private final AtomicInteger sequence;
    private final int limit =10;
    private final Semaphore odd,even ;
    public SequenceSemaphoreLock(int sequenceStart){
        this.sequence = new AtomicInteger(sequenceStart);
        odd = new Semaphore(1);
        even = new Semaphore(0);
    }

    /*
    why we can't use while loop with semaphore
    odd.acquire() should be called once to wait for your turn — not repeatedly inside a while that re-acquires permits.
    The pattern while(condition) wait/acquire() is incorrect with semaphores because acquire() consumes a permit each call.
    You end up draining permits or blocking incorrectly.


    That will release even twice when an InterruptedException occurs (and always release once due to finally).
    Double release gives extra permits, breaking the turn-taking (odd will keep running). Same bug in the even method.


Here's what happened step-by-step (simplified):
Odd acquires odd and prints 1, then calls even.release() in the try block — good.
*** The finally runs and calls even.release() again — now semEven has 2 permits.** vimp
Even acquires one permit, prints 2, and releases odd once.
Because semEven still has another permit (from that extra release), Even can acquire again immediately and prints 3 — even though it should have waited for Odd to print 3. That's exactly why you got two evens in a row.

So the root causes:
Unconditional release() in finally (double-release if try also released).
No parity check before printing (you blindly use getAndIncrement() rather than verifying the value is even/odd after acquiring the correct semaphore).
You should only release the other semaphore once per iteration, and only in the normal flow (or carefully in one place to handle exit).

     */
    public void printOdd(){
        while (sequence.get() <= limit) {
            try {
                odd.acquire();
                System.out.println(Thread.currentThread().getName() + " <odd> " + sequence.getAndIncrement());
//                even.release();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                even.release();
            }
        }
    }

    public void printEven(){
        while (sequence.get() <= limit) {
            try {
                even.acquire();
                System.out.println(Thread.currentThread().getName() + " <even> " + sequence.getAndIncrement());
//                odd.release();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                odd.release();
            }
        }
    }
}
public class PrintOddEvenUsingThreadUsingSemaphore {
    public static void main(String[] args) throws InterruptedException {

        Semaphore odd = new Semaphore(1);
        Semaphore even = new Semaphore(0);
//        int sequence =0,limit =10;
        final int limit = 20;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int sequence=1;sequence<=limit;sequence+=2){
                        odd.acquire();
                        System.out.println(Thread.currentThread().getName() + " " + sequence);
                        even.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        },"odd");

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int sequence=2;sequence<=limit;sequence+=2){
                        even.acquire(); // very important : ensure current has turn to print/run
                        System.out.println(Thread.currentThread().getName() + " " + sequence);
                        odd.release();
                        // very important : gives permit to next print/ run
                        // IMPORTANT: release semEven so the even thread can acquire
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        },"even");
//        th.start();
//        th1.start();

//        th.join();
//        th1.join();
//        System.out.println("Done");


        SequenceSemaphoreLock sequenceSemaphoreLock = new SequenceSemaphoreLock(1);
        Thread oddThread = new Thread(sequenceSemaphoreLock::printOdd, "Odd-Thread");
        Thread evenThread = new Thread(sequenceSemaphoreLock::printEven, "Even-Thread");

        oddThread.start();
        evenThread.start();

    }

    public static void usingChatGpt(){
        final int limit = 20;
        final Semaphore semOdd = new Semaphore(1);  // odd starts
        final Semaphore semEven = new Semaphore(0);

        Thread odd = new Thread(() -> {
            try {
                for (int i = 1; i <= limit; i += 2) {
//                    System.out.println("Odd trying to acquire (permOdd=" + semOdd.availablePermits() + ")");
                    semOdd.acquire();
//                    System.out.println("Odd acquired (permOdd=" + semOdd.availablePermits() + ")");
                    System.out.println("Odd:  " + i);
//                    System.out.println("Odd releasing semEven (before release permEven=" + semEven.availablePermits() + ")");
                    semEven.release();
//                    System.out.println("Odd released semEven (after release permEven=" + semEven.availablePermits() + ")");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Odd-Thread");

        Thread even = new Thread(() -> {
            try {
                for (int i = 2; i <= limit; i += 2) {
//                    System.out.println("Even trying to acquire (permEven=" + semEven.availablePermits() + ")");
                    semEven.acquire();
//                    System.out.println("Even acquired (permEven=" + semEven.availablePermits() + ")");
                    System.out.println("Even: " + i);
//                    System.out.println("Even releasing semOdd (before release permOdd=" + semOdd.availablePermits() + ")");
                    semOdd.release();
//                    System.out.println("Even released semOdd (after release permOdd=" + semOdd.availablePermits() + ")");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Even-Thread");

        odd.start();
        even.start();

//        odd.join();
//        even.join();
//        System.out.println("Done");
    }

}
