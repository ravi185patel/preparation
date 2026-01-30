package career.datastructure.dp.dponsequence;


public class UnbaoundKnapSack {

    public static void main(String[] args) {
        System.out.println(unboundedKnapsack(3,8,new int[]{2, 4, 6},new int[]{5, 11, 13}));
        System.out.println(unboundedKnapsack(2,3,new int[]{6,12},new int[]{4,17}));
        System.out.println(unboundedKnapsack(6,7,new int[]{5,3,6,8,7,4},new int[]{8,5,3,6,4,9}));
    }

    public static int unboundedKnapsack(int n, int W, int[] wt, int[] val) {
        int res =0;
//        return solveRec(n-1,W,val,wt,0);
//        res= solveRec1(n-1,W,val,wt);
//        res= solveRec2(n-1,W,val,wt);
//        res= solveRec3(n-1,W,val,wt);
        res= solveDp(n,W,val,wt);

        return res == Integer.MIN_VALUE ? 0: res;
    }

    public static int solveRec(int n ,int w,int []val,int []wt,int value){
        if(w == 0){
            return value;
        }

        if(n < 0){
            return Integer.MIN_VALUE;
        }

        int noTake = solveRec(n-1,w,val,wt,value);
        int take =Integer.MIN_VALUE;
        if(w >= wt[n]){
            take = solveRec(n,w-wt[n],val,wt,value+val[n]);
            // never pass value in parameter when asked for max and min
            // or count because it is difficult to translateinto dp
        }

        return Math.max(noTake,take);
    }

    public static int solveRec1(int n ,int w,int []val,int []wt){
        if(w == 0){
            return 0;
        }

        if(n < 0){
            return Integer.MIN_VALUE;
        }

        int noTake = solveRec1(n-1,w,val,wt);
        int take =Integer.MIN_VALUE;
        if(w >= wt[n]){
            take = val[n]+solveRec1(n,w-wt[n],val,wt);
        }

        return Math.max(noTake,take);
    }

    public static int solveRec3(int ind,int w, int[] profit, int[] weight) {



        if(ind ==0){

            return (int)(w/weight[0]) * (profit[0]);

        }


        int notTake = solveRec3(ind-1,w, profit, weight);
        int Take = (weight[ind] <= w) ? profit[ind] + solveRec3(ind,w-weight[ind], profit, weight) : Integer.MIN_VALUE;



        return Math.max(Take, notTake);

    }
    public static int solveRec2(int n ,int w,int []val,int []wt){
        if(n == 0){
            return (w/wt[0])*val[0];
        }

        int noTake = solveRec1(n-1,w,val,wt);
        int take =Integer.MIN_VALUE;
        if(w >= wt[n]){
            take = val[n]+solveRec1(n,w-wt[n],val,wt);
        }

        return Math.max(noTake,take);
    }
    public static int solveDp(int n ,int W,int []val,int []wt){
        int[] cur = new int[W + 1];

//        for (int i = wt[0]; i <= W; i++) {
//            cur[i] = (i / wt[0]) * val[0];
//        }

        for (int ind = 0; ind < n; ind++) {
            for (int cap = 0; cap <= W; cap++) {
                int notTaken = cur[cap];
                int taken = Integer.MIN_VALUE;
                if (wt[ind] <= cap) {
                    taken = val[ind] + cur[cap - wt[ind]];
                }
                cur[cap] = Math.max(notTaken, taken);
            }
        }

        // Final result for capacity W
        return cur[W];
    }
}
