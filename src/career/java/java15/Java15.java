package career.java.java15;

/*
⭐ Java 15 – Key Features (Clear + Interview Friendly)

Java 15 introduced:
Sealed Classes (Preview) ⭐ (Most important)
Text Blocks (Final)
Hidden Classes
ZGC improvements
Edwards-Curve key agreement (security)

Let’s focus on the main ones used in interviews.

-----------------------------------------------------------------------------------
Sealed classes let you control which classes can extend or implement a class or interface.
Why this is important?

✔ Helps create more secure, maintainable, domain-specific hierarchies
✔ Perfect for domain modeling (banking, rules engine, workflow states)
✔ Works great with pattern matching (Java 17+)

🎯 Why Sealed Classes exist?

To restrict inheritance
To enforce closed hierarchies
To make pattern matching exhaustive and safe
To prevent unwanted extension → cleaner architecture

public sealed interface PaymentStatus
    permits Success, Failed, Pending {}

public sealed interface PaymentStatus
        permits Success, Failed, Pending {}

public final class Success implements PaymentStatus {}
public final class Failed implements PaymentStatus {}
public final class Pending implements PaymentStatus {}


Now the system knows exactly all possible states.

Great for:
Workflow engines
State machines
Policies
Rules engines


🔥 3. Hidden Classes

Hidden classes are used by frameworks (Spring, Hibernate, ByteBuddy) to generate runtime proxy classes.
You don’t use them directly, but interviewers may ask:
Interview answer:
“Hidden classes are classes that cannot be referenced directly by code.
Frameworks use them to generate proxies at runtime for reflection, lambdas, and bytecode libraries.”

🔥 4. ZGC Improvements

Z Garbage Collector officially becomes stronger with:
Concurrent class unloading
Reduced memory footprint
Better performance cross-platform
Simple interview answer:
“Java 15 improved ZGC to support more concurrent operations and reduce memory usage.”

“Java 15 introduced sealed classes so developers can control which classes may extend a class or implement an interface.
It finalized text blocks and introduced hidden classes for framework runtime code generation.
ZGC also got performance improvements.”

“Hidden classes are special runtime-generated classes that cannot be referenced by normal code.
They are used by frameworks to create proxies and lambda implementations
without polluting the main class namespace.”

Runnable r = () -> System.out.println("Hi");

2. Spring AOP Proxy
@Service
class UserService { ... }

Spring creates proxy classes at runtime.

Hidden classes help avoid:
Memory leak
Class loader pollution
Extra classes appearing in debugging

3. Hibernate bytecode enhancement

Hibernate enhances entities at runtime.
Hidden classes allow it to generate transient helper classes.
 */

public class Java15 {

}
