package career.java.programmingandscenario;

import career.common.Student;

import java.util.*;
import java.util.stream.Collectors;

/*
1. Find students from Hyderabad with a grade greater than 8.0
2. Find the student with the highest grade
3. Count the number of students in each department
4. Find the average grade per department
5. List students sorted by age and then by grade
6. Create a comma-separated list of student names
7. Check if all students are above 18
8. Find the department with the most students
9. Divide students into those who have grades above 8.0 and below
10. Find the student with the longest full name
 */
public class StudentData {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Rahul", "Sharma", "Hyderabad", 8.38, 19, "Civil"),
                new Student("Amit", "Verma", "Delhi", 8.4, 21, "IT"),
                new Student("Suresh", "Reddy", "Chennai", 7.5, 20, "Civil"),
                new Student("Kiran", "Patel", "Mumbai", 9.1, 20, "IT"),
                new Student("Arjun", "Naidu", "Bengaluru", 7.83, 20, "Civil")
        );

        List<Student> HyderabadHighGrades = students.stream()
                .filter(student -> student.getCity().equalsIgnoreCase("Hyderabad")
                        && student.getGrade() > 8.0 ).collect(Collectors.toList());
        System.out.println(HyderabadHighGrades);

        Optional<Student> topStudent = students.stream()
                .sorted(Comparator.comparing(student -> -1*student.getGrade()))
                        .findFirst();
        System.out.println(topStudent.orElse(null));

        Map<String,Long> departmentCount=students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment,Collectors.counting()));
        System.out.println(departmentCount);

        Map<String,Double> avgGradeByDept =students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment,Collectors.averagingDouble(s->s.getGrade())));
        System.out.println(avgGradeByDept );

        List<Student> studentList = students.stream()
                .sorted(Comparator.comparingInt((Student s) -> s.getAge()).thenComparingDouble(s -> s.getGrade()))
                .collect(Collectors.toList());
        System.out.println(studentList);

        // 6. Create a comma-separated list of student names
        String names = students.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
        System.out.println("6. Comma-separated student names: " + names);

        // 7. Check if all students are above 18
        boolean allAbove18 = students.stream().allMatch(s -> s.getAge() > 18);
        System.out.println("7. Are all students above 18? " + allAbove18);

        Map<Boolean,List<Student>> map =
                students.stream().collect(Collectors.partitioningBy(student->student.getGrade() > 8.0,Collectors.toList()));

        System.out.println(map);

        // 10. Find the student with the longest full name
        Optional<Student> longestNameStudent = students.stream()
                .max(Comparator.comparingInt(s -> (s.getFirstName() + s.getLastName()).length()));
        System.out.println("10. Student with the longest full name: " + longestNameStudent.orElse(null));

        String mostPopularDept = students.stream()
                .collect(Collectors.groupingBy(student -> student.getDepartment(),Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");

        System.out.println("8. Department with most students: " + mostPopularDept);


    }
}
