package career.java.java21.virtualthread;// File: VirtualThreadSimple.java
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/*
Migration Checklist
1. Identify blocking calls (DB, HTTP, file I/O) and run them on virtual-thread executor.
2. Identify CPU-bound tasks and run them on a bounded platform thread pool (fixed thread pool).
3. Pass executors explicitly to CompletableFuture.supplyAsync and thenApplyAsync when you need control.
4. Add timeouts and exception handling (orTimeout, completeOnTimeout, exceptionally).
5. Monitor for JNI/native pinning; offload to a dedicated OS thread if necessary.
6. Avoid excessive ThreadLocal usage; clear ThreadLocals when necessary.
7. Use structured concurrency for grouped task lifetimes when appropriate.
8. Measure memory and latency; don't rely solely on 'it should scale' — benchmark real workload.
 */

public class VirtualThreadSimple {
    public static void main(String[] args) throws InterruptedException {
        final int n = 200_000; // try 200k virtual threads (adjust by memory)
        List<Thread> threads = new ArrayList<>(n);

        Instant start = Instant.now();
        for (int i = 0; i < n; i++) {
            Thread vt = Thread.ofVirtual().start(() -> {
                // simulate some blocking I/O like work
                try {
                    // cheap sleep to yield — in real apps this is an I/O
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    // ignore for this demo
                }
            });
            threads.add(vt);
        }

        for (Thread t : threads) t.join();
        Instant end = Instant.now();
        System.out.println("Created and joined " + n + " virtual threads in " + Duration.between(start, end).toMillis() + " ms");
    }
}
