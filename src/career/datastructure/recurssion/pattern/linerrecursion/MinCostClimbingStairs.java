package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10,15,20}));
        System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
    }
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // given you can start eiter from 0 or 1;
/*         return Math.min(
                 minCostClimbingStairsRec(cost, cost.length - 1), // start at 1 then reach to n
                 minCostClimbingStairsRec(cost, cost.length - 2)); // start at 0 then react to n-1*/

//        return minCostClimbingStairsDp(cost,n);
        return minCostClimbingStairsNormal(cost,n);
    }

    public static int minCostClimbingStairsRec(int []cost,int n){
        if(n < 0) return Integer.MAX_VALUE;
        if(n== 0 || n == 1) return cost[n];
        int noTake = minCostClimbingStairsRec(cost,n-1);
        int take= Integer.MAX_VALUE;
        if(n-2 >= 0){
            take = minCostClimbingStairsRec(cost,n-2);
        }
        return Math.min(noTake,take) + cost[n] ;
    }

    public static int minCostClimbingStairsDp(int []cost,int n){
        int dp[]=new int[n+1];
        dp[0]=cost[0];
        dp[1]=cost[1];

        for(int i=2;i<n;i++){
            dp[i] = cost[i] + Math.min(dp[i-1],dp[i-2]);
        }
        return Math.min(dp[n-1],dp[n-2]);
    }

    public static int minCostClimbingStairsNormal(int []cost,int n){

        int prev2=0;
        int prev1=0;

        for(int i=0;i<n;i++){
            int curr = cost[i] + Math.min(prev2,prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return Math.min(prev1,prev2);
    }
}
