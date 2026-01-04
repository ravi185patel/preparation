package career.java.programmingandscenario;

import java.util.Arrays;
import java.util.List;

public class ReduceFunctionExample {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("flower", "flow", "flight");
        String longestPrefix = strings.stream().reduce((s1,s2)->findLongestPrefix(s1,s2)).orElse("");
        System.out.println(longestPrefix);
    }

    public static String findLongestPrefix(String s1,String s2){
        int length = Math.min(s1.length(),s2.length());
        int commonIndex=0;
        while(commonIndex < length){
            if(s1.charAt(commonIndex) != s2.charAt(commonIndex)){
                break;
            }
            commonIndex++;
        }

        return s1.substring(0,commonIndex);
    }
}
