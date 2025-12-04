package career.java.concept.concurrency.Pool;

import java.util.LinkedList;
import java.util.Queue;
//https://www.baeldung.com/java-producer-consumer-problem
//https://www.cesarsotovalero.net/blog/the-producer-consumer-pattern-in-java-made-easy.html#:~:text=The%20Producer%20collects%20the%20data,the%20whole%20process%20as%20desired.
public class BlockingQueue <Type> {
    private Queue<Type> queue = new LinkedList<Type>();
    private int EMPTY = 0;
    private int MAX_TASK_IN_QUEUE = -1;

    public BlockingQueue(int size){
        this.MAX_TASK_IN_QUEUE = size;
    }

    public synchronized void enqueue(Type task)
            throws InterruptedException  {
        while(this.queue.size() == this.MAX_TASK_IN_QUEUE) {
            wait();
        }
        if(this.queue.size() == EMPTY) {
            notifyAll();
        }
        this.queue.offer(task);
    }

    public synchronized Type dequeue()
            throws InterruptedException{
        while(this.queue.size() == EMPTY){
            wait();
        }
        if(this.queue.size() == this.MAX_TASK_IN_QUEUE){
            notifyAll();
        }
        return this.queue.poll();
    }
}