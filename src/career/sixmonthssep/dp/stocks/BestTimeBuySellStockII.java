package career.sixmonthssep.dp.stocks;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/

here we need max profit, but you can buy and sell multiple time
It is same problem
here only you need to do is to achieve max profit by buy and sell multiple time

ex : [1,2,3,4,5] profit = 4
2-1,3-2,4-3,5-4 = 4
5-1 = 4

so we can consider nums[i]-nums[i-1] as profit only when nums[i] > nums[i-1];

 */
public class BestTimeBuySellStockII {
    public static void main(String[] args) {
        int prices[]={7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
        System.out.println(maxProfit(new int[]{1,2,3,4,5}));
    }

    public static int maxProfit(int[] prices) {
        int max=0,buy = Integer.MAX_VALUE;
        int profit=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i] > prices[i-1]){
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}
