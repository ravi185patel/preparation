package career.java.concept.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

class JMMExample {

    // 1️⃣ volatile → visibility + ordering
    private volatile boolean running = true;

    // 2️⃣ Atomic → atomicity + visibility
    private AtomicInteger atomicCounter = new AtomicInteger(0);

    // 3️⃣ synchronized → atomicity + visibility + ordering
    private int syncCounter = 0;

    public synchronized void incrementSync() {
        syncCounter++;
    }

    public void start() {

        Thread writer = new Thread(() -> {
            atomicCounter.incrementAndGet(); // atomic
            incrementSync();                  // synchronized
            running = false;                  // volatile write
        });

        Thread reader = new Thread(() -> {
            while (running) { }               // volatile read
            System.out.println(atomicCounter.get());
            System.out.println(syncCounter);
        });

        writer.start();
        reader.start();
    }
}
