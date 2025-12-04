package career.java.concept.concurrency.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/*
ReentrantLock behaves like synchronized and you might wonder when it's appropriate to use one or the other.
Use ReentrantLock when you need timed or interruptible lock waits, non-block-structured locks
(obtain a lock in one method; return the lock in another),  multiple condition variables, or lock polling.
Furthermore, ReentrantLock supports scalability and is useful where there is high contention among threads.
If none of these factors come into play, use synchronized.
 */
class DataQueue{
    private final Queue<Integer> task= new LinkedList<>();
    private ReentrantLock lock;
    private Condition notEmpty,notFull;

    /*
    A java.util.concurrent.locks.Condition interface provides a thread ability to suspend its execution, until the given condition is true.
    A Condition object is necessarily bound to a Lock and to be obtained using the newCondition() method.
     */
    public DataQueue(){
        /*
         ReentrantLock(true) /fair -> Equals chance for all threads (slower)
         ReentrantLock() /Unfair -> get chance in which order thread comes (Possible thread starvation th2-long th3-2ms then th3- need to
         wait long time).
         */
        this.lock=new ReentrantLock(); // new ReentrantLock(true)
        this.notEmpty=lock.newCondition();
        this.notFull=lock.newCondition();

    }

    public void addData(int no){
        task.add(no);
    }

    public int getData(){
        return task.remove();
    }
    public int size(){
        return task.size();
    }

    /*
    We use two Conditions (notFull and notEmpty) because producer and consumer wait for different conditions,
    and waking the wrong thread causes unnecessary wake-ups, wasted CPU, and even thread starvation.

    In producer–consumer, producers wait for “not full” and consumers wait for “not empty”. These are different conditions.
    Using two Conditions gives each group its own waiting queue so we wake the correct threads.
    With one Condition, producers may wake producers and consumers may wake consumers, causing wasted wake-ups, starvation, and lower throughput.

    notFull → exclusively for producers *********
    Contains threads waiting for space.
    notEmpty → exclusively for consumers *********
    Contains threads waiting for items.

    So:
    When producer adds an item → only consumer wakes
    When consumer removes an item → only producer wakes

    This eliminates:
    wrong thread wake-ups
    CPU waste
    starvation
    lock contention

    Imagine one line of people is waiting to use the bathroom and another line is waiting to enter a room.

    One Condition = one common line → confusion.
    Two Conditions = separate lines → clear and fair.
     */
    public Integer consume() throws InterruptedException{
        lock.lock();
        /*
            ReentrantLock allow threads to enter into lock on a resource more than once.
            When the thread first enters into lock, a hold count is set to one.
            Before unlocking the thread can re-enter into lock again and every time hold count is incremented by one.
            For every unlock request, hold count is decremented by one and when hold count is 0, the resource is unlocked.
         */

        /*
        acquires a lock only when it's free at the time of invocation.
        This method returns true when the lock is acquired; otherwise, it returns false.
        Scenario -> already we acquired lock;
        */
//        lock.tryLock(5, TimeUnit.MILLISECONDS);
        /*
        is similar to boolean tryLock();
        however, it lets you specify an amount of time to wait for the lock to become available.
        Pass the magnitude of the delay to time and the units represented by this delay to unit.
        Scenario -> Acquired lock takes more time to execute;

        signal() vs signalAll()

        1) signalAll() wakes all waiting threads. That is safe but can cause a lot of unnecessary wakeups and context switches.
        2) If a single producer can proceed after a consumer removes one element, signal() (wake one thread) is usually preferable for performance.
        Use signalAll() if you expect multiple threads must wake (rare in simple bounded-buffer).

         */
        try{
            System.out.println(lock.isHeldByCurrentThread()+" < consume-fair > "+lock.isFair());
            while(size()==0)
                notEmpty.await();//Causes the current thread to wait until it is signalled or interrupted.
            Integer data=getData();
            notFull.signalAll(); //Wakes up all  waiting thread.
            return data;
        }
        finally {
            lock.unlock();
        }
    }
    public void produce() throws InterruptedException{
        lock.lock();
        try{
            System.out.println(lock.isHeldByCurrentThread()+" <producer-fair> "+lock.isFair());
            while(size()==100)
                notFull.await(); //Causes the current thread to wait until it is signalled or interrupted.
            addData(size());
            notEmpty.signalAll(); //Wakes up all  waiting thread.
        }
        finally {
            lock.unlock();
        }
    }
}
class Producer implements Runnable{
    private  DataQueue dataQueue;
    public Producer(DataQueue dataQueue){
        this.dataQueue=dataQueue;
    }

    @Override
    public void run(){
        try {
            for(int i=0;i<10;i++)
                this.dataQueue.produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    private  DataQueue dataQueue;
    public Consumer(DataQueue dataQueue){
        this.dataQueue=dataQueue;
    }

    @Override
    public void run(){
        try {
            for(int i=0;i<10;i++)
                System.out.println(this.dataQueue.consume());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class ConditionsLocks {
    public static void main(String[] args){
        System.out.println("producer - consumer");
        DataQueue dataQueue=new DataQueue();
        Producer p=new Producer(dataQueue);
        Consumer c=new Consumer(dataQueue);
        Thread t1=new Thread(p);
        Thread t2=new Thread(c);
        t1.start();
        t2.start();
    }
}
