package career.datastructure.recurssion.pattern.linerrecursion.stock;

import java.util.Arrays;

/*
dp[i][0] = max profit up to day i, NOT holding stock
dp[i][1] = max profit up to day i, HOLDING stock

dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])

It means:

0 = after sell / before buy

1 = after buy / before sell

2️⃣ What Values Do We Actually Need?

Look carefully:

dp[i][0] needs: dp[i-1][0] dp[i-1][1]
dp[i][1] needs: dp[i-1][1] dp[i-2][0] ← cooldown

So at day i, we only need 3 old values:

Meaning	        Variable
dp[i-2][0]	profit 2 days ago, not holding
dp[i-1][0]	profit yesterday, not holding
dp[i-1][1]	profit yesterday, holding

prev2NotHold = dp[i-2][0]
prevNotHold  = dp[i-1][0]
prevHold     = dp[i-1][1]


 */

public class BestTimeBuySellStockCooldown {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,2,3,0,2}));
        System.out.println(maxProfit(new int[]{1}));
        System.out.println(maxProfit(new int[]{7,1,2,3,4}));
    }

    public static int maxProfit(int[] prices) {
//        return maxProfitRec(prices,0,0);
        return maxProfitDp(prices);
//        return maxProfitDpOp(prices,0,0);
//        return maxProfitNormal(prices,0,0);
    }
    public static int maxProfitRec(int[]prices,int flag,int index){
        if(index >= prices.length) return 0;
        int profit =0;
        if(flag == 0){ // will buy
            profit = Math.max(maxProfitRec(prices,0,index+1),-prices[index]+maxProfitRec(prices,1,index+1));
        }else{
            profit = Math.max(maxProfitRec(prices,1,index+1),prices[index]+maxProfitRec(prices,0,index+2));
        }
        return profit;
    }

    public static int maxProfitDp(int[]prices){
       /* int length = prices.length;
        int dp[][]=new int[length+2][2];
        for(int i=length-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++){
                if(buy == 0){
                    dp[i][0] = Math.max(dp[i+1][0],-prices[i]+dp[i+1][1]);
                }else{
                    dp[i][1] = Math.max(dp[i+1][1],prices[i]+dp[i+2][0]);
                }
            }
        }
        return dp[0][0];*/
        /*
            On day 0, before any action,
            we do not hold a stock, and we want the maximum profit from there onward
         */
        /*int n = prices.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(
                    dp[i - 1][0],
                    dp[i - 1][1] + prices[i]
            );

            dp[i][1] = Math.max(
                    dp[i - 1][1],
                    (i >= 2 ? dp[i - 2][0] : 0) - prices[i]
            );
        }

        return dp[n - 1][0];*/

        int length = prices.length;
        int dp[][]=new int[length+1][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0]; // very important point if you buy then add negative value;
        for(int i=1;i<length;i++){
            for(int buy=0;buy<=1;buy++){
                if(buy == 0){
                    dp[i][0] = Math.max(dp[i-1][0],-prices[i]+dp[i-1][1]);
                }else{
                    dp[i][1] = Math.max(dp[i-1][1],prices[i]+(i >= 2 ? dp[i - 2][0] : 0));
                }
            }
        }
        return Math.max(dp[length][1],dp[length][0]);
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

    // it is jump game but here you can't take adjacent jump and more than 1 jump
    // to get max need min and max (max-min)
    // 1 2 3 4
    // b s h
    // b   s h
    //   b s h
    // b     s
    public static int maxProfitDpOP(int[] prices) {
        int prev2NotHold = 0; // do nothing
        int prevNotHold = 0;
        int prevHold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
                                                    //sell today
            int curNotHold = Math.max(prevNotHold, prevHold + prices[i]); // today not holding stock
                                                //buy today (after cool down)
            int curHold = Math.max(prevHold, prev2NotHold - prices[i]); // holding stock

            prev2NotHold = prevNotHold;  // prev2 prev1 curr
            prevNotHold = curNotHold;
            prevHold = curHold;
        }

        return prevNotHold;
    }
}
