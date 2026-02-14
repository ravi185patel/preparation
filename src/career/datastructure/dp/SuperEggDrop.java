package career.datastructure.dp;

import java.util.Arrays;

public class SuperEggDrop {
    public static void main(String[] args) {
        System.out.println(superEggDrop(3,14));
    }
    public static int superEggDrop(int k, int n) {
//        return solve(k,n);
        return solveDp(k,n);
    }

    public static int solve(int e,int n){
        if(n == 0 || n == 1) return n;
        if(e == 1) return n;

        int min = Integer.MAX_VALUE;
        for(int k=1;k<=n;k++){
            int attempt = 1 + Math.max(solve(e-1,k-1),solve(e,n-k));
            min = Math.min(attempt,min);
        }

        return min;
    }

    public static int solveMemo(int e,int n,int memo[][]){
        if(n == 0 || n == 1) return n;
        if(e == 1) return n;

        if(memo[e][n] != -1){
            return memo[e][n];
        }
        int min = Integer.MAX_VALUE;
        for(int k=1;k<=n;k++){
            int attempt = 1 + Math.max(solveMemo(e-1,k-1,memo),solveMemo(e,n-k,memo));
            min = Math.min(attempt,min);
        }

        return memo[e][n] = min;
    }


    public static int solveDp(int eggs,int floors){
        int dp[][]=new int[eggs+1][floors+1];
        for (int e = 1; e <= eggs; e++) {
            dp[e][0] = 0;
            dp[e][1] = 1;
        }

        for (int f = 1; f <= floors; f++) {
            dp[1][f] = f;
        }

        for (int e = 2; e <= eggs; e++) {
            for (int f = 2; f <= floors; f++) {

                dp[e][f] = Integer.MAX_VALUE;

                for (int k = 1; k <= f; k++) {

                    int breakCase = dp[e - 1][k - 1];
                    int noBreakCase = dp[e][f - k];

                    int attempt = 1 + Math.max(breakCase, noBreakCase);

                    dp[e][f] = Math.min(dp[e][f], attempt);
                }
            }
        }

        return dp[eggs][floors];
    }
    public static int solveDpBs(int eggs,int floors){
        int dp[][]=new int[eggs+1][floors+1];
        for (int e = 1; e <= eggs; e++) {
            dp[e][0] = 0;
            dp[e][1] = 1;
        }

        for (int f = 1; f <= floors; f++) {
            dp[1][f] = f;
        }

        for (int e = 2; e <= eggs; e++) {
            for (int f = 2; f <= floors; f++) {

                int low = 1;
                int high = f;
                int min = Integer.MAX_VALUE;

                while (low <= high) {

                    int mid = (low + high) / 2;

                    int breakCase = dp[e - 1][mid - 1];
                    int noBreakCase = dp[e][f - mid];

                    int temp = 1 + Math.max(breakCase, noBreakCase);

                    min = Math.min(min, temp);

                    if (breakCase < noBreakCase) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }

                dp[e][f] = min;
            }
        }

        return dp[eggs][floors];
    }

    public static int solveDpOpt(int eggs, int floors) {

        int[] dp = new int[eggs + 1];
        int moves = 0;

        while (dp[eggs] < floors) {
            moves++;

            for (int e = eggs; e > 0; e--) {
                dp[e] = dp[e] + dp[e - 1] + 1;
            }
        }

        return moves;
    }
}
