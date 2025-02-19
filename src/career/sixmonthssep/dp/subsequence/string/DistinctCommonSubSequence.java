package career.sixmonthssep.dp.subsequence.string;

/*

https://leetcode.com/problems/distinct-subsequences/description/
 */
public class DistinctCommonSubSequence {
    public static void main(String[] args) {
//        System.out.println(countCommonSubsequence("abc","ab"));
//        System.out.println(countCommonSubsequence("abc","de"));
//        System.out.println(countCommonSubsequence("abc","cba"));
//        System.out.println(countCommonSubsequence("a","bca"));
//
//        System.out.println(countCommonSubsequence("abcde","ace"));
        System.out.println(countCommonSubsequence("ABEF","CADE"));
        System.out.println(countCommonSubsequence("ABCDE","FGHIJ"));
        System.out.println(countCommonSubsequence("rabbbit","rabbit"));
        System.out.println(countCommonSubsequence("babgbag","bag"));
        System.out.println(countCommonSubsequence("gfg","gfg"));
    }

    public static int countCommonSubsequence(String s, String t) {
        return countCommonSubsequenceDp(s,t);
    }

    private static int countCommonSubsequenceDp(String s,String t){
        int m = s.length();
        int n = t.length();
        int dp[][]=new int[m+1][n+1];


        /*

        if(s == 0 || t == 0){
           return t == 0 ? 1:0;
        }

        int taken =0;
        if(matched){
            taken = lcs(s+1,t+1);
        }
        untaken = lcs(s+1,t);
        return taken + untaken;

         */
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                int noTake = dp[i-1][j];
                int take = 0;
                if(s.charAt(i-1) == t.charAt(j-1)){
                    take = dp[i-1][j-1];
                }
                dp[i][j]= noTake + take;
            }
        }

        return dp[m][n];
    }
}
