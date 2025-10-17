package career.java.java8.streams;

import com.career.common.Department;
import com.career.common.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
public static <T,K,V> Collector<T,?,Map<K,V>> toMap(
    Function<? super T, ? extends K> keyMapper,
    Function<? super T, ? extends V> valueMapper)
 */
public class ToMapDemo {
    public static void main(String[] args) {
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

        // Convert List of Employees to Map (ID -> Employee Name)
        Map<String, Department> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getName, Employee::getDepartment));

        System.out.println(employeeMap);

        // Convert List of Employees to Map (Department -> Employee Name)
        // If the department has multiple employees, concatenate the names with a comma
        Map<Department, String> departmentMap = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment, // Key: Department
                        Employee::getName, // Value: Employee Name
                        (name1, name2) -> name1 + ", " + name2 // Merge function to handle duplicate departments
                ));

        System.out.println(departmentMap);


        // Convert List of Employees to Map (ID -> Employee Object)
        Map<String, Employee> employeeMap1 = employees.stream()
                .collect(Collectors.toMap(Employee::getName, e -> e));

        System.out.println(employeeMap1);

    }
}
