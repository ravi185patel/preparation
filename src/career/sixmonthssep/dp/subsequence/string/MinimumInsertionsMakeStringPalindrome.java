package career.sixmonthssep.dp.subsequence.string;

/*
https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/

find the longest palindrome subsequence
length - length(longest palindrome subsequence)

longest palindrome subsequence -> common subsequence(original string,reverse of original string)
 */
public class MinimumInsertionsMakeStringPalindrome {
    public static void main(String[] args) {

        System.out.println(minInsertions("zzazz"));
        System.out.println(minInsertions("mbadm"));
        System.out.println(minInsertions("leetcode"));
    }
    public static int minInsertions(String s) {
        return findMinInsert(s,new StringBuilder(s).reverse().toString());
    }

    private static int findMinInsert(String s,String t){
        int m = s.length();
        int dp[][] = new int[m+1][m+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=m;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return m - dp[m][m];
    }
}
