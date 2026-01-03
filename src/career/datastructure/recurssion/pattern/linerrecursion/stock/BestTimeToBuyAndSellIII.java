package career.datastructure.recurssion.pattern.linerrecursion.stock;

/*

 */
public class BestTimeToBuyAndSellIII {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }

    public static int maxProfit(int[] prices) {
//        return maxProfitRec(prices,0,0,0);
//        return maxProfitDp(prices,0,0,0);
//        return maxProfitDpOp(prices,0,0);
        return maxProfitNormal(prices,0,0);
    }
    public static int maxProfitRec(int[]prices,int flag,int trans,int index){
        if(index == prices.length) return 0;
        if(trans == 2) return 0;
        int profit =0;
        if(flag == 0){ // will buy
            profit = Math.max(maxProfitRec(prices,0,trans,index+1),-prices[index]+maxProfitRec(prices,1,trans,index+1));
            // main part when you buy it is not complete transaction
        }else{
            profit = Math.max(maxProfitRec(prices,1,trans,index+1),prices[index]+maxProfitRec(prices,0,trans+1,index+1));
            // when you sell any stock it is complete transaction so increase transaction
        }
        return profit;
    }

    public static int maxProfitDp(int[]prices,int flag,int trans,int index){
       /* int n = prices.length;
        int[][][] dp = new int[n][2][3];
        int NEG = (int)-1e9;

// Initialization
        for (int cap = 0; cap <= 2; cap++) {
            dp[0][0][cap] = 0;              // not holding
            dp[0][1][cap] = NEG;            // holding impossible initially
        }
        dp[0][1][2] = -prices[0];           // buy on day 0

// Forward DP
        for (int i = 1; i < n; i++) {
            for (int cap = 1; cap <= 2; cap++) {

                // Not holding (can buy)
                dp[i][0][cap] = Math.max(
                        dp[i-1][0][cap],              // skip
                        dp[i-1][1][cap] + prices[i]   // sell
                );

                // Holding (can sell)
                dp[i][1][cap] = Math.max(
                        dp[i-1][1][cap],              // keep holding
                        dp[i-1][0][cap-1] - prices[i] // buy
                );
            }
        }

// Must end not holding
        return dp[n-1][0][2];*/


        // DP table: dp[ind][buy][cap]
        // ind → current index
        // buy → 0 = can buy, 1 = can sell
        // cap → remaining transactions (max 2)
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        // Base case: initialized to 0 → no profit if no transactions left or no days left

        // Fill DP table from the last day to the first
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) { // cap starts from 1 because 0 means no transactions left
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
        return dp[0][0][2];
    }

    public static int maxProfitDpOp(int[]prices,int flag,int index){
        int length = prices.length;
        int dp[][]=new int[2][3];
        for(int i=length-1;i>=0;i--){
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) { // cap starts from 1 because 0 means no transactions left
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

    public static int maxProfitNormal(int[]prices,int flag,int index){
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;
        int sell1 = 0;
        int sell2 = 0;

        for (int p : prices) {
            buy1 = Math.max(buy1, -p);
            sell1 = Math.max(sell1, buy1 + p);
            buy2 = Math.max(buy2, sell1 - p);
            sell2 = Math.max(sell2, buy2 + p);
        }

        return sell2;

    }
}
