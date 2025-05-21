package career.sixmonthssep.sw.substring;

import java.util.Arrays;

/*
https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/

1] recursion
check overall frequency
right++;
left--;
tc = O(2^n);

2] sliding window
tc = O(n)
make window which contains a,b, and c > k
if they increase then shrink
 */
public class TakeKOfEachCharFromLeftAndRight {
    public static void main(String[] args) {
        findLongestSubStringContainsAllLeastK("aabaaaacaabc",2);
        findLongestSubStringContainsAllLeastK("aaabcbc",2);
    }

    public static void findLongestSubStringContainsAllLeastK(String str,int k){
        int freq[]=new int[3];
        for(char c:str.toCharArray()){
            freq[c-'a']++;
        }

        if(freq[0] < k || freq[1] < k || freq[2] < k){
            System.out.println("no");
            return;
        }

        int left=0,maxLength=0;
        for(int right =0;right<str.length();right++){
            int index = str.charAt(right)-'a';
            freq[index]--;
            while(left <= right &&
                    (freq[0] < k || freq[1] < k || freq[2] < k)){
                index = str.charAt(left)-'a';
                freq[index]++;
                left++;
            }
            maxLength = Math.max(maxLength,right-left+1);
        }
        System.out.println(str.length()-maxLength);
    }
}
