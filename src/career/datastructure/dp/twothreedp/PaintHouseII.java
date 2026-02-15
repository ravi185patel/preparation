package career.datastructure.dp.twothreedp;

import java.util.Arrays;

public class PaintHouseII {
    public static void main(String[] args) {
        System.out.println(paintCost(2,3,new int[][]{
                {1,5,3},
                {2,9,4}
        }));
        System.out.println(paintCost(3,3,new int[][]{
                {1,4,5},
                {2,3,5},
                {6,7,8}
        }));
    }
    public static int paintCost(int n, int K, int[][] costs)
    {
        return solveRec(n-1,-1,K,costs);
//        return solveDp(n,K,costs);
    }

    public static int solveRec(int n,int k,int K,int[][] costs){
        if(n < 0) return 0;
        int paintCost = Integer.MAX_VALUE;
        for(int i=0;i<K;i++){ // same as phone number generate + n queen
            if(k == i) continue;
            paintCost = Math.min(paintCost,costs[n][i] + solveRec(n-1,i,K,costs));
        }

        return paintCost;
    }

    public static int solveRecMemo(int n,int k,int K,int[][] costs){
        if(n < 0) return 0;
        int paintCost = Integer.MAX_VALUE;
        for(int i=0;i<K;i++){
            if(k == i) continue;
            paintCost = Math.min(paintCost,costs[n][i] + solveRecMemo(n-1,i,K,costs));
        }

        return paintCost;
    }

    public static int solveDp(int n,int K,int[][] costs){
        int[][] dp = new int[n+1][K];

        dp[0] = costs[0].clone();

        for (int i = 1; i < n; i++) {
            for (int k = 0; k < K; k++) {
                int minPrev = Integer.MAX_VALUE;
                for (int j = 0; j < K; j++) {
                    if (j == k) continue;
                    minPrev = Math.min(minPrev, dp[i - 1][j]);
                }
                dp[i][k] = costs[i][k] + minPrev;
            }
        }


        return Arrays.stream(dp[n-1]).min().getAsInt();
    }
}
