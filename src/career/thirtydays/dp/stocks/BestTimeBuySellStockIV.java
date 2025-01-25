package career.thirtydays.dp.stocks;

import java.util.Arrays;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/

You are given an integer array prices
where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e.
 you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2)
and sell on day 3 (price = 6), profit = 6-2 = 4.
Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

 */
public class BestTimeBuySellStockIV {
    public static void main(String[] args) {
//        System.out.println(maxProfitDp(new int[]{3,3,5,0,0,3,1,4},2));
//        System.out.println(maxProfitDp(new int[]{1,2,3,4,5},2));
//        System.out.println(maxProfitDp(new int[]{2,4,1},2));
//        System.out.println(maxProfitDp(new int[]{3,2,6,5,0,3},2));
        System.out.println(maxProfitDpOP(new int[]{3,2,6,5,0,3},2));
    }

    /*
    previous and current logic
     */
    public static int maxProfitDpOP(int[] prices,int k) {
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

    public static int maxProfitDp(int[] prices,int k) {
        int length = prices.length;
        int prevStatus[][] = new int[2][k+1];
        for (int stock = length - 1; stock >= 0; stock--) {
            int status[][] = new int[2][k+1];
            for (int buy = 0; buy <= 1; buy++) {
                for(int cap =1;cap<=k;cap++){
                    int profit = 0;
                    if (buy == 0) {
                        profit = Math.max(prevStatus[0][cap], -prices[stock] + prevStatus[1][cap]);
                    } else {
                        profit = Math.max(prevStatus[1][cap], prices[stock] + prevStatus[0][cap-1]);
                    }
                    status[buy][cap] = profit;
                }
            }
            prevStatus = status.clone();
        }

        return prevStatus[0][k];
    }
}
