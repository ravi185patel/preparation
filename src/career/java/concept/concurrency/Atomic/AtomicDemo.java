package career.java.concept.concurrency.Atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.*;

class AtomicPkg{

     AtomicInteger atomicInteger=new AtomicInteger(1);
     AtomicLong atomicLong=new AtomicLong(1);
     AtomicBoolean atomicBoolean=new AtomicBoolean(false);

    /*Object initialRef   = null;
    int    initialStamp = 0;
    AtomicStampedReference atomicStampedReference =new AtomicStampedReference(initialRef, initialStamp);*/
    volatile String str="need to update";
    String initialRef   = "initial value referenced";
    int    initialStamp = 0;

    AtomicStampedReference<String> atomicStringReference =
            new AtomicStampedReference<String>(
                    initialRef, initialStamp
            );
     int atomicIntArr[]=new int[10];
     AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(atomicIntArr);

     long atomicLongArr[]=new long[10];
     AtomicLongArray atomicLongArray=new AtomicLongArray(atomicLongArr);

    Object[] source = new Object[10];
     AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(source);
    // AtomicReferenceArray<String> array = new AtomicReferenceArray<String>(source);


    static class Test {
        volatile int count;

        public int getCount() {
            return count;
        }
    }
}
public class AtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicPkg atomicPkg=new AtomicPkg();
       /* AtomicStampReference*/
        String newRef   = "new value referenced";
        int    newStamp = atomicPkg.initialStamp + 1;

        boolean exchanged = atomicPkg.atomicStringReference
                .compareAndSet(atomicPkg.initialRef, newRef, atomicPkg.initialStamp, newStamp);
        System.out.println("exchanged: " + exchanged);  //true

        exchanged = atomicPkg.atomicStringReference
                .compareAndSet(atomicPkg.initialRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicPkg.atomicStringReference
                .compareAndSet(newRef, "new string", atomicPkg.initialStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicPkg.atomicStringReference
                .compareAndSet(newRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //true

        /*AtomicIntegerArray*/
        atomicPkg.atomicIntegerArray.set(5,10000);
        System.out.println(atomicPkg.atomicIntegerArray.get(5)+" <---- getting element from AtomicIntegerArray");
        /*AtomicLongArray*/
        atomicPkg.atomicLongArray.set(5,10000);
        System.out.println(atomicPkg.atomicLongArray.get(5)+" <---- getting element from AtomicLongArray");
        /*AtomicReferenceArray*/
        atomicPkg.atomicReferenceArray.set(0,"ravi");
        atomicPkg.atomicReferenceArray.set(1,"ravidpatel");
//        atomicPkg.atomicReferenceArray.set(12,"ravidpatel"); // getting error bcz object array is limited to 10 not 12// getting exception
        System.out.println(atomicPkg.atomicReferenceArray.get(1)+" <---- getting element from AtomicReferenceArray");

        /*AtomicIntegerFiledUPdate*/
        /*
        Field Updater classes can be used to perform atomic operation on a selected volatile field of a selected class.
        AtomicReferenceFieldUpdater, AtomicIntegerFieldUpdater, and AtomicLongFieldUpdater are reflection-based utilities
        that provide access to the associated field types.
        These are mainly of use in atomic data structures in which several volatile fields of the same node
        (for example, the links of a tree node) are independently subject to atomic updates.
        These classes enable greater flexibility in how and when to use atomic updates,
        at the expense of more awkward reflection-based setup, less convenient usage, and weaker guarantees.
         */
        AtomicIntegerFieldUpdater<AtomicPkg.Test> countFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(AtomicPkg.Test.class, "count");
        AtomicPkg.Test test = new AtomicPkg.Test();

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                for (int c = 0; c < 100; c++) {
                    countFieldUpdater.incrementAndGet(test);
                }
            });
        }
        es.shutdown();
//        es.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("count: " + test.getCount());

        /**
         *
         */
        AtomicReferenceFieldUpdater<AtomicPkg,String> upd =
                AtomicReferenceFieldUpdater.newUpdater(AtomicPkg.class, String.class, "str");
        String s1 = "one";
        String s2 = "two";
//        atomicPkg.str = s1;

        System.out.println(atomicPkg.str);
        System.out.println(upd.compareAndSet(atomicPkg, s1, s2));
        System.out.println(atomicPkg.str);
        System.out.println(atomicPkg.str == s2);
    }
}
