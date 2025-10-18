package career.java.java21;

//import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyExample {
    static String fetchUser() {
        return "User123";
    }

    static String fetchOrder() {
        return "Order456";
    }

    public static void main(String[] args) throws Exception {
//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            var userFuture = scope.fork(StructuredConcurrencyExample::fetchUser);
//            var orderFuture = scope.fork(StructuredConcurrencyExample::fetchOrder);
//
//            scope.join();           // Wait for both tasks
//            scope.throwIfFailed();  // Propagate any exception
//
//            // All tasks succeeded
////            String user = userFuture.resultNow();
////            String order = orderFuture.resultNow();
//
////            System.out.println("User: " + user + ", Order: " + order);
//        }
    }
}
