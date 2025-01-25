package career.thirtydays.string;

/*
https://leetcode.com/problems/palindrome-permutation/description/

 */
public class PalindromePermutation {
    public static void main(String[] args) {

    }

    public static boolean canPermutePalindrome(String s) {
        int freq[]=new int[26];
        for(char c:s.toCharArray()){
            freq[c-'a']++;
        }

        int count=0;
        for(int i=0;i<26;i++){
            if(freq[i] == 0) continue;
            if(freq[i]%2!=0){
                count++;
            }
            if(count > 1){
                return false;
            }
        }

        return true;
    }
}
