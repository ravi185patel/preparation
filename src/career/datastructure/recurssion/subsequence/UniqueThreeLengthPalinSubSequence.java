package career.datastructure.recurssion.subsequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*

https://leetcode.com/problems/unique-length-3-palindromic-subsequences/description/?envType=daily-question&envId=2025-01-04

1] bruteforce approach
i] find leftmost and rightmost index of character
 -> count distinct character between leftmost and rightmost
 -> do same for all characters from left to right.

 */
public class UniqueThreeLengthPalinSubSequence {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequence("aabca"));
        System.out.println(countPalindromicSubsequence("adc"));
    }
//    public static int countPalindromicSubsequence(String s) {
//
//    }


    public static int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (first[curr] == - 1) {
                first[curr] = i;
            }

            last[curr] = i;
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) {
                continue;
            }

            Set<Character> between = new HashSet<>();
            for (int j = first[i] + 1; j < last[i]; j++) {
                between.add(s.charAt(j));
            }

            ans += between.size();
        }

        return ans;
    }

}
