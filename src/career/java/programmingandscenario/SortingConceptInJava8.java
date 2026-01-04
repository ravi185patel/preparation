package career.java.programmingandscenario;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortingConceptInJava8 {
    public static void main(String[] args) {
        List<Integer> duplicateNumbers = Arrays.asList(1, 2, 3, 2, 4, 3, 5, 1);
        List<Integer> lst = duplicateNumbers.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(lst);

        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL",
                "C");
        listOfStrings = listOfStrings.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
        System.out.println(listOfStrings);
        listOfStrings = listOfStrings.stream().sorted(Comparator.comparing((str) -> (-1)*str.length())).collect(Collectors.toList());
        System.out.println(listOfStrings);


        // non repeating character
        String input = "aabbcdeffg";
        String firstNonRepeatingChar = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);

        System.out.println(firstNonRepeatingChar);
    }
}
