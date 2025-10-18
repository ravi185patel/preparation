package career.java.java21;

/*
What is it?

Virtual threads are lightweight threads managed by the JVM (not the OS),
allowing you to run thousands or millions of concurrent tasks without the usual overhead of traditional platform threads.

🔧 Why it matters:

Traditional (platform) threads are limited (~1000–10000 threads max).
Virtual threads are cheap to create and use minimal memory.
Ideal for handling high-concurrency applications like web servers or microservices.

🔹 Thread.startVirtualThread() creates a virtual thread.
🔹 These threads still behave like normal threads (Thread.sleep, try/catch, etc. all work).


| Feature           | Virtual Threads                        | Structured Concurrency                    |
| ----------------- | -------------------------------------- | ----------------------------------------- |
| ✅ Type            | Low-level concurrency model            | High-level API for structured parallelism |
| 🔧 Purpose        | Create millions of lightweight threads | Manage multiple tasks as one logical unit |
| 🔄 Error Handling | Manual                                 | Automatic failure propagation             |
| 💡 Best For       | I/O-heavy or reactive tasks            | Parallel tasks with clear lifecycle       |

When to Use What?

Virtual Threads → You want to write simple, blocking-style code that scales (e.g., replacing complex async code).
Structured Concurrency → You’re launching multiple dependent tasks (e.g., fetch user + fetch orders) and want cleaner control.
 */
public class VirtualThreadExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Running in thread: " + Thread.currentThread());
        };

        // Virtual thread
//        Thread.startVirtualThread(task);
        
        // Traditional thread (for comparison)
        new Thread(task).start();
    }
}
