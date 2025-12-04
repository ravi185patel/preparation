package career.java.concept.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/*
Note :
  May be you are not getting what output you want.
  Example is only for understand may be output is not right or valid.
 */
class Voletile{
    boolean flag;
    int noCounter; // visibility problem during threading
    volatile int counter; // solve visibility problem but create synchronized problem ( solution make method synchronized)
    AtomicInteger atomicInteger=new AtomicInteger(0); // alternative for volatile with synchronized method

    public int getNoCounter() {
        return noCounter;
    }

    public void setNoCounter() {
        ++this.noCounter;
    }

    public int getAtomicInteger(){
        return atomicInteger.incrementAndGet();
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        ++this.counter;
    }

    public Voletile(){
        flag=false;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
class Example implements Runnable{
    private Voletile voletile;

    public Example(Voletile voletile){
        this.voletile=voletile;
    }

    @Override
    public void run(){
//        setFlag(Thread.currentThread().getName());
//        setNormalCounter(Thread.currentThread().getName());
//        setVoletilelCounter(Thread.currentThread().getName());
        setVoletilelCounterSync(Thread.currentThread().getName());
//        setAutomicInteger(Thread.currentThread().getName());
        System.out.println("end");
    }
//    synchronized
     public  void setFlag(String name){
         System.out.println("normal flag and counter changed by thread");
         System.out.println(voletile.isFlag()+" "+name);
         voletile.setFlag(true);
         System.out.println(voletile.isFlag()+" "+name);
         System.out.println("normal flag changed by thread end");
    }

    /*
      Thread will read value from memory and set it in their own memory (called cache) which will updated in memory.
      so before update by thread one ( means from his cache to memory) it is read by other thread and where both will read same value of this variable.
     */
    public  void setNormalCounter(String name){ // both thread will read same value and increment it
        System.out.println("Normal counter increment by thread");
        System.out.println(voletile.getNoCounter()+" "+name);
        voletile.setNoCounter();
    }

    /*
       As mention above, thread update in their own memory before update in main memory.
       So after declare variable voletile, thread will update and read value directly from main memory instead of their cache.
       So will get latest value after thread change it.
       But again in multithreading env there is a possiblity that both thread read at a same time.
       So to resolve this issue we have to declare method synchronize
     */

    public  void setVoletilelCounter(String name){ // resolve visibility problem but again same
        System.out.println("voletile counter increment by thread");
        System.out.println(voletile.getCounter()+" "+name);
        voletile.setCounter();
    }

    /*
     Very important point.
     if you declare method synchronize it will not work. both threads will execute on same object simultaneously.
     Why : here when you declare method sync it actually put lock on object of class in which it is declared.
      Ex : below method. it will put lock on   Example instead of voletile.
      So use synchronized block to understand scenario.
     */

    public void setVoletilelCounterSync(String name){
        synchronized (voletile) {
            System.out.println("voletile counter increment by thread");
            System.out.println(voletile.counter + " " + name);
            voletile.counter++;
        }
    }

    public  void setAutomicInteger(String name){
        System.out.println("Atomic integer  counter increment by thread end.");
        int res = voletile.getAtomicInteger();
        System.out.println(res+" "+name);
    }
}

public class VoltileVsAtomic {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Voletile voletile=new Voletile();
        Thread t1=new Thread(new Example(voletile),"t1");
        Thread t2=new Thread(new Example(voletile),"t2");
//        threadPoolExecutor.submit(t1); // submit for callable interface
//        threadPoolExecutor.submit(t2);
//        for(int i=0;i<10;i++) {
            threadPoolExecutor.execute(t1); // for runnable
            threadPoolExecutor.execute(t2);
//        }
        threadPoolExecutor.shutdown();

    }
}
