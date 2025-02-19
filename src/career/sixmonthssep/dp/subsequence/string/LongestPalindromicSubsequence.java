package career.sixmonthssep.dp.subsequence.string;

/*
https://leetcode.com/problems/longest-palindromic-subsequence/description/
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {

        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseq("cbbd"));

    }
    public static int longestPalindromeSubseq(String s) {
        String s1 = new StringBuilder(s).reverse().toString();
        return lcs(s,s1);
    }


    private static int lcs(String s,String t){
        int m = s.length();
        int n = t.length();
        int dp[][]=new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
