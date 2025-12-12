package career.java.concept.immutable;

public final class Employee {

    private final String name;
    private final int age;
    private final Address address;   // mutable object

    public Employee(String name, int age, Address address) {
        this.name = name;
        this.age = age;

        // deep copy of mutable object
        this.address = new Address(address.getCity(), address.getPin());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // return deep copy to protect internal state
    public Address getAddress() {
        return new Address(address.getCity(), address.getPin());
    }
}
