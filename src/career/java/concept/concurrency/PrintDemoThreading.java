package career.java.concept.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TaskEvenOdd implements  Runnable{
    private int max;
    private Printer print;
    private boolean isEvenNumber;

    public TaskEvenOdd(Printer print,int max,  boolean isEvenNumber) {
        this.max = max;
        this.print = print;
        this.isEvenNumber = isEvenNumber;
    }

    public void run(){
        int number=isEvenNumber ? 2:1;
        while(number <=max){
            if(isEvenNumber){
                print.printEven(number);
            }else{
                print.printOdd(number);
            }
            number+=2;
        }
    }

}

class Printer{
    private volatile  boolean isOdd;

    synchronized void printEven(int number){
        while(!isOdd){
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName()+" : "+number);
        isOdd=false;
        notify();
    }

    synchronized void printOdd(int number){
        while(isOdd){
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName()+" : "+number);
        isOdd=true;
        notify();
    }
}
public class PrintDemoThreading {
    public static void main(String[] args) {
        Printer print = new Printer();

        // normal thread execution
//        Thread t1 = new Thread(new TaskEvenOdd(print, 10, false),"Odd");
//        Thread t2 = new Thread(new TaskEvenOdd(print, 10, true),"Even");
//        t1.start();
//        t2.start();
//        while (!t1.isAlive()){
//            t1.start();
//        }

        // Use Thread Executor
        ExecutorService threadPoolExecutors= Executors.newFixedThreadPool(2);
        threadPoolExecutors.execute(new TaskEvenOdd(print, 10, false));
        threadPoolExecutors.execute(new TaskEvenOdd(print, 10, true));
        threadPoolExecutors.shutdown();
//        ThreadPoolExecutor
    }
}
