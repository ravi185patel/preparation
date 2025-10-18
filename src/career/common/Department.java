package career.common;

public class Department {
    String name;
    String location;

    public Department(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}
