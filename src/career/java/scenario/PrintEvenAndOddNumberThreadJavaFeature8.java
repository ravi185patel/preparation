package career.java.scenario;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class PrintNumberJava8{

//    AtomicInteger count;
    private Object object;
    int limit;
    int count;
    public IntPredicate odd = (e)-> e%2 != 0;
    public IntPredicate even = (e)-> e%2 == 0;
    public PrintNumberJava8(int limit){
        this.limit =limit;
        count=0;
//        count = new AtomicInteger(0);
    }
    public void print(IntPredicate predicate){
        IntStream.range(0,limit).filter(predicate).forEach((i)->printNo(predicate));
    }
    public void printNo(IntPredicate predicate){
        synchronized (this){
            while (count <= limit && !predicate.test(count)) { //Threads now wait only when it is NOT their turn
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (count <= limit) {
                System.out.println(Thread.currentThread().getName() + " : " + count);
                count++;
                notifyAll();
            }
        }
    }

}
public class PrintEvenAndOddNumberThreadJavaFeature8 {
    public static void main(String[] args) {
        PrintNumberJava8 printNumber = new PrintNumberJava8(10);
        Runnable rOdd = ()->printNumber.print(printNumber.odd);
        Runnable rEven = () -> printNumber.print(printNumber.even);
        Thread thOdd = new Thread(rOdd);
        Thread thEven = new Thread(rEven);

        thOdd.start();
        thEven.start();
    }
}
