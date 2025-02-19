package career.sixmonthssep.dp.subsequence.string;

/*
https://leetcode.com/problems/longest-common-subsequence/description/
 */
public class CountCommonSubSequence {
    public static void main(String[] args) {
//        System.out.println(countCommonSubsequence("abc","ab"));
//        System.out.println(countCommonSubsequence("abc","de"));
//        System.out.println(countCommonSubsequence("abc","cba"));
//        System.out.println(countCommonSubsequence("a","bca"));
//
//        System.out.println(countCommonSubsequence("abcde","ace"));
        System.out.println(countCommonSubsequence("ABEF","CADE"));
        System.out.println(countCommonSubsequence("ABCDE","FGHIJ"));
        System.out.println(countCommonSubsequence("ajblqcpdz","aefcnbtdi"));
    }

    public static int countCommonSubsequence(String s, String t) {
        return countCommonSubsequenceDp(s,t);
    }

    private static int countCommonSubsequenceDp(String s,String t){
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
                    dp[i][j] = 1 + dp[i][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) - dp[i-1][j-1];
                }
            }
        }

        return dp[m][n];
    }
}
