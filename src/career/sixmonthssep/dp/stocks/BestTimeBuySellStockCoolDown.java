package career.sixmonthssep.dp.stocks;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0


 */
public class BestTimeBuySellStockCoolDown {
    public static void main(String[] args) {
        System.out.println(maxProfitDp(new int[]{3,3,5,0,0,3,1,4},2));
        System.out.println(maxProfitDp(new int[]{1,2,3,4,5},2));
        System.out.println(maxProfitDp(new int[]{2,4,1},2));
        System.out.println(maxProfitDp(new int[]{3,2,6,5,0,3},2));
        System.out.println(maxProfitDp(new int[]{1,2,3,4},2));
    }

    // it is jump game but here you can't take adjacent jump and more than 1 jump
    // to get max need min and max (max-min)
    // 1 2 3 4
    // b s h
    // b   s h
    //   b s h
    // b     s
    public static int maxProfitDpOP(int[] prices) {
        int sold = Integer.MIN_VALUE, held = Integer.MIN_VALUE, reset = 0;

        for (int price : prices) {
            int preSold = sold;

            sold = held + price;
            held = Math.max(held, reset - price);
            reset = Math.max(reset, preSold);
        }

        return Math.max(sold, reset);
    }

    public static int maxProfitDp(int[] prices,int k) {

        int n = prices.length;
        // int prevStatus[][] = new int[n + 2][2];
        int status[][] = new int[n + 2][2];

        for (int stock = n - 1; stock >= 0; stock--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if (buy == 0) {
                    profit = Math.max(status[stock + 1][0], -prices[stock] + status[stock + 1][1]);
                } else {
                    profit = Math.max(status[stock + 1][1], prices[stock] + status[stock + 2][0]);
                }
                status[stock][buy] = profit;
            }

            // prevStatus = status.clone();
        }

        return status[0][0];
    }
}
