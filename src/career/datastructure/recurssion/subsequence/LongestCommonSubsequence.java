package career.datastructure.recurssion.subsequence;


import java.util.Arrays;

/*
https://leetcode.com/problems/longest-common-subsequence/

 */

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        System.out.println(longestCommonSubsequence("abcde","ace"));
        System.out.println(longestCommonSubsequence("babgbag","bag"));
        System.out.println(longestCommonSubsequence("abc","abc"));
        System.out.println(longestCommonSubsequence("abc","def"));
        System.out.println(longestCommonSubsequence("bl","yby"));
    }
    public static int longestCommonSubsequence(String s, String t) {
        return findSequence(s,t,0,0);

//        int m = s.length();
//        int n = t.length();
//        int dp[][]=new int[m+1][n+1];
////        for(int row[]:dp){
////            Arrays.fill(row,-1);
////        }
//        return findSequenceMemo(s,t,0,0,dp);
//////        return findSequenceDp(s,t,m,n,dp);
////
////        return findSequenceDpSp(s,t,m,n);
    }

    private static int findSequence(String s,String t,int indexS,int indexT){

        if(indexS >= s.length() || indexT >= t.length()){
            return 0;
        }
        if(s.charAt(indexS) == t.charAt(indexT)){
            return 1 + findSequence(s,t,indexS+1,indexT+1);
        }else{
            return Math.max(findSequence(s,t,indexS+1,indexT),findSequence(s,t,indexS,indexT+1));
        }
    }

    private static int findSequenceMemo(String s,String t,int indexS,int indexT,int dp[][]){

        if(indexS >= s.length() || indexT >= t.length()){
            return 0;
        }
        if(dp[indexS][indexT] != 0){
            return dp[indexS][indexT];
        }
        if(s.charAt(indexS) == t.charAt(indexT)){
            return dp[indexS][indexT] = 1 + findSequenceMemo(s,t,indexS+1,indexT+1,dp);
        }else{
            return dp[indexS][indexT] = Math.max(findSequenceMemo(s,t,indexS+1,indexT,dp),findSequenceMemo(s,t,indexS,indexT+1,dp));
        }
    }


    private static int findSequenceDp(String s,String t,int M,int N,int dp[][]){
        for (int i = 1; i <=M; i++) {
            for (int j = 1; j <=N; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }

        return dp[M][N];

    }


    private static int findSequenceDpSp(String s,String t,int M,int N){

        int prev[]=new int[N+1];
        for (int i = 1; i <=M; i++) {
            int curr[]=new int[N+1];
            for (int j = 1; j <=N; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                }else{
                    curr[j] = Math.max(curr[j-1],prev[j]);
                }
            }
            prev = curr.clone();
        }

        return prev[N];

    }
}