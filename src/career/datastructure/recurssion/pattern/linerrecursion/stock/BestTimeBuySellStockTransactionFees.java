package career.datastructure.recurssion.pattern.linerrecursion.stock;

import java.util.Arrays;

public class BestTimeBuySellStockTransactionFees {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,3,2,8,4,9},2));
        System.out.println(maxProfit(new int[]{1,3,7,5,10,3},3));
    }

    public static int maxProfit(int[] prices,int fees) {
//        return maxProfitRec(prices,0,0,fees);
        return maxProfitDp(prices,fees);
//        return maxProfitDpOp(prices,0,0);
//        return maxProfitNormal(prices,fees);
    }
    public static int maxProfitRec(int[]prices,int flag,int index,int fees){
        if(index == prices.length) return 0;
        int profit =0;
        if(flag == 0){ // will buy
            profit = Math.max(maxProfitRec(prices,0,index+1,fees),-prices[index]+maxProfitRec(prices,1,index+1,fees));
        }else{
            profit = Math.max(maxProfitRec(prices,1,index+1,fees),-fees + prices[index]+maxProfitRec(prices,0,index+1,fees));
        }
        return profit;
    }

    public static int maxProfitDp(int[]prices,int fees){
        int length = prices.length;
        int dp[][]=new int[length][2];
        dp[0][0]=-prices[0]; // very important point if you buy then add negative value;
        dp[0][1]=0;
        for(int i=1;i<length;i++){
            for(int buy=0;buy<=1;buy++){
                if(buy == 0){
                    dp[i][0] = Math.max(dp[i-1][0],-prices[i]+dp[i-1][1]);
                }else{
                    dp[i][1] = Math.max(dp[i-1][1],prices[i]+dp[i-1][0]-fees);
                }
            }
        }
        return Math.max(dp[length-1][1],dp[length-1][0]);
    }

    public static int maxProfitDpOp(int[]prices,int flag,int index){
        int length = prices.length;
        int dp[]=new int[2];
        dp[0]=-prices[0]; // very important point if you buy then add negative value;
        dp[1]=0;
        for(int i=1;i<length;i++){
            for(int buy=0;buy<=1;buy++){
                if(buy == 0){
                    dp[0] = Math.max(dp[0],-prices[i]+dp[1]);
                }else{
                    dp[1] = Math.max(dp[1],prices[i]+dp[0]);
                }
            }
        }
        return Math.max(dp[1],dp[0]);
    }

    public static int maxProfitNormal(int[]prices,int fees){
        /*int length = prices.length;
        int prev0 =-prices[0],prev1=0;
        for(int i=1;i<length;i++){
            for(int buy=0;buy<=1;buy++){
                if(buy == 0){
                    prev0 = Math.max(prev0,-prices[i]+prev1);
                }else{
                    prev1 = Math.max(prev1,prices[i]+prev0);
                }
            }
        }
        return Math.max(prev1,prev0);*/

        int length = prices.length;
        int sell = 0,purchase=-prices[0];
        for(int i=1;i<length;i++){
            sell = Math.max(sell, prices[i]+purchase-fees);
            purchase = Math.max(purchase,-prices[i]+sell);
        }

        return Math.max(sell,purchase);

        /*int profit=0;
        for(int i=1;i< prices.length;i++){
            if(prices[i] - prices[i-1] > 0){
                profit += (prices[i] - prices[i-1]);
            }
        }
        return profit;*/
    }
}
