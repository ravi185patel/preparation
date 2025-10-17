package career.java.java8.streams;

import com.career.common.Department;
import com.career.common.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingByDemo {
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


        // group by department
        Map<Department,List<Employee>> departmentWiseEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(departmentWiseEmployees);
        // group by department and return department name -> employee
        Map<String, List<Employee>> employeesByDepartmentName = employees.stream()
                .collect(Collectors.groupingBy(emp -> emp.getDepartment().getName()));
        System.out.println(employeesByDepartmentName);
        Map<String, List<String>> employeeNamesByDepartmentName = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        System.out.println(employeesByDepartmentName);

        // Group employees by department and find second-highest salary employee in each department
        Map<Department, Optional<Employee>> secondHighestSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))  // Sort by salary descending
                                        .skip(1)  // Skip the highest salary (first in the sorted list)
                                        .findFirst()  // Get the second-highest salary
                        )
                ));
        System.out.println(secondHighestSalaryByDepartment);
//        How would you get a Map with a list of employee names, grouped by department, and sorted by name within each department?
//        1] grouing by department first.
        // 2] applied sorting on each employee name

        Map<String,List<String>> employeeNamesWithDepartment = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getName(),
                        Collectors.mapping(Employee::getName,Collectors.collectingAndThen(Collectors.toList(),list -> {
                            list.sort(String::compareTo);  // Sorting by name
                            return list;
                        }))));

        System.out.println(employeeNamesWithDepartment);

        Map<String, Long> employeeCountByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.counting()
                ));

        employeeCountByDepartment.forEach((department, count) ->
                System.out.println(department + ": " + count));
    }

}
