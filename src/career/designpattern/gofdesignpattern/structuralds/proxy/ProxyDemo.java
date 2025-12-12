package career.designpattern.gofdesignpattern.structuralds.proxy;

interface Entity{
    void filter();
}

class PersonFilter implements  Entity{

    @Override
    public void filter() {
        System.out.println("PersonFilter");
    }
}

class EmployeeFilter implements Entity{
    Entity entity;


    public EmployeeFilter(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void filter() {
        entity.filter();
        System.out.println("Employee Filter");
    }
}



public class ProxyDemo {
    public static void main(String[] args) {
        Entity person = new PersonFilter();
        Entity employee = new EmployeeFilter(person);
        employee.filter();
    }
}
