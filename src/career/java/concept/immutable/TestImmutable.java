package career.java.concept.immutable;

public class TestImmutable {
    public static void main(String[] args) {
        Address address = new Address("Delhi", "110001");
        Employee e = new Employee("John", 30, address);

        address.setCity("Mumbai");    // change external object

        System.out.println(e.getAddress().getCity()); // Still Delhi ✔
    }
}
