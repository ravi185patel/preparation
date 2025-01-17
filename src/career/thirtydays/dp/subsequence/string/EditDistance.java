package career.thirtydays.dp.subsequence.string;

import java.util.Arrays;

/*
https://leetcode.com/problems/edit-distance/

 */
public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse","ros"));
    }
    public static int minDistance(String s, String t) {
        return minOp(s,t);
//
//        /*
//          if(matched){
//            move
//          }else{
//            replace (indexS+1,indexT+1)
//            delete (indexS+1,indexT)
//            insert  (indexS,indexT+1)
//          }
//         */
//
//        int
//        int dp[][]=new int[][];
    }

    private static int minOp(String s,String t){
        int m = s.length();
        int n = t.length();

        int dp[][]=new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            dp[i][0]= i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}
