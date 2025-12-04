package career.java.concept.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class PrintNoUsingJava8Feature{

    private static Object object = new Object();
    public static IntPredicate evenNo= e->e%2==0;
    public static IntPredicate oddNo= e->e%2!=0;

    public static void printNo(IntPredicate predicate){
        IntStream.range(1,12).filter(predicate).forEach(PrintNoUsingJava8Feature::execute);
    }

    public static void execute(int no){
        synchronized(object){
            try {
                System.out.println("Thread name :"+Thread.currentThread().getName()+" -- :"+no);
                object.notify();
                object.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

public class PrintEvenOddNoUsingThreadUsingJava8Feature {

    public static void main(String[] args) throws InterruptedException {

//        PrintNoUsingJava8Feature.print();
        CompletableFuture.runAsync(()->PrintNoUsingJava8Feature.printNo(PrintNoUsingJava8Feature.oddNo));
        CompletableFuture.runAsync(()->PrintNoUsingJava8Feature.printNo(PrintNoUsingJava8Feature.evenNo));
        Thread.sleep(1000);

    }
}
