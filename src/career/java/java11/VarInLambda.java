package career.java.java11;

import java.util.List;

public class VarInLambda {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        // Lambda expression using var
        names.forEach((var name) -> System.out.println(name));
    }
}
