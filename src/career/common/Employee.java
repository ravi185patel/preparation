package career.common;

import java.util.List;

public class Employee {
    String name;
    double salary;
    Department department;
    List<String> skills;

    public Employee(String name, double salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return name + " (" + salary + ")";
    }

    public Employee(String name, List<String> skills) {
        this.name = name;
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

}