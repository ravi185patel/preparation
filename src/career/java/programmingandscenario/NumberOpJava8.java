package career.java.programmingandscenario;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberOpJava8 {
    public static void main(String[] args) {
        int i = 123456;
        Integer sumOfDigits= Arrays.stream(String.valueOf(i).split(""))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println(sumOfDigits);

        sumOfDigits= Arrays.stream(String.valueOf(i).split(""))
                .collect((Collectors
                .summingInt(Integer::parseInt)));
        System.out.println(sumOfDigits);
    }
}
