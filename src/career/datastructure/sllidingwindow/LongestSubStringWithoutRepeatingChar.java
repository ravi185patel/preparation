package career.datastructure.sllidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubStringWithoutRepeatingChar {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;

        int length=s.length();
        int maxLength=0,start=0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<length;i++){
            char c= s.charAt(i);
            if(map.containsKey(c)){
                start = Math.max(map.get(c)+1,start);
            }
            map.put(c,i);
            maxLength = Math.max(maxLength,i-start+1);
        }

        return maxLength;
    }
}
