package career.java.concept.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
/*
work-stealing algorithm
Fork-join used in java.
1] One such implementation, introduced in Java SE 8, is used by the java.util.Arrays class for its parallelSort() methods.
These methods are similar to sort(), but leverage concurrency via the fork/join framework.
Parallel sorting of large arrays is faster than sequential sorting on multiprocessor systems.

2] Parallelism used in Stream.parallel(). Read more about this parallel stream operation in java 8.

The invoke() method combines fork() and join() in a single call. It starts the task, waits for it to end and return the result.

 */
public class FrokJoinDemo {
    public static void main(final String[] arguments) throws InterruptedException,
            ExecutionException {

        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(nThreads);

        int[] numbers = new int[1000];

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
        Long result = forkJoinPool.invoke(new Sum(numbers,0,numbers.length)); // maintask
//        The invoke() method combines fork() and join() in a single call. It starts the task,
//        waits for it to end and return the result.
        System.out.println(result);
    }

    static class Sum extends RecursiveTask<Long> { // RecursiveTask -> ForkJoinTask -> Future
        int low;
        int high;
        int[] array;

        Sum(int[] array, int low, int high) {
            this.array = array;
            this.low   = low;
            this.high  = high;
        }

        protected Long compute() {

            if(high - low <= 10) {
                long sum = 0;

                for(int i = low; i < high; ++i)
                    sum += array[i];
                return sum;
            } else {
                int mid = low + (high - low) / 2;
                Sum left  = new Sum(array, low, mid);
                Sum right = new Sum(array, mid, high);
                // subtask
                left.fork(); //The fork() method submits the task to execute asynchronously. This method return this (ForkJoinTask) and the calling thread continues to run.
                long rightResult = right.compute();
                long leftResult  = left.join(); // The join() method waits until the task is done and returns the result.
                // join subtask
                return leftResult + rightResult;
            }
        }
    }
}
