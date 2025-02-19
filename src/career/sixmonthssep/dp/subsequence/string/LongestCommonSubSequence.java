package career.sixmonthssep.dp.subsequence.string;

/*
https://leetcode.com/problems/longest-common-subsequence/description/
 */
public class LongestCommonSubSequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abc","ab"));
        System.out.println(longestCommonSubsequence("abc","de"));
        System.out.println(longestCommonSubsequence("abc","cba"));
        System.out.println(longestCommonSubsequence("a","bca"));

        System.out.println(longestCommonSubsequence("abcde","ace"));
        System.out.println(longestCommonSubsequence("abc","abc"));
        System.out.println(longestCommonSubsequence("abc","abc"));
        System.out.println(longestCommonSubsequence("abbbabaaaa","baababab"));
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
        printLongestCommonSubsequence(dp,s,t);
        return dp[m][n];
    }

    public static void printLongestCommonSubsequence(int dp[][],String s,String t){
        int n = s.length();
        int m = t.length();

        int len=dp[n][m];
        int i=n;
        int j=m;

        int index = len-1;
        String str="";
        for(int k=1; k<=len;k++){
            str +="$"; // dummy string
        }
        StringBuilder ss= new StringBuilder(s);
        StringBuilder str2=new StringBuilder(str);
        while(i>0 && j>0){
            if(ss.charAt(i-1) == t.charAt(j-1)){
                str2.setCharAt(index,ss.charAt(i-1) );
                index--;
                i--;
                j--;
            }
            else if(ss.charAt(i-1)>t.charAt(j-1)){
                i--;
            }
            else j--;
        }
        System.out.println(str2);
    }
}
