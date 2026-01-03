package career.datastructure.recurssion.pattern.linerrecursion.stock;

import java.util.Arrays;

/*

 */
public class BestTimeToBuyAndSellIV {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1},2));
        System.out.println(maxProfit(new int[]{3,2,6,5,0,3},2));
    }

    public static int maxProfit(int[] prices,int k) {
//        return maxProfitRec(prices,0,k,0);
        return maxProfitDp(prices,k);
//        return maxProfitDpOp(prices,0,0);
//        return maxProfitNormal(prices,0,0);
    }
    public static int maxProfitRec(int[]prices,int flag,int trans,int index){
        if(index == prices.length) return 0;
        if(trans == 0) return 0;
        int profit =0;
        if(flag == 0){ // will buy
            profit = Math.max(maxProfitRec(prices,0,trans,index+1),-prices[index]+maxProfitRec(prices,1,trans,index+1));
            // main part when you buy it is not complete transaction
        }else{
            profit = Math.max(maxProfitRec(prices,1,trans,index+1),prices[index]+maxProfitRec(prices,0,trans-1,index+1));
            // when you sell any stock it is complete transaction so increase transaction
        }
        return profit;
    }

    public static int maxProfitDp(int[]prices,int k){

        // DP table: dp[ind][buy][cap]
        // ind → current index
        // buy → 0 = can buy, 1 = can sell
        // cap → remaining transactions (max 2)
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k+1];

        // Base case: initialized to 0 → no profit if no transactions left or no days left

        // Fill DP table from the last day to the first
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) { // cap starts from 1 because 0 means no transactions left
                    if (buy == 0) {
                        // Option 1: skip buying
                        // Option 2: buy today → subtract price, keep cap same
                        dp[ind][buy][cap] = Math.max(
                                0 + dp[ind + 1][0][cap],
                                -prices[ind] + dp[ind + 1][1][cap]
                        );
                    } else {
                        // Option 1: skip selling
                        // Option 2: sell today → add price, reduce cap by 1
                        dp[ind][buy][cap] = Math.max(
                                0 + dp[ind + 1][1][cap],
                                prices[ind] + dp[ind + 1][0][cap - 1]
                        );
                    }
                }
            }
        }

        // Final result: start at index 0, can buy, with 2 transactions left
        return dp[0][0][k];
    }

    public static int maxProfitDpOp(int[]prices,int k){
        int length = prices.length;
        int dp[][]=new int[2][k+1];
        for(int i=length-1;i>=0;i--){
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) { // cap starts from 1 because 0 means no transactions left
                    if(buy==0){
                        dp[buy][cap] = Math.max(
                                0 + dp[0][cap],
                                -prices[i] + dp[1][cap]
                        );
                    } else {
                        // Option 1: skip selling
                        // Option 2: sell today → add price, reduce cap by 1
                        dp[buy][cap] = Math.max(
                                0 + dp[1][cap],
                                prices[i] + dp[0][cap - 1]
                        );
                    }
                }
            }
        }
        return  dp[0][2];
    }

    public static int maxProfitNormal(int[]prices,int k){
        if (k == 0) return 0;

        int[] profit = new int[k+1];
        int[] cost = new int[k+1];

        profit[0] = 0;
        Arrays.fill(cost, Integer.MAX_VALUE);

        for (int price: prices) {
            for (int i = 0; i < k; i++) {
                cost[i+1] = Math.min(cost[i+1], price - profit[i]);
                profit[i+1] = Math.max(profit[i+1], price - cost[i+1]);
            }
            System.out.println(Arrays.toString(cost));
            System.out.println(Arrays.toString(profit));
        }
        return profit[k];

    }
}
