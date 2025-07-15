package career.sixmonthssep.dp.stocks;

import java.util.Arrays;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/

You are given an array prices where prices[i] is the price of a given stock on the ith day,
and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like,
but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.



Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6



 */
public class BestTimeBuySellStockTransFees {
    public static void main(String[] args) {
        System.out.println(maxProfitDpOP(new int[]{1,3,2,8,4,9},2));
        System.out.println(maxProfitDpOP(new int[]{1,3,7,5,10,3},3));
        System.out.println(maxProfitRec(new int[]{1,3,7,5,10,3},0,0,3));
        System.out.println(maxProfitDp(new int[]{1,3,7,5,10,3},3));
    }

    public static long maxProfitRec(int[]prices,int index,int buy,int fees){
        if(index == prices.length){
            return 0;
        }

        long maxProfit =0l;
        if(buy == 0){
            maxProfit = Math.max(maxProfitRec(prices,index+1,0,fees),-prices[index]+maxProfitRec(prices,index+1,1,fees));
        }else {
            maxProfit = Math.max(maxProfitRec(prices,index+1,1,fees),-fees+prices[index]+maxProfitRec(prices,index+1,0,fees));
        }
        return maxProfit;
    }

    public static long maxProfitDp(int[]prices,int fees){
        int sell = 0;
        int purchase=-prices[0];
        for(int i=1;i<prices.length;i++){
            purchase = Math.max(purchase,-prices[i]+sell);
            sell = Math.max(sell,prices[i]+purchase-fees);
        }
        return sell;
    }

    public static int maxProfitDpOPOwn(int[] prices,int fee) {
        int n = prices.length;
        int profit = 0;
        for(int i=1;i<n;i++){
            if(prices[i-1] < prices[i]){
                // won't work as every transaction we have to pay fees
                // here two transaction performed and total 2*fee has to pay
                // and same range has one one transaction with fee.
                // 1,3,2,8 -> 1-3,3-8 -> 1,8 -> max profit = 8-1-2 = 5
                profit = Math.max(profit,(prices[i] - prices[i-1])-fee);
            }
        }
        return profit;
    }
    public static int maxProfitDpOP(int[] prices,int fee) {
        int n = prices.length;
        int free = 0, hold = -prices[0];

        for (int i = 1; i < n; i++) {
            int tmp = hold;
            hold = Math.max(hold, free - prices[i]);
            free = Math.max(free, tmp + prices[i] - fee);
        }

        return free;
    }

    public int maxProfitDP(int[] prices, int fee) {
        int length = prices.length;
        int prevStatus[] = new int[2];
        for (int stock = length - 1; stock >= 0; stock--) {
            int status[] = new int[2];
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if (buy == 0) {
                    profit = Math.max(prevStatus[0], -prices[stock] + prevStatus[1]);
                } else {
                    profit = Math.max(prevStatus[1], prices[stock] - fee + prevStatus[0]);
                }
                status[buy] = profit;
                System.out.println(Arrays.toString(status));
            }
            prevStatus = status.clone();
        }

        // System.out.println(Arrays.toString(prevStatus));
        return prevStatus[0];
    }
}
