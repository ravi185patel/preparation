package career.common;

// new Student("Rahul", "Sharma", "Hyderabad", 8.38, 19, "Civil"),
public class Student {
    String firstName, lastName, city, department;
    double grade;
    int age;

    public Student(String firstName, String lastName, String city, double grade, int age, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.grade = grade;
        this.age = age;
        this.department = department;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + city + ", " + grade + ", " + age + ", " + department + ")";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}