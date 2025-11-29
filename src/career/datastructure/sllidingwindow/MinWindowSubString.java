package career.datastructure.sllidingwindow;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/minimum-window-substring/description/

 */
public class MinWindowSubString {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
        System.out.println(minWindow("a","aa"));
    }
    public static String minWindow(String s, String t) {
        int freqT[] = new int[126];

        for(char c:t.toCharArray()){
            freqT[c-'A']++;
        }
        int start=0;
        int length = t.length();
        int minWinSize =Integer.MAX_VALUE;
        int startI=0;
        for(int end=0;end<s.length();end++){
            int indexE = s.charAt(end)-'A';
            if(freqT[indexE] > 0){
                length--;
            }
            freqT[indexE]--;
            while(length == 0){
                int currWinSize = end-start+1;
                if(minWinSize > currWinSize){
                    minWinSize = currWinSize;
                    startI=start;
                }
                int indexS = s.charAt(start)-'A';
                freqT[indexS]++;
                if(freqT[indexS] > 0){
                    length++;
                }
                start++;
            }
        }

        return minWinSize == Integer.MAX_VALUE ? "": s.substring(startI,startI+minWinSize);
    }

    public static String minWindow2(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        // 1. Build frequency map for t
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int required = need.size();   // number of distinct chars in t
        int formed = 0;               // how many distinct chars in window match freq in t

        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // If this char is needed AND we just met the exact required count
            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            // Try to shrink from the left while window is valid
            while (left <= right && formed == required) {
                int currLen = right - left + 1;
                if (currLen < minLen) {
                    minLen = currLen;
                    minStart = left;
                }

                char cLeft = s.charAt(left);
                window.put(cLeft, window.get(cLeft) - 1);

                // If this was a needed char and we dropped below its required count,
                // window is no longer valid
                if (need.containsKey(cLeft) && window.get(cLeft).intValue() < need.get(cLeft).intValue()) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
