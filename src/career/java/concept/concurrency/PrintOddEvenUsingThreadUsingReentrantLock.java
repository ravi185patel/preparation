package career.java.concept.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class SequenceReentrantLock{
    int sequence;
    int limit =10;
    ReentrantLock lock ;
    Condition cond;
    public SequenceReentrantLock(int sequenceStart){
        this.sequence = sequenceStart;
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }

    public void printOdd()throws InterruptedException{
        while (sequence <= limit) {
            lock.lock();
            try {
                while (sequence % 2 != 0) {
                    cond.await();
                }
                System.out.println(Thread.currentThread().getName() + " " + sequence);
                sequence++;
                cond.signalAll();
            }catch (InterruptedException e){
                cond.notifyAll();
            }finally {
                lock.unlock();
            }
        }
    }

    public void printEven()throws InterruptedException{
        while (sequence <= limit) {
            lock.lock();
            try {
                while (sequence % 2 == 0) {
                    cond.await();
                }
                System.out.println(Thread.currentThread().getName() + " " + sequence);
                sequence++;
                cond.signalAll();
//                cond.notifyAll(); // traditional and it is method of object class
            }catch (InterruptedException e){
                cond.notifyAll();
            }finally {
                lock.unlock();
            }
        }
    }
}

public class PrintOddEvenUsingThreadUsingReentrantLock {
    public static void main(String[] args) {
        SequenceReentrantLock sequence = new SequenceReentrantLock(1);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sequence.printOdd();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sequence.printEven();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th.start();
        th1.start();
    }

}
