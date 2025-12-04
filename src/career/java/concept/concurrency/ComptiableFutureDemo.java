package career.java.concept.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ComptiableFutureDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10,20,30,40);
        /*
            supplyAsync:- accepts a Supplier as an argument and complete its job asynchronously.
            The result of supplier is run by a task from ForkJoinPool.commonPool() as default.
            We can also pass our Executor. Finally supplyAsync method returns CompletableFuture on which
            we can apply other methods.
            thenApply:- method accepts a function as an argument. Once the calling CompletableFuture completes ,
            then on the result of this stage, that function is applied by thenApply method and returns a CompletableFuture.
            join:- method returns the result after completion or throws CompletionException.
            This method waits for the completion of calling completion stage.
         */
        list.stream().map(data-> CompletableFuture.supplyAsync(()->getNumber(data)))
                                .map(compFuture->compFuture.thenApply(n->n*n)).map(t->t.join())
                                .forEach(s->System.out.println(s));

        /*
        thenAccept:- method accepts Consumer as an argument. On the completion of any completion stage,
        thenAccept method applies Consumer on the result and returns CompletableFuture.
         */
        List<String> list1 = Arrays.asList("A","B","C","D");
        list1.stream().map(data->CompletableFuture.supplyAsync(()->"Processing:"+data)).
                map(compFuture->compFuture.thenAccept(s->System.out.println(s))).map(t->t.join())
                .count();

        /*
         whenComplete:- method uses BiConsumer as an argument. Once the calling completion stage completes,
         whenComplete method applies completion stage result on BiConsumer.
         BiConsumer takes first argument as result and second argument as error if any.
         */
        List<String> list2 = Arrays.asList("A","B","C","D");
        list2.stream().map(s->CompletableFuture.supplyAsync(()->s+s))
                .map(f->f.whenComplete((result,error)->System.out.println(result+" Error:"+error))).count();
        /*
         getNow:- is a method that if calling completion stage is not completed then the value passed to getNow will be set to result.
         */
        List<String> list3 = Arrays.asList("A","B","C","D");
        list3.stream().map(s->CompletableFuture.supplyAsync(()->s+s))
                .map(f->f.getNow("Not Done")).forEach(s->System.out.println(s));
    }
    private static int getNumber(int a){
        return a*a;
    }
}
