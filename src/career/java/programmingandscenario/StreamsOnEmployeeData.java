package career.java.programmingandscenario;

import java.util.*;
import java.util.stream.Collectors;

record EmployeeDto(
        String name,
        String department,
        int age,
        String gender
) { }

//1.Find the names of all Employees in the CS department, sorted by age in descending order
//2. Group Employees by department and count how many Employees are in each department
//3.Find the youngest female Employee.
//4. Create a map of department -> list of Employee names.
//5. Find the average age of Employees in each department.
//6. Get a list of unique departments Employees belong to
//7. Partition Employees into male and female groups, then list their names.
//8. Group employees by department, then within each department find the oldest employee
//9. Build a map of gender with average age of employees sorted by average age descending
//10. For each department, find the youngest employee, but instead of returning the employee object,
//return only their name in uppercase.
//11. Return a map where keys will be first letter of the name and value will the set of names starting with
//that letter, no solution provided, try on your own

public class StreamsOnEmployeeData {
    public static void main(String[] args) {
        EmployeeDto employee1 = new EmployeeDto("SRK","ECE",31,"Male");
        EmployeeDto employee2 = new EmployeeDto("Salman","CS",44,"Male");
        EmployeeDto employee3 = new EmployeeDto("Katrina","ECE",21,"Female");
        EmployeeDto employee4 = new EmployeeDto("Kareena","CS",34,"Female");
        EmployeeDto employee5 = new EmployeeDto("Hrithik","EEE",30,"Male");
        EmployeeDto employee6 = new EmployeeDto("Aish","EEE",25,"Female");

        List<EmployeeDto> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
        list.add(employee4);
        list.add(employee5);
        list.add(employee6);

        //1.Find the names of all Employees in the CS department, sorted by age in descending order

        List<String> csSortedEmployeeDtos = list.stream().filter(employeeDto -> employeeDto.department().equalsIgnoreCase("CS"))
                .sorted(Comparator.comparing(employeeDto -> -1*employeeDto.age()))
                .map(employeeDto -> employeeDto.name())
                .collect(Collectors.toList());

        System.out.println(csSortedEmployeeDtos);


        //2. Group Employees by department and count how many Employees are in each department
        Map<String,Long> deptWiseEmpCount =  list.stream().collect(Collectors.groupingBy(employeeDto -> employeeDto.department(),Collectors.counting()));
        System.out.println(deptWiseEmpCount);


//3.Find the youngest female Employee.
        Optional<EmployeeDto> youngestFemaleEmployee =  list.stream()
                .filter(employeeDto ->  employeeDto.gender().equalsIgnoreCase("female"))
                .min(Comparator.comparing(employeeDto -> employeeDto.age()));
        System.out.println(youngestFemaleEmployee.orElse(null));

//4. Create a map of department -> list of Employee names.
        Map<String,List<EmployeeDto>> deptWiseEmpList =  list.stream().collect(Collectors.groupingBy(employeeDto -> employeeDto.department(),Collectors.toList()));
        System.out.println(deptWiseEmpList);
        Map<String,List<String>> deptWiseEmpNameList =  list.stream().collect(Collectors.groupingBy(employeeDto -> employeeDto.department(),
                Collectors.mapping(EmployeeDto::name,Collectors.toList())));
        System.out.println(deptWiseEmpNameList);


//5. Find the average age of Employees in each department.
        Map<String,Double> avgEmpAgeDeptWise =  list.stream().collect(Collectors.groupingBy(EmployeeDto::department,Collectors.averagingDouble(EmployeeDto::age)));
        System.out.println(avgEmpAgeDeptWise);

//6. Get a list of unique departments Employees belong to

//7. Partition Employees into male and female groups, then list their names.
        Map<String,List<String>> empNamesGenderWise = list.stream()
                .collect(Collectors.groupingBy(EmployeeDto::gender,Collectors.mapping(EmployeeDto::name,Collectors.toList())));
        System.out.println(empNamesGenderWise);

//8. Group employees by department, then within each department find the oldest employee
        Map<String,Optional<EmployeeDto>> oldEmpDeptWise = list.stream()
                .collect(Collectors.groupingBy(EmployeeDto::department,Collectors.maxBy(Comparator.comparing(EmployeeDto::age))));

//9. Build a map of gender with average age of employees sorted by average age descending
        Map<String, Double> mapOfGender = list
                .stream().collect(Collectors.groupingBy(EmployeeDto::gender, Collectors.averagingDouble(EmployeeDto::age)));
        System.out.println("Map of Gender: " + mapOfGender);

//10. For each department, find the youngest employee, but instead of returning the employee object,
//return only their name in uppercase.
        Map<String, String> youngestByDept = list.stream()
                .collect(Collectors.groupingBy(
                        EmployeeDto::department,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(EmployeeDto::age)),
                                e -> e.get().name().toUpperCase()
                        )
                ));
//11. Return a map where keys will be first letter of the name and value will the set of names starting with
//that letter, no solution provided, try on your own

    }
}
