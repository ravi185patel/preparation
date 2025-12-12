package career.designpattern.gofdesignpattern.structuralds.composite;


import java.util.ArrayList;
import java.util.List;

interface Employee{
    String getName();
    String getDesignation();
}

class Developer implements Employee{
    String name;
    String designation;

    public Developer(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesignation() {
        return this.designation;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}

class Manager implements Employee{

    String name;
    String designation;

    public Manager(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesignation() {
        return this.designation;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}

class Company implements Employee{

    List<Employee> employeeList;

    public Company() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(Employee employee){
        employeeList.remove(employee);
    }

    @Override
    public String getName() {
        for(Employee employee: employeeList){
            System.out.println(employee.toString());
        }
        return null;
    }

    @Override
    public String getDesignation() {
        for(Employee employee: employeeList){
            System.out.println(employee.toString());
        }
        return null;
    }
}

public class CompositeDemo {

    public static void main(String[] args) {
        Employee developer =new Developer("ravi","developer");
        Employee tester =new Developer("ravi1","Tester");
        Employee manager = new Manager("xyz","manager");

        Company company = new Company();
        company.addEmployee(developer);
        company.addEmployee(tester);
        company.addEmployee(manager);

        company.getName();
    }
}
