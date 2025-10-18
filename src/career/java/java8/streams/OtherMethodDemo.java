package career.java.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OtherMethodDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(evenSquares);  // Output: [4, 16]
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);  // Output: 15

        Optional<Integer> sum1 = numbers.stream().reduce((a, b) -> a + b);
        sum1.ifPresent(System.out::println);


        Optional<Integer> first = numbers.stream().findFirst();
        Optional<Integer> any = numbers.stream().findAny();

        System.out.println(numbers.stream().allMatch(n -> n > 0));  // true
        System.out.println(numbers.stream().anyMatch(n -> n == 3));  // true
        System.out.println(numbers.stream().noneMatch(n -> n > 5));  // true


        /*
        Explanation:

forEach() performs an action for each element in the stream. The order of processing is not guaranteed, especially when the stream is parallel.

forEachOrdered() guarantees that elements are processed in the order they appear in the stream, regardless of whether the stream is parallel or not.
         */

// forEach
        numbers.stream().forEach(n -> System.out.print(n + " "));  // Output order not guaranteed
        System.out.println("-------------------------------------");
// forEachOrdered
        numbers.parallelStream().forEachOrdered(n -> System.out.print(n + " "));
    }
}
