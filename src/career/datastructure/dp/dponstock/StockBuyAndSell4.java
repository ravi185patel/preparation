package career.datastructure.dp.dponstock;

public class StockBuyAndSell4 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1},2));
        System.out.println(maxProfit(new int[]{3,2,6,5,0,3},2));
//        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
    public static int maxProfit(int prices[],int k){
//        return solve(0,2,0,prices);
        // Creating a 3D dp array of size [n][2][3]
/*        int[][][] memo = new int[prices.length][2][3];

        // Initialize the dp array with -1
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }*/
//
//        return solveMemo(0,2,0,prices,memo);

        return solveDp(prices,k);
    }

    public static int solve(int index,int noOfTrans,int op,int prices[]){
        if(index ==prices.length || noOfTrans == 0){
            return 0;
        }

        if(op == 0){
            return Math.max(solve(index + 1, noOfTrans, 0, prices),
            -prices[index]+solve(index+1,noOfTrans,1,prices));
        }else{
            return Math.max(solve(index + 1, noOfTrans, 1, prices),
                    prices[index]+solve(index+1,noOfTrans-1,0,prices));
        }
    }

    public static int solveMemo(int index,int noOfTrans,int op,int prices[],int memo[][][]){
        if(index ==prices.length || noOfTrans == 0){
            return 0;
        }
        if(memo[index][op][noOfTrans]!= -1){
            return memo[index][op][noOfTrans];
        }
        int profit=0;
        if(op == 0){
            profit = Math.max(solve(index + 1, noOfTrans, 0, prices),
                    -prices[index]+solve(index+1,noOfTrans,1,prices));
        }else{
            profit = Math.max(solve(index + 1, noOfTrans, 1, prices),
                    prices[index]+solve(index+1,noOfTrans-1,0,prices));
        }

        return memo[index][op][noOfTrans]=profit;
    }

    public static int solveDp(int prices[],int k){
        int n = prices.length;
        int[][][] dp = new int[n+1][2][k+1];

        for(int i=n-1;i>=0;i--){
            for(int op=0;op<2;op++) {
                for(int cap=1;cap<=k;cap++){
                    int profit = 0;
                    if (op == 0) {
                        profit = Math.max(dp[i + 1][0][cap], -prices[i] + dp[i + 1][1][cap]);
                    } else {
                        profit = Math.max(dp[i + 1][1][cap], prices[i] + dp[i + 1][0][cap-1]);
                    }
                    dp[i][op][cap] = profit;
                }
            }
        }

        return dp[0][0][2];
    }

}
