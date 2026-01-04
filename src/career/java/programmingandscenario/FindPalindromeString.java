package career.java.programmingandscenario;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindPalindromeString {
    public static void main(String[] args) {
        List<String> palindromeNames = Arrays.asList("Telugu", "Tamil",
                "Malayalam");

        List<String> findPalindromeNames= palindromeNames.stream()
                .filter((str) -> str.toLowerCase().compareTo(new StringBuilder(str).reverse().toString().toLowerCase()) ==0)
                .collect(Collectors.toList());
        System.out.println(findPalindromeNames);

    }
}
