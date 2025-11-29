package career.datastructure.sllidingwindow;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/

 */
public class MaxNoOfVowelsInSubStringOfGivenLength {
    public static void main(String[] args) {
        // fixed window length k (max vowels)
        System.out.println(maxVowels("abciiidef",3));
    }
    public static int maxVowels(String s, int k) {
        Set<Character> set = new HashSet<>();
        set.add('a');set.add('e');set.add('i');set.add('o');set.add('u');

        int max =0;
        int countVowels = 0,j=0;
        for(int i=0;i<s.length();i++){
            char c= s.charAt(i);
            if(set.contains(c)){
                countVowels++;
            }
            while(i-j+1 > k){
                char ch= s.charAt(j);
                if(set.contains(ch)){
                    countVowels--;
                }
                j++;
            }
            max = Math.max(max,countVowels);
        }
        return max;
    }

}
