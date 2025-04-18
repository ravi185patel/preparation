package career.sixmonthssep.sw.substring;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
 */
public class LongestSubStrWithAtMostTwoDistinctChar {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("eceba",2));
        System.out.println(lengthOfLongestSubstring("ccaabbb",2));
    }

    public static int lengthOfLongestSubstring(String s,int k) {
        if(s==null || s.length()==0) return 0;

        int length = s.length(),start=0,maxLength=0;
        Map<Character,Integer> map = new HashMap<>();


        for(int i=0;i<length;i++){
            char c= s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
            if(map.size() > k){
                while(start <= i && map.size() > k){
                    c= s.charAt(start);
                    map.put(c,map.get(c)-1);
                    if(map.get(c) == 0){
                        map.remove(c);
                    }
                    start++;
                }
            }

            maxLength = Math.max(maxLength,i-start + 1);
        }

        return maxLength;
    }
}
