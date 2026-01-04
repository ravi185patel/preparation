package career.java.programmingandscenario;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringOptUsingJava8 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("cat", "elephant", "dog", "giraffe", "zebra");
        Optional<String> word = words.stream().max(Comparator.comparing(String::length));
        System.out.println(word.get());

        List<String> strings = Arrays.asList("java scala ruby", "java react spring java");
        String word1 = "java";
        long count = strings.stream()
                .flatMap(str -> Arrays.stream(str.split(" "))) // when you split it will gives you array you need to conver into stream
                .filter(str->str.equals(word1)).count();
        System.out.println(count);

        List<String> uniqueWords = strings.stream()
                .flatMap(str -> Arrays.stream(str.split(" ")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueWords);

        String sentence = "Java is fun and java is powerful";
        Map<String,Long> frequencySplit = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(frequencySplit);

    }
}
