package career.datastructure.recurssion.subsequence;

import java.util.Arrays;

/*
https://leetcode.com/problems/longest-palindromic-subsequence/description/
 */
public class LongestPalindromSubSequence {
    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq(s));
    }

    public static int longestPalindromeSubseq(String s) {
        String revS = new StringBuilder(s).reverse().toString();
//        return findLongestPalinSubseq(s.toCharArray(),revS.toCharArray(),0,0);
        int n = s.length();
        int memo[][]=new int[n+1][n+1];
        for(int d[]:memo){
            Arrays.fill(d,-1);
        }
//        return findLongestPalinSubseqMemo(s.toCharArray(),revS.toCharArray(),0,0,memo);
        return findLongestPalinSubseqDP(s.toCharArray(),revS.toCharArray(),0,0,memo);
    }

    private static int findLongestPalinSubseq(char sChArr[],char revsCharArr[],int indexS,int indexRevs){
        if(indexS >= sChArr.length || indexRevs >= revsCharArr.length){
            return 0;
        }
        if(sChArr[indexS] == revsCharArr[indexRevs]){
            return 1 + findLongestPalinSubseq(sChArr,revsCharArr,indexS+1,indexRevs+1);
        }else{
            return Math.max(findLongestPalinSubseq(sChArr,revsCharArr,indexS+1,indexRevs),
                    findLongestPalinSubseq(sChArr,revsCharArr,indexS,indexRevs+1));
        }
    }


    private static int findLongestPalinSubseqMemo(char sChArr[],char revsCharArr[],int indexS,int indexRevs,int memo[][]){
        if(indexS >= sChArr.length || indexRevs >= revsCharArr.length){
            return 0;
        }
        if(memo[indexS][indexRevs] != -1){
            return memo[indexS][indexRevs];
        }
        if(sChArr[indexS] == revsCharArr[indexRevs]){
            return memo[indexS][indexRevs] = 1 + findLongestPalinSubseqMemo(sChArr,revsCharArr,indexS+1,indexRevs+1,memo);
        }else{
            return memo[indexS][indexRevs] = Math.max(findLongestPalinSubseqMemo(sChArr,revsCharArr,indexS+1,indexRevs,memo),
                    findLongestPalinSubseqMemo(sChArr,revsCharArr,indexS,indexRevs+1,memo));
        }
    }

    private static int findLongestPalinSubseqDP(char sChArr[],char revsCharArr[],int indexS,int indexRevs,int memo[][]){
        for(int m[]:memo){
            Arrays.fill(m,0);
        }
        for(int i=1;i<=sChArr.length;i++){
            for(int j=1;j<=sChArr.length;j++){
                if(sChArr[i-1] == revsCharArr[j-1]){
                    memo[i][j] = 1 + memo[i-1][j-1];
                }else{
                    memo[i][j] = Math.max(memo[i-1][j],memo[i][j-1]);
                }
            }
        }

        return memo[sChArr.length][sChArr.length];
    }
}
