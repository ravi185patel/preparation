package career.datastructure.dp.dponsequence;

import java.util.Arrays;
import java.util.HashMap;

public class PerfectSquare {
    public static void main(String[] args) {
        System.out.println(numSquares(13));
        System.out.println(numSquares(6));
    }
    public static int numSquares(int n) {
//        return solveRec(n);
        int memo[]=new int[n+1];
        Arrays.fill(memo,-1);
//        return solveRecMemo(n,memo);
        return solveDp(n);

    }

    public static int solveRec(int n){
        if(n == 0){
            return 0;
        }

        int res = n;
        for(int i=1;i*i<=n;i++){
            res = Math.min(res, 1 + solveRec(n-i*i));
        }
        return res;
    }

    public static int solveRecMemo(int n,int memo[]){
        if(n == 0){
            return 0;
        }

        if(memo[n]!=-1){
            return memo[n];
        }
        int res = n;
        for(int i=1;i*i<=n;i++){
            res = Math.min(res, 1 + solveRecMemo(n-i*i,memo));
        }
        return memo[n] = res;
    }

    public static int solveDp(int n){
        int dp[]=new int[n+1];
        Arrays.fill(dp,n);
        dp[0]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                dp[i] = Math.min(dp[i],1+dp[i-j*j]);
            }
        }
        return dp[n];
    }

}
