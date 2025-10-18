package career.java.java8.streams;

import java.util.*;
import java.util.stream.*;

public class CollectingAndThenExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Use collectingAndThen to collect elements into a list and make the list unmodifiable
        List<Integer> unmodifiableList = numbers.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),                // Downstream collector (collects to a List)
                Collections::unmodifiableList       // Finisher (makes the list unmodifiable)
            ));

        // This will throw an UnsupportedOperationException
        unmodifiableList.add(6);  // Uncomment this to see the exception

        // Collect to list and sort the result
        List<Integer> sortedList = numbers.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),          // Downstream: collect to a list
                        list -> {                     // Finisher: sort the list
                            list.sort(Comparator.naturalOrder());
                            return list;
                        }
                ));

        System.out.println(sortedList);  // Output: [1, 2, 3, 4, 5]

        List<String> words = Arrays.asList("apple", "banana", "apple", "orange");

        // Collect elements into a Set (eliminating duplicates) and then convert all strings to uppercase
        Set<String> uppercaseSet = words.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),                // Downstream: collect to a Set
                        set -> {                           // Finisher: convert all elements to uppercase
                            Set<String> uppercase = new HashSet<>();
                            for (String word : set) {
                                uppercase.add(word.toUpperCase());
                            }
                            return uppercase;
                        }
                ));

        System.out.println(uppercaseSet);  // Output: [APPLE, BANANA, ORANGE]
    }
}
