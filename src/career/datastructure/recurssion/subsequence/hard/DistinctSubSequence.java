package career.datastructure.recurssion.subsequence.hard;

import java.util.Arrays;

/*

https://leetcode.com/problems/distinct-subsequences/description/?envType=problem-list-v2&envId=dynamic-programming
1] recursive
2] memo + rec
3] dp
4] dp + space optimization
 */
public class DistinctSubSequence {
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit","rabbit"));
        System.out.println(numDistinct("babgbag","bag"));
        System.out.println(numDistinct("bl","yby"));
    }
    public static int numDistinct(String s, String t) {
            return findSequence(s,t,0,0);
//        int m = s.length();
//        int n = t.length();
//        int dp[][]=new int[m+1][n+1];
//        for(int row[]:dp){
//            Arrays.fill(row,-1);
//        }
//        return findSequenceMemo(s,t,0,0,dp);
//        //     return findSequenceDp(s,t,m,n,dp);// you can more space optimization
    }

    private static int findSequence(String s,String t,int indexS,int indexT){
//        if(indexS >= s.length()){
//            return indexT == t.length() ? 1:0;
//        }
//
//        if(indexT == t.length()){
//            return 1;
//        }
//
//        if(indexT > t.length()){
//            return 0;
//        }
//
//        int noTaken = findSequence(s,t,indexS+1,indexT);
//        int taken = 0;
//        if(s.charAt(indexS) == t.charAt(indexT)){
//            taken = findSequence(s,t,indexS+1,indexT+1);
//        }
//
//        return noTaken + taken;
        if(indexS == s.length() || indexT == t.length()){
            return indexT == t.length() ? 1:0;
        }

        if(s.charAt(indexS) == t.charAt(indexT)){
            return findSequence(s,t,indexS+1,indexT)
                    + findSequence(s,t,indexS+1,indexT+1);
        }else{
            return findSequence(s,t,indexS+1,indexT);
        }
    }

    private static int findSequenceMemo(String s,String t,int indexS,int indexT,int dp[][]){
        if(indexS == s.length() || indexT == t.length()){
            return indexT == t.length() ? 1:0;
        }

        if(dp[indexS][indexT] != -1){
            return dp[indexS][indexT];
        }

        if(s.charAt(indexS) == t.charAt(indexT)){
            return dp[indexS][indexT] = findSequenceMemo(s,t,indexS+1,indexT,dp)
                    + findSequenceMemo(s,t,indexS+1,indexT+1,dp);
        }else{
            return dp[indexS][indexT] = findSequenceMemo(s,t,indexS+1,indexT,dp);
        }
    }


    private static int findSequenceDp(String s,String t,int M,int N,int dp[][]){

        // Base case initialization
        for (int j = 0; j <= N; j++) {
            dp[M][j] = 0;
        }

        // Base case initialization
        for (int i = 0; i <= M; i++) {
            dp[i][N] = 1;
        }

        // satisfy the way we've modeled our recursive solution
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                // Remember, we always need this result
                dp[i][j] = dp[i + 1][j];

                // If the characters match, we add the
                // result of the next recursion call (in this
                // case, the value of a cell in the dp table
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];

    }
}
