package career.datastructure.sllidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
 */
public class LongestSubStringWithKDistinctChar {
    public static void main(String[] args) {
        System.out.println(longestKSubstr("aabacbebebe",3));
        System.out.println(longestKSubstr("aabaaab",2));
        System.out.println(longestKSubstr("aaaa",2));
    }

    public static int longestKSubstr(String s, int k) {
        Map<Character,Integer> map = new HashMap<>();
        int left=0,length=0;
        for(int right =0;right<s.length();right++){

            map.put(s.charAt(right),map.getOrDefault(s.charAt(right),0)+1);
            while(map.size() > k){

//                length = Math.max(length, right - left + 1);
                map.put(s.charAt(left),map.getOrDefault(s.charAt(left),0)-1);
                if(map.get(s.charAt(left)) <= 0){
                    map.remove(s.charAt(left));
                }
                left++;
            }
            if(map.size() ==k ){ // execute even there is no distinct character if there is no if condition
                length = Math.max(length, right - left + 1);
            }
        }
        return length == 0 ? -1: length;
    }


}
