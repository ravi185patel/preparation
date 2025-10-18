package career.java.java8.streams;

import com.career.common.Department;
import com.career.common.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionByExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Partition numbers into even and odd groups
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Even numbers: " + partitioned.get(true));
        System.out.println("Odd numbers: " + partitioned.get(false));

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

        // Partition employees into those with salary above 60000 and below or equal to 60000
        Map<Boolean, List<Employee>> partitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 60000));

        System.out.println("Employees with salary above 60000: " + partitionedBySalary.get(true));
        System.out.println("Employees with salary below or equal to 60000: " + partitionedBySalary.get(false));

        // Partition employees by salary and collect their names
        Map<Boolean, List<String>> namesBySalaryPartition = employees.stream()
                .collect(Collectors.partitioningBy(
                        e -> e.getSalary() > 60000,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));

        System.out.println("Employees with salary above 60000: " + namesBySalaryPartition.get(true));
        System.out.println("Employees with salary below or equal to 60000: " + namesBySalaryPartition.get(false));

        // Partition employees by salary and count the number of employees in each partition
        Map<Boolean, Long> employeeCountBySalaryPartition = employees.stream()
                .collect(Collectors.partitioningBy(
                        e -> e.getSalary() > 60000,
                        Collectors.counting()
                ));

        System.out.println("Number of employees with salary above 60000: " + employeeCountBySalaryPartition.get(true));
        System.out.println("Number of employees with salary below or equal to 60000: " + employeeCountBySalaryPartition.get(false));
    }
}
