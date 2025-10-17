package career.java.java21;

//import java.util.concurrent.StructuredTaskScope;
/*
This program:

Runs both functions concurrently in virtual threads.

Waits for both to finish.

Cancels both if one fails.

Collects and prints the results.
 */
public class UserService {
    public static void main(String[] args) throws Exception {
//
//    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//
//            // Run tasks in virtual threads
//            var userFuture = scope.fork(UserService::getUser);
//            var orderFuture = scope.fork(UserService::getOrders);
//
//            scope.join();           // Wait for both
//            scope.throwIfFailed();  // If any task failed, throw
//
//            // Get the results
////            String user = userFuture.resultNow();
////            String orders = orderFuture.resultNow();
//
//            System.out.println("✅ Response:");
////            System.out.println(user);
////            System.out.println(orders);
//        }

//    static String getUser() throws InterruptedException {
//        Thread.sleep(1000); // Simulate delay
//        return "[User] Name: Alice, ID: 123";
//    }
//
//    static String getOrders() throws InterruptedException {
//        Thread.sleep(1200); // Simulate delay
//        return "[Orders] 3 items: Book, Laptop, Pen";
//    }
    }
}