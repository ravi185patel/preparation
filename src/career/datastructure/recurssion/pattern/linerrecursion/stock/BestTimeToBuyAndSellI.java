package career.datastructure.recurssion.pattern.linerrecursion.stock;

/*

 */
public class BestTimeToBuyAndSellI {
    public static void main(String[] args) {
        int prices[]={7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }

    public static int maxProfit(int[] prices) {
        int maxProfit=0,prevPrice=Integer.MAX_VALUE;
        for(int price:prices){
            prevPrice = Math.min(price,prevPrice);
            maxProfit = Math.max(maxProfit,price - prevPrice);
        }
        return maxProfit;
    }
}
