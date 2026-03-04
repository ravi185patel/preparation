package career.interview.priceline;

import java.util.Arrays;

public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab","eidbaooo"));
        System.out.println(checkInclusion("ab","eidboaoo"));
        System.out.println(checkInclusion("abb","eidbaboo"));
        System.out.println(checkInclusion("abb","eidbacboo"));
        System.out.println(checkInclusion("hello","ooolleoooleh"));
    }
    public static boolean checkInclusion(String s1, String s2) {
        return isPer(s1,s2);
    }

    public static boolean isPer(String s1,String s2){
        int windowSize = s1.length();
        int freq[]=new int[26];
        for(char c:s1.toCharArray()){
            freq[c-'a']++;
        }
        int left =0;
        int freq1[] = new int[26];
        for(int right =0;right<s2.length();right++){
            char ch = s2.charAt(right);
            int  index = ch-'a';
            freq1[index]++;

            if(right-left+1 > windowSize) {
                char chL = s2.charAt(left);
                int indexL = chL - 'a';
                freq1[indexL]--;
                left++;
            }

            if(Arrays.equals(freq,freq1)){
                return true;
            }
        }

        return false;
    }
}
