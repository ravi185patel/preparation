package career.sixmonthssep.dp.stocks;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).

can do only two transaction to achieve max result

example
[1,2,3,4,5]
5-1  => 4
2-1+ 5-3 => 3
3-1 + 5-4 => 3


The intuition is that we can consider the problem as a game,
and we as agent could make at most two transactions in order
to gain the maximum points (profits) from the game.

The two transactions be decomposed into 4 actions: "buy of transaction #1",
"sell of transaction #1", "buy of transaction #2" and "sell of transaction #2".

To solve the game, we simply run a simulation along the sequence of prices,
at each time step, we calculate the potential outcomes for each of our actions.
At the end of the simulation, the outcome of the final action "sell of transaction #2"
would be the desired output of the problem.

Algorithm

Overall, we run an iteration over the sequence of prices.

Over the iteration, we calculate 4 variables which correspond to the costs or the profits of each action respectively, as follows:

t1_cost: the minimal cost of buying the stock in transaction #1.
The minimal cost to acquire a stock would be the minimal price value
that we have seen so far at each step.

t1_profit: the maximal profit of selling the stock in transaction #1.
Actually, at the end of the iteration, this value would be the answer for
the first problem in the series, i.e. Best Time to Buy and Sell Stock.

t2_cost: the minimal cost of buying the stock in transaction #2,
while taking into account the profit gained from the previous transaction #1.
One can consider this as the cost of reinvestment. Similar with t1_cost,
we try to find the lowest price so far,
which in addition would be partially compensated by the profits gained
from the first transaction.

t2_profit: the maximal profit of selling the stock in transaction #2.
With the help of t2_cost as we prepared so far, we would find out
the maximal profits with at most two transactions at each step.

 */
public class BestTimeBuySellStockIII {
    public static void main(String[] args) {
        System.out.println(maxProfitDpOP(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(maxProfitDpOP(new int[]{1,2,3,4,5}));

        System.out.println(" Dp ---");
        System.out.println(maxProfitDp1(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(maxProfitDp1(new int[]{1,2,3,4,5}));
    }

    public static int maxProfitDpOP(int[] prices) {
        int buy1 = Integer.MAX_VALUE,buy2 = Integer.MAX_VALUE;
        int profit1=0,profit2=0;
        for(int i=0;i<prices.length;i++){
            buy1 = Math.min(buy1,prices[i]);
            profit1 = Math.max(profit1,prices[i]-buy1);

            buy2 = Math.min(buy2,prices[i] - profit1);
            profit2 = Math.max(profit2,prices[i]-buy2);

//            System.out.println(prices[i]+" "+buy1+" "+profit1+" "+buy2+" "+profit2);
        }
        return profit2;
    }

    public int maxProfitDp(int[] prices) {
        int length = prices.length;
        int prevStatus[][] = new int[2][3];
        for (int stock = length - 1; stock >= 0; stock--) {
            int status[][] = new int[2][3];
            for (int buy = 0; buy <= 1; buy++) {
                for(int cap =1;cap<=2;cap++){
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

        return prevStatus[0][2];
    }

    public static int maxProfitDp1(int[] prices) {
        int length = prices.length;
        int prevStatus[][] = new int[2][3];
        prevStatus[0][0]=0;
        prevStatus[0][1]=0;
        prevStatus[0][2]=0;
        for (int stock = 1;stock< length;stock++) {
            int status[][] = new int[2][3];
            for (int buy = 0; buy <= 1; buy++) {
                for(int cap =1;cap<=2;cap++){
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

        return prevStatus[0][2];
    }
}
