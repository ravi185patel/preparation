package career.java.programmingandscenario;

import java.util.Arrays;
import java.util.List;

public class MatchAnyAllConcept {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        boolean isAnyEven = numbers.stream().anyMatch((i) -> i%2==0);
        boolean isAllEven = numbers.stream().allMatch((i) -> i%2==0);
        boolean isAllOdd = numbers.stream().noneMatch((i) -> i%2==0);

        System.out.println(isAnyEven+" "+isAllEven+" "+isAllOdd);
    }
}
