package career.java.programmingandscenario;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicartesWithoutDistinct {
    public static void main(String[] args) {
        List<Integer> duplicateNumbers = Arrays.asList(1, 2, 3, 2, 4, 3, 5, 1);
        Set<Integer> set = new HashSet<>();
        List<Integer> lst = duplicateNumbers.stream().distinct().collect(Collectors.toList());
        lst = duplicateNumbers.stream().filter((elem)->set.add(elem)).collect(Collectors.toList());

    }
}
