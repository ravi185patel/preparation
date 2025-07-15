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

        System.out.println(" Recursive ");
        System.out.println(maxProfitRec(prices,0,0));
        System.out.println(maxProfitRec(new int[]{7,6,4,3,1},0,0));
        System.out.println(maxProfitRec(new int[]{1,2,3,4,5},0,0));

        System.out.println(" Recursive-Memo ");
        System.out.println(maxProfitRecMemo(prices,0,0,new long[prices.length][2]));
        System.out.println(maxProfitRecMemo(new int[]{7,6,4,3,1},0,0,new long[prices.length][2]));
        System.out.println(maxProfitRecMemo(new int[]{1,2,3,4,5},0,0,new long[prices.length][2]));

        System.out.println(" Recursive-Dp ");
        System.out.println(maxProfitRecDp(prices,new long[prices.length][2]));
        System.out.println(maxProfitRecDp(new int[]{7,6,4,3,1},new long[prices.length][2]));
        System.out.println(maxProfitRecDp(new int[]{1,2,3,4,5},new long[prices.length][2]));

        System.out.println(" Recursive-Dp-Sop ");
        System.out.println(maxProfitRecDp(prices));
        System.out.println(maxProfitRecDp(new int[]{7,6,4,3,1}));
        System.out.println(maxProfitRecDp(new int[]{1,2,3,4,5}));

        System.out.println(" Recursive-Dp-No-space ");
        System.out.println(maxProfitRecDpSp(prices));
        System.out.println(maxProfitRecDpSp(new int[]{7,6,4,3,1}));
        System.out.println(maxProfitRecDpSp(new int[]{1,2,3,4,5}));
    }

    public static long maxProfitRec(int[]prices,int index,int buy){
        if(index == prices.length){
            return 0;
        }

        long maxProfit =0l;
        if(buy == 0){
            maxProfit = Math.max(maxProfitRec(prices,index+1,0),-prices[index]+maxProfitRec(prices,index+1,1));
        }else {
            maxProfit = Math.max(maxProfitRec(prices,index+1,1),prices[index]+maxProfitRec(prices,index+1,0));
        }
        return maxProfit;
    }

    public static long maxProfitRecMemo(int[]prices,int index,int buy,long dp[][]){
        if(index == prices.length){
            return 0;
        }
        if(dp[index][buy] != 0){
            return dp[index][buy];
        }

        long maxProfit =0l;
        if(buy == 0){
            maxProfit = Math.max(maxProfitRecMemo(prices,index+1,0,dp),-prices[index]+maxProfitRecMemo(prices,index+1,1,dp));
        }else {
            maxProfit = Math.max(maxProfitRecMemo(prices,index+1,1,dp),prices[index]+maxProfitRecMemo(prices,index+1,0,dp));
        }
        return dp[index][buy] = maxProfit;
    }

    public static long maxProfitRecDp(int[]prices,long dp[][]){
        int n= prices.length;
        dp[0][0]=-prices[0];
        dp[0][1]=0;
        for(int i=1;i<n;i++){
            for(int buy=0;buy<=1;buy++){
                if(buy==0){
                    dp[i][0]=Math.max(dp[i-1][0],-prices[i]+dp[i-1][1]);
                }else{
                    dp[i][1]=Math.max(dp[i-1][1],prices[i]+dp[i-1][0]);
                }
            }
        }

        return Math.max(dp[n-1][1],dp[n-1][0]);
    }

    public static long maxProfitRecDp(int[]prices){
        int n= prices.length;
        int dp[]=new int[2];
        dp[0]=-prices[0];
        dp[1]=0;
        for(int i=1;i<n;i++){
            for(int buy=0;buy<=1;buy++){
                if(buy==0){
                    dp[0]=Math.max(dp[0],-prices[i]+dp[1]);
                }else{
                    dp[1]=Math.max(dp[1],prices[i]+dp[0]);
                }
            }
        }

        return Math.max(dp[1],dp[0]);
    }

    public static long maxProfitRecDpSp(int[]prices){
        int n= prices.length;
        int dp[]=new int[2];
        int sell=-prices[0];
        int purchase=0;
        for(int i=1;i<n;i++){
//            for(int buy=0;buy<=1;buy++){
//                if(buy==0){
                    sell=Math.max(sell,-prices[i]+purchase);
//                }else{
                    purchase=Math.max(purchase,prices[i]+sell);
//                }
//            }
        }

        return Math.max(purchase,sell);
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
