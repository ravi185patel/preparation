package career.interview.priceline;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(""));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(lengthOfLongestSubstring(" "));
    }
    public static int lengthOfLongestSubstring(String s) {
//        return LSBf(s);
        return LSSW(s);
    }

    public static int LSBf(String s){
        int maxLength =0, n = s.length();
        char sCharArr[]=s.toCharArray();
        Set<Character> set=new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++) {
                if(set.contains(sCharArr[j])) {
                    break;
                }
                set.add(sCharArr[j]);
            }
            maxLength = Math.max(maxLength,set.size());
            set.clear();
        }
        return maxLength;
    }

    public static int LSSW(String s){
        int maxLength =0, n = s.length();
        char sCharArr[]=s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int j=0;
        for(int i=0;i<n;i++){
            map.put(sCharArr[i] ,map.getOrDefault(sCharArr[i],0)+1);
             while(j <= i && map.getOrDefault(sCharArr[i],0) > 1){
                 int count = map.get(sCharArr[j]);
                 if(count == 0){
                     map.remove(sCharArr[i]);
                 }
                 map.put(sCharArr[j],count-1);
                 j++;
             }
             maxLength = Math.max(maxLength,i-j+1);
        }
        return maxLength;
    }
}
