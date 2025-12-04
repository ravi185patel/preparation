package career.java.concept.concurrency;
/*
    Introduce in java-8
    Improvements in java-9
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class CompletableFutureTest{

}
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("string");
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<String> greetingFuture
                = CompletableFuture.supplyAsync(() -> {
            // some async computation
            return "Hello from CompletableFuture";
        });

        System.out.println(greetingFuture.get());


        CompletableFuture<String> helloFuture
                = CompletableFuture.supplyAsync(() -> "Hello");
        greetingFuture
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<String> combinedFuture
                = helloFuture.thenCombine(
                greetingFuture, (m1, m2) -> m1 + " " + m2);

        System.out.println(combinedFuture.get());


        CompletableFuture<Integer> printOdd
                = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });


        CompletableFuture<Integer> printEven
                = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        });

        CompletableFuture.allOf(printEven,printOdd).join();
        System.out.println(Thread.currentThread().getName());
//        System.out.println(printOdd.isDone());
//        System.out.println(printEven.isDone());


    }
}
