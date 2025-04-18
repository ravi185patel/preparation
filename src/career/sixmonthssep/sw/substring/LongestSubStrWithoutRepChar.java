package career.sixmonthssep.sw.substring;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStrWithoutRepChar {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("eceab"));
        System.out.println(lengthOfLongestSubstring("pwwke"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;

        int length = s.length(),start=0,maxLength=0;
        Map<Character,Integer> map = new HashMap<>();


        for(int i=0;i<length;i++){
            char c= s.charAt(i);
            if(map.containsKey(c)){
                start = Math.max(map.get(c)+1,start);
            }
            map.put(c,i);
            maxLength = Math.max(maxLength,i-start + 1);
        }

        return maxLength;
    }
}
