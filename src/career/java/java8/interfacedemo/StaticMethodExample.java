package career.java.java8.interfacedemo;

interface Calculator {
    // Static method
    static int add(int a, int b) {
        return a + b;
    }

    // Abstract method
    int subtract(int a, int b);
}

class SimpleCalculator implements Calculator {
    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}

public class StaticMethodExample {
    public static void main(String[] args) {
        // Calling static method from the interface
        int sum = Calculator.add(10, 20);
        System.out.println("Sum: " + sum);  // Output: Sum: 30

        // Using the class method (subtract)
        SimpleCalculator calc = new SimpleCalculator();
        System.out.println("Difference: " + calc.subtract(20, 10));  // Output: Difference: 10
    }
}
