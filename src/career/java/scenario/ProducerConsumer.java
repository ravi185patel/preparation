package career.java.scenario;

import career.java.concept.concurrency.Pool.BlockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.IntStream;

/*
Threads share heap memory. Visibility issues arise due to local caching,
and Java guarantees visibility through synchronization mechanisms like synchronized,
wait/notify, and volatile which establish happens-before relationships

synchronized ensures mutual exclusion and memory visibility,
while wait() and notifyAll() are used for inter-thread communication by releasing and signaling locks under synchronization.`

📌 Ultra-Short Bullet Summary
synchronized → visibility + atomicity
wait() → release lock & pause thread
notifyAll() → signal waiting threads
Memory sync happens on lock release/acquire
wait/notify must always be used inside synchronized

 */
public class ProducerConsumer {
    private Queue<Integer> queue;
    private int limit;

    public ProducerConsumer(int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public synchronized void consume(){
        try{
            while(queue.size() == 0){
                wait();
            }
            System.out.println("Consumer :"+queue.poll());
            notifyAll();
        }catch (Exception e){

        }
    }

    public synchronized void produce(int no){
        try{
            while(queue.size() > limit){
                wait();
            }
            queue.add(no);
            System.out.println("Producer :"+no);
            notifyAll();
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer(10);
        Runnable runnableConsumer = () -> {
            while (true) {
                producerConsumer.consume();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        Runnable runnableProducer = () -> IntStream.range(0,10).forEach(i->producerConsumer.produce(i));

        Thread th1 = new Thread(runnableProducer);
        Thread th2 = new Thread(runnableConsumer);

        th1.start();
        th2.start();
    }
}
