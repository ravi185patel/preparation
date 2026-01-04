package career.java.programmingandscenario;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindMaxNumberInList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 41, 4);

        int max = numbers.stream().max(Integer::compareTo).orElse(0);
        System.out.println("max : "+max);

        max = numbers.stream().max(Comparator.naturalOrder()).orElse(0);
        System.out.println("max : "+max);

    }
}
