package career.java.java8.interfacedemo;

interface Animal {
    // Abstract method (does not have a body)
    void sound();

    // Default method with a body
    default void eat() {
        System.out.println("This animal is eating.");
    }

    // Default method with a body
    default void sleep() {
        System.out.println("This animal is sleeping.");
    }
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Woof!");
    }
}

public class DefaultMethodExample {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound();  // Output: Woof!
        dog.eat();    // Output: This animal is eating.
        dog.sleep();  // Output: This animal is sleeping.
    }
}
