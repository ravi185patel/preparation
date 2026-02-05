package career.datastructure.dp.dponstock;

public class StockBuyAndSell1 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
    public static int maxProfit(int prices[]){
        return solveOp(prices);
    }

    public static int solve(int prices[]){
        int maxProfit=0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }

    public static int solveOp(int prices[]){
        int maxProfit=0;
        for(int i=0;i<prices.length;i++){
            if(i> 0 && prices[i] > prices[i-1]){
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }
}
