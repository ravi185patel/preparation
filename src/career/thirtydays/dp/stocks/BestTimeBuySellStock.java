package career.thirtydays.dp.stocks;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

 */
public class BestTimeBuySellStock {
    public static void main(String[] args) {
        int prices[]={7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }

    public static int maxProfit(int[] prices) {
        int max=0,buy = Integer.MAX_VALUE;
        int profit=0;
        for(int price:prices){
            buy = Math.min(price,buy);
            profit = Math.max(profit,price-buy);
        }
        return profit;
    }
}
