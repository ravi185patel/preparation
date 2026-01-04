package career.java.programmingandscenario;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    private int id;
    private String name;
    private List<String> departments;

    public Student(int id, String name, List<String> departments) {
        this.id = id;
        this.name = name;
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public List<String> getDepartments() {
        return departments;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Department {
    private String name;
    private List<Student> students;

    public Department(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    @Override
    public String toString() {
        return name + " -> " + students;
    }
}



public class ImpConceptsOfJava8 {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student(1, "Amit", List.of("CS", "IT")),
                new Student(2, "Neha", List.of("IT", "ECE")),
                new Student(3, "Rahul", List.of("CS")),
                new Student(4, "Sneha", List.of("ECE"))
        );

        Map<String, List<Student>> deptStudentMap =
                students.stream()
                        .flatMap(s ->
                                s.getDepartments().stream()
                                        .map(d -> Map.entry(d, s))
                        )
                        .collect(Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                        ));

        System.out.println(deptStudentMap);
        List<Map.Entry<String, Student>> deptStudent =  students.stream()
                .flatMap(s ->
                        s.getDepartments().stream()
                                .map(d -> Map.entry(d, s))
                ).collect(Collectors.toList());

        System.out.println(deptStudent);
    }
}
