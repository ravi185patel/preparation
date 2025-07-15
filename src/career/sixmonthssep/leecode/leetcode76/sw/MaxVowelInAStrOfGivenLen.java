package career.sixmonthssep.leetcode76.sw;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaxVowelInAStrOfGivenLen {
    public static void main(String[] args) {
       String  s = "abciiidef"; int k = 3;
       System.out.println(maxVowels(s,k));
    }

    public static int maxVowels(String s, int k) {
        Set<Character> set = new HashSet<>();
        set.add('a');set.add('e');set.add('i');set.add('o');set.add('u');

        int max =0;
        int countVowels = 0,start=0;
        for(int end=0;end<s.length();end++){
            char c= s.charAt(end);
            if(set.contains(c)){
                countVowels++;
            }
            while(end-start+1 > k){
                char ch= s.charAt(start);
                if(set.contains(ch)){
                    countVowels--;
                }
                start++;
            }
            max = Math.max(max,countVowels);
        }
        return max;
    }
}
