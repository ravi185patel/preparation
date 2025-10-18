package career.java.java8.streams;

import com.career.common.Department;
import com.career.common.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
Common Collectors in Java:

Collectors.toList() – Collects the elements into a List.

Collectors.toSet() – Collects the elements into a Set.

Collectors.toMap() – Collects the elements into a Map.

Collectors.joining() – Concatenates the elements into a single string.

Collectors.groupingBy() – Groups elements by a classifier function.

Collectors.partitioningBy() – Partitions elements based on a predicate.

Collectors.counting() – Counts the number of elements.

Collectors.summingInt() – Computes the sum of the elements (using an int).

Collectors.averagingInt() – Computes the average of the elements (using an int).

Collectors.reducing() – Performs a reduction (e.g., summing, multiplying, etc.).
 */
public class CollectToDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Collecting the stream elements into a List
        List<Integer> collectedList = numbers.stream()
                .collect(Collectors.toList());

        System.out.println(collectedList);  // Output: [1, 2, 3, 4, 5]

        // Collecting the stream elements into a Set (removes duplicates)
        Set<Integer> collectedSet = numbers.stream()
                .collect(Collectors.toSet());

        System.out.println(collectedSet);  // Output: [1, 2, 3, 4, 5]

        // Create some departments
        Department itDept = new Department("IT", "New York");
        Department hrDept = new Department("HR", "San Francisco");
        Department financeDept = new Department("Finance", "London");

        // Create some employees
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 75000, itDept),
                new Employee("Bob", 60000, hrDept),
                new Employee("Charlie", 80000, itDept),
                new Employee("David", 95000, financeDept),
                new Employee("Eve", 85000, itDept),
                new Employee("Frank", 67000, hrDept)
        );

        // Collecting employees into a Map (id -> Employee)
        Map<String, Double> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getName, Employee::getSalary));

        System.out.println(employeeMap);  // Output: {1=Alice, 2=Bob, 3=Charlie}

        List<String> words = Arrays.asList("Java", "Streams", "are", "fun");

        // Joining the elements into a single string with a space delimiter
        String sentence = words.stream()
                .collect(Collectors.joining(" "));

        System.out.println(sentence);  // Output: Java Streams are fun
    }
}
