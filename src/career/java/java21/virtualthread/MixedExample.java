package career.java.java21.virtualthread;

import java.util.concurrent.*;

public class MixedExample {
    public static void main(String[] args) {
        try (ExecutorService vts = Executors.newVirtualThreadPerTaskExecutor()) {
            ExecutorService cpuPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

            // Fetch (blocking IO) -> then CPU heavy processing
            CompletableFuture<byte[]> io = CompletableFuture.supplyAsync(() -> {
                // simulate blocking IO
                try { Thread.sleep(200); } catch (InterruptedException e) {}
                return new byte[1024];
            }, vts);

            CompletableFuture<Integer> processed = io.thenApplyAsync(bytes -> {
                // CPU-bound processing on platform threads
                int sum = 0;
                for (byte b : bytes) sum += b;
                return sum;
            }, cpuPool);

            System.out.println("Result: " + processed.join());

            cpuPool.shutdown();
        }
        // Old
        ExecutorService pool = Executors.newFixedThreadPool(200);

// New (no tuning)
        pool = Executors.newVirtualThreadPerTaskExecutor();
//        try{
//
//            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//                var r1 = scope.fork(() -> { Thread.sleep(100); return "A"; });
//                var r2 = scope.fork(() -> { Thread.sleep(200); return "B"; });
//
//                scope.join();
//                scope.throwIfFailed();
//                System.out.println(r1.resultNow() + " " + r2.resultNow());
//            }
//        }
    }


}
