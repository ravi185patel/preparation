package career.java.java8.streams;

import com.career.common.Employee;

import java.util.*;
import java.util.stream.*;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<Integer>> listOfLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8)
        );

        // Flatten the list of lists into a single list
        List<Integer> flattened = listOfLists.stream()
            .flatMap(List::stream)  // Flatten each list into a stream of integers
            .collect(Collectors.toList());

        System.out.println(flattened);  // Output: [1, 2, 3, 4, 5, 6, 7, 8]

        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java Streams are powerful",
                "FlatMap makes it easy"
        );
        // Split each sentence into words and flatten into a single stream
        List<String> words = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))  // Split each sentence into words
                .collect(Collectors.toList());

        System.out.println(words);  // Output: [Hello, world, Java, Streams, are, powerful, FlatMap, makes, it, easy]

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", Arrays.asList("Java", "SQL", "Spring")),
                new Employee("Bob", Arrays.asList("Python", "Django")),
                new Employee("Charlie", Arrays.asList("JavaScript", "React", "Node.js"))
        );

        // Flatten the skills of all employees into a single list
        List<String> allSkills = employees.stream()
                .flatMap(employee -> employee.getSkills().stream())  // Flatten skills of each employee into a stream
                .collect(Collectors.toList());

        System.out.println(allSkills);  // Output: [Java, SQL, Spring, Python, Django, JavaScript, React, Node.js]

    }
}
