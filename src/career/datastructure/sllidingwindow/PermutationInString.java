package career.datastructure.sllidingwindow;

import java.util.Arrays;

/*
https://leetcode.com/problems/permutation-in-string/description/

 */
public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab","eidbaooo"));
        System.out.println(checkInclusion("ab","eidboaoo"));
    }
    public static boolean checkInclusion(String s1, String s2) {
        int windowLength = s1.length();
        int freqS1[]=new int[126];
        int freqS2[]=new int[126];
        for(char c: s1.toCharArray()){
            freqS1[c-'a']++;
        }
        int  left=0;
        for(int right=0;right<s2.length();right++){
            char c = s2.charAt(right);
            freqS2[c-'a']++;
            if(right-left+1 == windowLength){
                if(Arrays.equals(freqS1,freqS2)){ //isMatched(s1,freqS1,freqS2)
                    return true;
                }else{
                    c = s2.charAt(left);
                    freqS2[c-'a']--;
                    left++;
                }
            }
        }
        return false;
    }

    public static boolean isMatched(String s1,int freqS1[],int freqS2[]){
        for(char c: s1.toCharArray()){
            if(freqS1[c-'a'] != freqS2[c-'a']){
                return false;
            }
        }
        return true;
    }
}
