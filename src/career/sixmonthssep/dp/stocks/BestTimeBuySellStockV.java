package career.sixmonthssep.dp.stocks;

import java.util.Arrays;

public class BestTimeBuySellStockV {
    public static void main(String[] args) {
        int prices[]={1,7,9,8,2},k=2;
        System.out.println(maximumProfit(prices,k));
        System.out.println(maxProfitRec(prices,0,0,k));
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
    public static long maximumProfit(int[] prices, int k) {
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
        }
        return profit[k];
    }
}
