package career.datastructure.dp.dponstring;

import java.util.Arrays;

public class DistinctSubsequences {
    public static void main(String[] args) {
        System.out.println(distinctSubsequence("axbxax","axa"));
        System.out.println(distinctSubsequence("babgbag","bag"));
    }

    public static int distinctSubsequence(String s,String t){
//        return solve(s.toCharArray(),t.toCharArray(),s.length()-1,t.length()-1);
        return solveDp(s.toCharArray(),t.toCharArray());
    }

    public static int solve(char s[],char t[],int ind,int ind1){
        if(ind1 < 0){
            return 1;
        }
        if(ind < 0){
            return 0;
        }

        if(s[ind]==t[ind1]) {
            return solve(s, t, ind - 1, ind1 - 1) + solve(s,t,ind-1,ind1);
        }else{
            return solve(s,t,ind-1,ind1);
        }
    }
//
    public static  int solveDp(char s[],char t[]){
        int n = s.length;
        int m = t.length;
        int dp[][]=new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for(int i=1;i<=s.length;i++){
            for(int j=1;j<=t.length;j++){
                if(s[i-1] == t[j-1]){
                    dp[i][j] = dp[i-1][j]+dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        for(int row[]:dp){
            System.out.println(Arrays.toString(row));
        }
        return dp[n][m];
    }
}
