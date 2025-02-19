package career.sixmonthssep.dp.subsequence.string;

/*
https://leetcode.com/problems/shortest-common-supersequence/description/
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"

1] recursion
2] recursion using lcs -> s - lcs = deleteChar , t - lcs = insert
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        System.out.println("res "+shortestCommonSupersequence("abac","cab"));
        System.out.println("res "+shortestCommonSupersequence("aaaaaaaa","aaaaaaaa"));
    }
    public static String shortestCommonSupersequence(String str1, String str2) {
//        minLength = Integer.MAX_VALUE;
//        findShortestCommonSuperseq(str1,str2,0,0,new StringBuilder());
//        return res;
        return findShortestCommonSuperseqDp(str1,str2);
    }

    private static String findShortestCommonSuperseqDp(String s,String t){
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

        int len = dp[m][n];
        int i = m;
        int j = n;

        int index = len - 1;
        String ans = "";

        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                ans += s.charAt(i-1);
                index--;
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans += s.charAt(i-1);
                i--;
            } else {
                ans += t.charAt(j-1);
                j--;
            }
        }

        //Adding Remaing Characters - Only one of the below two while loops will run

        while(i>0){
            ans += s.charAt(i-1);
            i--;
        }
        while(j>0){
            ans += t.charAt(j-1);
            j--;
        }

        String ans2=new StringBuilder(ans).reverse().toString();

        return ans2;
    }

    static int minLength = Integer.MAX_VALUE;
    static String res ="";
    private static void findShortestCommonSuperseq(String s,String t,int indexS,int indexT,StringBuilder sb){
        /*
        Input: str1 = "abac", str2 = "cab"
        Output: "cabac"
        abac ab
        cab ac min -> picked
        if(indexS < 0 || indexT < 0){
           if(indexS > 0) concate remain string of s
           if(indexT > 0) concate remain string of t
        }

        if(matched){
            concate;
        }else{
            concate s+1
            concate t+1
        }

         */

        if(indexS >= s.length() || indexT >= t.length() ){
            String resStr=sb.toString();
            if(indexS < s.length() ){
                resStr += s.substring(indexS,s.length());
            }
            if(indexT < t.length()){
                resStr += t.substring(indexT,t.length());
            }
            if(minLength > resStr.length()){
                minLength = resStr.length();
                res = resStr;
            }
            return;
        }

        if(s.charAt(indexS) == t.charAt(indexT)){
            sb.append(s.charAt(indexS));
            findShortestCommonSuperseq(s,t,indexS+1,indexT+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }else{
            sb.append(s.charAt(indexS));
            findShortestCommonSuperseq(s,t,indexS+1,indexT,sb);
            sb.deleteCharAt(sb.length()-1);
            sb.append(t.charAt(indexT));
            findShortestCommonSuperseq(s,t,indexS,indexT+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }


}
