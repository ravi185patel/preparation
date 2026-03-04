package career.java.java8.streams;

import career.common.Department;
import career.common.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingByDemo {
    public static void main(String[] args) {

        // Create some departments
        Department itDept = new Department("IT", "New York");
        Department hrDept = new Department("HR", "San Francisco");
        Department financeDept = new Department("Finance", "London");
        System.out.println(itDept instanceof Department);

        // Create some employees
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 75000, itDept,Arrays.asList(itDept,financeDept)),
                new Employee("Bob", 60000, hrDept,Arrays.asList(itDept,hrDept)),
                new Employee("Charlie", 80000, itDept,Arrays.asList(hrDept,financeDept)),
                new Employee("David", 95000, financeDept,Arrays.asList(itDept,financeDept)),
                new Employee("Eve", 85000, itDept,Arrays.asList(itDept,hrDept)),
                new Employee("Frank", 67000, hrDept,Arrays.asList(hrDept,financeDept))
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


        Map<String,List<String>> departmentEmployeeMap =
                employees.stream().flatMap(employee ->
                        employee.getDepartments()
                                .stream()
                                .map(department ->
                                        Map.entry(department.getName(),employee.getName()))
                        )
                                .collect(
                                        Collectors.groupingBy(
                                                Map.Entry::getKey,
                                                Collectors.mapping(Map.Entry::getValue,
                                                        Collectors.toList()))
                                );

        System.out.println(departmentEmployeeMap);


        List<Map.Entry<String, Double>> departmentEmplyeeName=
        employees.stream().flatMap(
                employee -> employee.getDepartments().stream().map(department -> Map.entry(department.getName(),employee.getSalary()))
        ).collect(Collectors.toList());

        System.out.println(departmentEmplyeeName);

        Map<String, Double> departmentMaxSalary =
                employees.stream()
                        .flatMap(emp ->
                                emp.getDepartments().stream()
                                        .map(dept -> new AbstractMap.SimpleEntry<>(
                                                dept.getName(),
                                                emp.getSalary()
                                        ))
                        )
                        .collect(Collectors.groupingBy(
                                entry -> entry.getKey(),
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparing(
                                                        entry -> entry.getValue()
                                                )
                                        ),
                                        opt -> opt.map(e -> e.getValue())
                                                .orElse(0.0)
                                )
                        ));
        System.out.println(departmentMaxSalary);
        departmentMaxSalary =
                employees.stream()
                        .flatMap(emp ->
                                emp.getDepartments().stream()
                                        .map(dept -> new AbstractMap.SimpleEntry<>(
                                                dept.getName(),
                                                emp.getSalary()
                                        ))
                        )
                        .collect(Collectors.groupingBy(
                                entry -> entry.getKey(),
                                Collectors.reducing(
                                        0.0,
                                        entry -> entry.getValue(),
                                        Double::max
                                )
                        ));
        System.out.println(departmentMaxSalary);
        departmentMaxSalary =
                employees.stream()
                        .flatMap(emp ->
                                emp.getDepartments().stream()
                                        .map(dept -> new AbstractMap.SimpleEntry<>(
                                                dept.getName(),
                                                emp.getSalary()
                                        ))
                        )
                        .collect(Collectors.groupingBy(
                                entry -> entry.getKey(),
                                Collectors.collectingAndThen(
                                        Collectors.mapping(
                                                entry -> entry.getValue(),
                                                Collectors.toList()
                                        ),
                                        list -> list.stream()
                                                .distinct()
                                                .sorted(Comparator.reverseOrder())
                                                .skip(2)
                                                .findFirst()
                                                .orElse(0.0)
                                )
                        ));
        System.out.println(departmentMaxSalary);

        departmentMaxSalary =
                employees.stream()
                        .flatMap(emp ->
                                emp.getDepartments().stream()
                                        .map(dept -> new AbstractMap.SimpleEntry<>(
                                                dept.getName(),
                                                emp.getSalary()
                                        ))
                        )
                        .collect(Collectors.groupingBy(
                                entry -> entry.getKey(),
                                Collectors.collectingAndThen(
                                        Collectors.mapping(
                                                entry -> entry.getValue(),
                                                Collectors.toList()
                                        ),
                                        list -> list.stream()
                                                .distinct()
                                                .sorted(Comparator.reverseOrder())
                                                .skip(1)
                                                .findFirst()
                                                .orElse(0.0)
                                )
                        ));
        System.out.println(departmentMaxSalary);
    }

}
