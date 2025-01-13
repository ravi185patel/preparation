package career.thirtydays.dp.onedimensional_fibonaccistyle;

/*
https://leetcode.com/problems/min-cost-climbing-stairs/description/

You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.


Apprach
1] we have choice to pick or not pick -> recursion
2] memo
3] dp
 */
public class MinCostClimbStairs {
    public static void main(String[] args) {
        int cost[] = {10,15,20};
        int minCostClimbStairs = minCostClimbStairs(cost);
        System.out.println(minCostClimbStairs);
    }


    private static int minCostClimbingStairsDp(int[] cost, int n) {
        int dp[]=new int[n+1];
        dp[0]=cost[0];
        dp[1] = cost[1];
        for(int i=2;i<n;i++){
            dp[i] = Math.min(dp[i-1],dp[i-2]) + cost[i];
        }

        return Math.min(dp[n-1],dp[n-2]);
    }
    private static int minCostClimbStairs(int cost[]){
        int prevMinCost1 = cost[0];
        int prevMinCost = cost[1];

        for(int i=2;i<cost.length;i++){
            int costCal = Math.min(prevMinCost,prevMinCost1) + cost[i];
            prevMinCost1 = prevMinCost;
            prevMinCost = costCal;
        }
        return Math.min(prevMinCost,prevMinCost1);
    }
}
