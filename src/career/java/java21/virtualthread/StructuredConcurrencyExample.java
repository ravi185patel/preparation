package career.java.java21.virtualthread;// File: StructuredConcurrencyExample.java
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyExample {
    public static void main(String[] args) {
//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            var f1 = scope.fork(() -> {
//                Thread.sleep(200);
//                return "result1";
//            });
//            var f2 = scope.fork(() -> {
//                Thread.sleep(100);
//                return "result2";
//            });
//
//            scope.join();          // wait for all or for a failure
//            scope.throwIfFailed(); // rethrow first failure if any
////            System.out.println("f1: " + f1.resultNow() + ", f2: " + f2.resultNow());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
