package career.java.concept.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Party implements Runnable{
    private CyclicBarrier barrier;
    public Party(CyclicBarrier barrier){
        this.barrier=barrier;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is calling await()");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName() + " has started running again");
    }
}
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("action performed after all thread meet condition..."); // you can create separate class (called task)
                // which can be passwed in cyclicBarrier to be run/work on by each thread.
            }
        });
        Party first = new Party(barrier);
        Party second = new Party(barrier);
        Party third = new Party(barrier);
        Party fourth = new Party(barrier);
//        Thread friend1=new Thread(first,"First");
//        Thread friend2=new Thread(second,"Second");
//        Thread friend3=new Thread(third,"Third");
//        Thread friend4=new Thread(fourth,"Fourth");

        Thread friend1=new Thread(first);
        Thread friend2=new Thread(second);
        Thread friend3=new Thread(third);
        Thread friend4=new Thread(fourth);

        friend1.start();
        friend2.start();
        friend3.start();
        friend4.start();

    }
}
