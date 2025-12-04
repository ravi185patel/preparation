package career.java.concept.concurrency;


import java.util.concurrent.CountDownLatch;
class Worker implements Runnable{
    private CountDownLatch countDownLatch;
    private int delay;
    public Worker(int delay,CountDownLatch countDownLatch){
        this.delay = delay;
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName()+" finished");
    }
}
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(4);
        Worker worker1=new Worker(1000,countDownLatch);
        Worker worker2=new Worker(2000,countDownLatch);
        Worker worker3=new Worker(3000,countDownLatch);
        Worker worker4=new Worker(4000,countDownLatch);

        Thread thread1=new Thread(worker1,"Worker 1");
        Thread thread2=new Thread(worker2,"Worker 2");
        Thread thread3=new Thread(worker3,"Worker 3");
        Thread thread4=new Thread(worker4,"Worker 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // The main task waits for four threads
        countDownLatch.await();

        Worker worker5=new Worker(2000,countDownLatch);

        Thread thread5=new Thread(worker5,"Worker 5");
        thread5.start();

        System.out.println(Thread.currentThread().getName() +
                " has finished");

//        ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor) Executors.newFixedThreadPool(2);
//        threadPoolExecutor.execute(worker1);
//        threadPoolExecutor.execute(worker2);
//        threadPoolExecutor.execute(worker3);
//        threadPoolExecutor.execute(worker4);
//        threadPoolExecutor.shutdown();

        countDownLatch.await();
    }
}
