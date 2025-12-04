package career.java.concept.concurrency;

class Sequence{
    int sequence;
    int limit =10;
    public Sequence(int sequenceStart){
        this.sequence = sequenceStart;
    }

    public synchronized void printOdd()throws InterruptedException{
        while(sequence <= limit) {
            while(sequence%2 != 0){
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " " + sequence);
            sequence++;
            notifyAll();
        }
    }

    public synchronized void printEven()throws InterruptedException{
        while(sequence <= limit) {
            while(sequence%2 != 0){
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " " + sequence);
            sequence++;
            notifyAll();
        }
    }
}

public class PrintOddEvenUsingThread {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(1);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sequence.printOdd();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sequence.printEven();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th.start();
        th1.start();
    }

}
