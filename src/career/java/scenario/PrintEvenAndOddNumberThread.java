package career.java.scenario;

import java.util.concurrent.atomic.AtomicInteger;

class PrintNumber{

//    AtomicInteger count;
    int limit;
    int count;
    public PrintNumber(int limit){
        this.limit =limit;
        count=0;
//        count = new AtomicInteger(0);
    }
    public synchronized void printOdd(){
        while(count <= limit ){
            try {
                while (count % 2 == 0) {
                    wait();
                }
                System.out.println("print odd: " + count);
                count++;
                notifyAll();
            }catch (InterruptedException e){
                System.out.println(" Thread interrupted");
            }finally {

            }
        }
    }

    public synchronized void printEven(){
        while(count <= limit ){
            try {
                while (count % 2 != 0) {
                    wait();
                }
                System.out.println("print even: " + count);
                count++;
                notifyAll();
            }catch (InterruptedException e){
                System.out.println(" Thread interrupted");
            }finally {

            }
        }
    }
}
public class PrintEvenAndOddNumberThread {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber(10);
        Runnable rOdd = ()->printNumber.printOdd();
        Runnable rEven = () -> printNumber.printEven();
        Thread thOdd = new Thread(rOdd);
        Thread thEven = new Thread(rEven);

        thOdd.start();
        thEven.start();
    }
}
