package career.sixmonthssep.sw.substring;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
 */
public class LongestSubStrWithAtLeastKRepChar {
    public static void main(String[] args) {
        System.out.println(longestSubstring("aaabb",3));
        System.out.println(longestSubstring("ababbc",2));
        System.out.println(longestSubstring("aaaaa",3));
        System.out.println(longestSubstring("bbaaacbd",3));
    }
    public static int longestSubstring(String s, int k) {
        if(s==null || s.length() == 0 || k == 0) return 0;

        int length = s.length(),start=0,maxLength=0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<length;i++){
            char c= s.charAt(i);
            if(map.getOrDefault(c,0) >= k){
                maxLength = Math.max(maxLength, i - start + 1);
            }
            map.put(c,map.getOrDefault(c,0)+1);
            if(isAllAboveK(map,k)) {
                maxLength = Math.max(maxLength, i - start + 1);
            }
        }

        return maxLength;
    }

    public static boolean isAllAboveK(Map<Character,Integer>map,int k){
        for(Character key:map.keySet()){
            if(map.getOrDefault(key,0) < k){
                return false;
            }
        }
        return true;
    }
}
