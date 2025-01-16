package career.thirtydays.dp.subsequence.string;

import java.util.Arrays;

/*
https://leetcode.com/problems/longest-common-subsequence/description/
 */
public class CountLongestCommonSubSequence {
    public static void main(String[] args) {
//        System.out.println(longestCommonSubsequence("abc","ab"));
//        System.out.println(longestCommonSubsequence("abc","de"));
//        System.out.println(longestCommonSubsequence("abc","cba"));
//        System.out.println(longestCommonSubsequence("a","bca"));
//
//        System.out.println(longestCommonSubsequence("abcde","ace"));
//        System.out.println(longestCommonSubsequence("abc","abc"));
//        System.out.println(longestCommonSubsequence("abaaa","baabaca"));
        System.out.println(longestCommonSubsequence("abcabcaa","acbacba"));
    }

    public static int longestCommonSubsequence(String s, String t) {
        return longestCommonSubsequenceDp(s,t);
    }

    private static int longestCommonSubsequenceDp(String s,String t){
        int m = s.length();
        int n = t.length();
        int dp[][]=new int[m+1][n+1];

        /*
        recursion condition
        here we increment 1 only when previous index got matched.
        if(s.charAt(indexS) == t.charAt(indexT)){
            return 1 + findSequence(s,t,indexS+1,indexT+1);
        }else{
            return Math.max(findSequence(s,t,indexS+1,indexT),findSequence(s,t,indexS,indexT+1));
        }
         */

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        for(int me[]:dp){
            System.out.println(Arrays.toString(me));
        }
        return dp[m][n];
    }
}
