package career.thirtydays.dp.substring_subrray.substring;

import java.util.Arrays;

/*
https://leetcode.com/problems/palindrome-partitioning-ii/description/
Given a string s, partition s such that every
substring
 of the partition is a
palindrome
.

Return the minimum cuts needed for a palindrome partitioning of s.

 */
public class PalindromePartitioningII {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));
    }

    public static int minCut(String s) {
        return minCutRec(0,s)-1;
//        return minCutDp(s);
    }

    private static int minCutDp(String s){
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            // String[i...j]
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, s)) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }
        return dp[0] - 1;

    }

    private static int minCutRec(int index,String s){
        if(index == s.length()) return 0;

        int minCost = Integer.MAX_VALUE;
        for(int i=index;i<s.length();i++){
            if(isPalindrome(index,i,s)){
                int cost = 1 + minCutRec(i+1,s);
                minCost = Math.min(minCost,cost);
            }
        }

        return minCost;
    }

    static boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
