package career.java.java14;

import java.util.LinkedList;
import java.util.Queue;

/*
To represent a simple data class like User, we had to write a lot of boilerplate:
✔ Auto-generates:
constructor
getters
equals()
hashCode()
toString()

✔ Class becomes immutable by default
✔ Perfect for DTOs, API responses, config objects, events, DDD value objects

--------Why interviewers love this?-----------
Because records are great for:
Microservices DTOs
Event-driven systems
Kafka message models
Immutable data modeling
Cleaner code + value semantics
When NOT to Use Records
-------------------------------------------------------

❌ If you need mutability
❌ If you need inheritance
❌ If you have large business logic in the class
❌ If you need JPA entities (Hibernate support is poor)
❌ If you need lazy-loaded fields

⭐ When RECORDS Are PERFECT

✔ DTOs between microservices
✔ Kafka message schemas
✔ Request/Response classes in REST
✔ Configurations
✔ Value objects
✔ Small data carriers
✔ Maps keys (because immutable & hashable)

🔥 Summary (Interview-Friendly)

Record = Immutable Data Carrier

Auto-generates:
fields
constructor
accessors
equals/hashCode
toString

Internally:
compiled to a final class extending java.lang.Record
all fields private final
strict rules: no setters, no inheritance
Optimized for clean code, immutability, and thread safety.

*/
public class RecordDemo {
    public record User(String name, int age) { }

    public record Product(String id, double price) {

        // Compact constructor - for validation
        public Product {
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
        }
    }

    public record Point(int r, int c) { }

    public static void main(String[] args) {
        User u = new User("Aryan", 35);

        System.out.println(u.name()); // getter
        System.out.println(u.age());

        System.out.println(u); // toString

            Product p = new Product("P100", 1299.0);

            System.out.println(p);

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));

        Point p1 = q.poll();
        System.out.println("Row: " + p1.r());
        System.out.println("Col: " + p1.c());


        OrderCreated event = new OrderCreated("O101", "U12", 2500.0);

        System.out.println(event.orderId());
        System.out.println(event);
        Money m1 = new Money(100, "INR");
        Money m2 = new Money(50, "INR");

        Money m3 = m1.add(m2);

        System.out.println(m3); // Money[amount=150.0, currency=INR]
    }

    public record Money(double amount, String currency) {

        public Money add(Money other) {
            if (!other.currency.equals(this.currency)) {
                throw new RuntimeException("Different currency");
            }
            return new Money(this.amount + other.amount, this.currency);
        }
    }

    public record CreateUserRequest(String name, String email) { }
    //microservice request

//    @RequestMapping("/users")
//    public class UserController {
//
//        @PostMapping
//        public String createUser(@RequestBody CreateUserRequest req) {
//            return "User created: " + req.name() + " (" + req.email() + ")";
//        }
//    }
public record OrderCreated(String orderId, String userId, double amount) { }
// as kafka message

}
