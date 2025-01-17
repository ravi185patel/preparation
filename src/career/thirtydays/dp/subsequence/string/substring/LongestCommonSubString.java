package career.thirtydays.dp.subsequence.string.substring;

public class LongestCommonSubString {
    public static void main(String[] args) {
        System.out.println(longestCommonSubstr("ABCDGH","ACDGHR"));
        System.out.println(longestCommonSubstr("abc","acb"));
        System.out.println(longestCommonSubstr("YZ","yz"));
    }
    public static int longestCommonSubstr(String s1, String s2) {
        return lcs(s1,s2);
    }

    private static int lcs(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int dp[][]=new int[m+1][n+1];
        int ans=0;

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans = Math.max(ans, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }
}
