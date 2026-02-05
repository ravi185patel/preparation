package career.datastructure.dp.dponstock;

public class StockBuyAndSellCoolDown {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,2,3,0,2}));
//        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
    public static int maxProfit(int prices[]){
//        return solve(0,0,prices);
        // Creating a 3D dp array of size [n][2][3]
/*        int[][][] memo = new int[prices.length][3];

        // Initialize the dp array with -1
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }*/
//
//        return solveMemo(0,2,0,prices,memo);

        return solveDp(prices);
    }

    public static int solve(int index,int op,int prices[]){
        if(index ==prices.length){
            return 0;
        }

        if(op == 0){
            return Math.max(solve(index + 1,  0, prices),
                    -prices[index]+solve(index+1,1,prices));
        }else{
            return Math.max(solve(index + 1,  1, prices),
                    prices[index]+solve(index+2,0,prices));
        }
    }

    public static int solveMemo(int index,int op,int prices[],int memo[][]){
        if(index ==prices.length){
            return 0;
        }
        if(memo[index][op]!= -1){
            return memo[index][op];
        }
        int profit=0;
        if(op == 0){
            profit = Math.max(solveMemo(index + 1,  0, prices,memo),
                    -prices[index]+solveMemo(index+1,1,prices,memo));
        }else{
            profit = Math.max(solveMemo(index + 1,  1, prices,memo),
                    prices[index]+solveMemo(index+2,0,prices,memo));
        }

        return memo[index][op]=profit;
    }

    public static int solveDp(int prices[]){
        int n = prices.length;
        int[][] dp = new int[n+2][2];

        for(int i=n-1;i>=0;i--){
            for(int op=0;op<2;op++) {
                int profit = 0;
                if (op == 0) {
                    profit = Math.max(dp[i + 1][0], -prices[i] + dp[i + 1][1]);
                } else {
                    profit = Math.max(dp[i + 1][1], prices[i] + dp[i + 2][0]);
                }
                dp[i][op] = profit;
            }
        }

        return dp[0][0];
    }
}
