package career.java.concept.concurrency.producerconsumer;

import java.util.concurrent.Semaphore;

public class BoundedBuffer {
    private final int[] buffer;
    private int putIndex = 0, takeIndex = 0;

    private final Semaphore slots; // empty
    private final Semaphore items; // full

    public BoundedBuffer(int capacity) {
        buffer = new int[capacity];
        slots = new Semaphore(capacity); // provide permission up to capacity for producer so he can enter that many into queue
        items = new Semaphore(0);
    }

    public void put(int value) throws InterruptedException {
        slots.acquire();          // wait if full
        buffer[putIndex] = value;
        putIndex = (putIndex + 1) % buffer.length;
        items.release();          // one more item
    }

    public int take() throws InterruptedException {
        items.acquire();         // wait if empty
        int val = buffer[takeIndex];
        takeIndex = (takeIndex + 1) % buffer.length;
        slots.release();         // one slot free
        return val;
    }

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer(10);
        Thread producer = new Thread(()->{
            try{
                for(int i=0;i<20;i++) {
                    boundedBuffer.put(i);
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(()->{
            try{
                for(int i=0;i<20;i++) {
                    int val = boundedBuffer.take();
                    System.out.println(val);
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
