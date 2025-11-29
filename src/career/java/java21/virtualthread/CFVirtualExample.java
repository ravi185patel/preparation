package career.java.java21.virtualthread;// Java 21+
//import java.util.concurrent.*;

public class CFVirtualExample {
    public static void main(String[] args) throws Exception {
//        try (ExecutorService vts = Executors.newVirtualThreadPerTaskExecutor()) {
//            CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> {
//                // runs on a virtual thread -> blocking is cheap
//                try {
//                    Thread.sleep(200);               // OK on virtual thread
//                    return "done on " + Thread.currentThread();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }, vts);
//
//            // you can chain normally; supply explicit executor for async stages if needed
//            CompletableFuture<String> f2 = f.thenApplyAsync(s -> s + " -> processed", vts);
//
//            System.out.println(f2.join());
//        }
    }
}
