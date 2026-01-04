package career.java.scenario;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class IncrementSharedCounterWithThreads {

    private int counter=0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void increment(){
        counter++;
        System.out.println("Increment :"+counter+" | "+atomicInteger.incrementAndGet());
    }

    public void decrement(){
        counter--;
        System.out.println("Decrement :"+counter+" | "+atomicInteger.decrementAndGet());
    }

    public int getCount() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        IncrementSharedCounterWithThreads instance = new IncrementSharedCounterWithThreads();
        Thread[] threads = new Thread[10];

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                instance.increment();
            }
        };

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final Count: " + instance.getCount());
    }
}
