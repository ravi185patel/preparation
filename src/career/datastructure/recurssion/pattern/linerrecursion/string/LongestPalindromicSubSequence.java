package career.datastructure.recurssion.pattern.linerrecursion.string;

import java.util.Arrays;

/*
https://leetcode.com/problems/longest-common-subsequence/description/

 */
public class LongestPalindromicSubSequence {
    static int start=0,end=0;
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("adbab"));
        System.out.println(longestPalindrome("adbba"));
        System.out.println(longestPalindrome("cbbd"));
    }
    public static int longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        String reversS = sb.reverse().toString();
//        return longestPalindromeRec(s.toCharArray(),reversS.toCharArray(),0,0);
        return longestPalindromeDp(s.toCharArray(),reversS.toCharArray(),0,0);
//        return longestPalindromeDp(s.toCharArray(),reversS.toCharArray());
    }

    public static int longestPalindromeRec(char sChar[],char revSChar[],int sIndex,int revSIndex){
        if(sIndex >= sChar.length || revSIndex >= revSChar.length) return 0;

        if(sChar[sIndex] == revSChar[revSIndex]){
            return 1 + longestPalindromeRec(sChar,revSChar,sIndex+1,revSIndex+1);
        }else{
            return Math.max(longestPalindromeRec(sChar,revSChar,sIndex,revSIndex+1),
                    longestPalindromeRec(sChar,revSChar,sIndex+1,revSIndex));
        }
    }


    public static int longestPalindromeDp(char sChar[],char revSChar[],int sIndex,int revSIndex){
        int sLength = sChar.length;
        int revsLength = revSChar.length;
        int dp[][]=new int[sLength+1][revsLength+1];

        for(int i=1;i<=sLength;i++){
            for(int j=1;j<=revsLength;j++){
                if(sChar[i-1] == revSChar[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[sLength][revsLength];
    }

    public static int longestPalindromeDp(char sChar[],char revSChar[]){
        int sLength = sChar.length;
        int revsLength = revSChar.length;
        int prev[]=new int[revsLength+1];

        for(int i=1;i<=sLength;i++){
            int curr[]=new int[revsLength+1];
            for(int j=1;j<=revsLength;j++){
                if(sChar[i-1] == revSChar[j-1]){
                    curr[j] = 1 + prev[j-1];
                }else{
                    curr[j] = Math.max(prev[j],curr[j-1]);
                }
            }
            prev = curr.clone();
        }
        return prev[revsLength];
    }
}
