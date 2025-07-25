package career.datastructure.recurssion.subsequence.permutation;

public class PelindromPermutation {

    /*
    Example 1:

Input: s = "code"
Output: false
Example 2:

Input: s = "aab"
Output: true
Example 3:

Input: s = "carerac"
Output: true
     */
    public static void main(String[] args) {

    }

    public boolean canPermutePalindrome(String s) {
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
