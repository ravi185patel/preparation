package career.java.java11;

public class StringFeatures {
    public static void main(String[] args) {
        String str = "  Java  ";

        // strip() - removes leading and trailing whitespaces (Unicode-aware)
        System.out.println(str.strip()); // "Java"

        // isBlank() - true if the string is empty or contains only whitespaces
        System.out.println("   ".isBlank()); // true

        // lines() - splits a string into lines (stream)
        String multiline = "Line1\nLine2\nLine3";
        multiline.lines().forEach(System.out::println);

        // repeat(n) - repeats the string n times
        System.out.println("Hi ".repeat(3)); // "Hi Hi Hi "
    }
}
