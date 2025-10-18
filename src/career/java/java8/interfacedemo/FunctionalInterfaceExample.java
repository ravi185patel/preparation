package career.java.java8.interfacedemo;

@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Using a lambda expression to implement the Greeting interface
        Greeting greeting = (name) -> System.out.println("Hello, " + name);
        
        // Calling the abstract method
        greeting.sayHello("John");  // Output: Hello, John
    }
}
