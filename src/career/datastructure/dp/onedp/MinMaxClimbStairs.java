package career.datastructure.dp.onedp;

public class MinMaxClimbStairs {
    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
        System.out.println(minCostClimbingStairs(new int[]{10,15,20}));
        System.out.println(minCostClimbingStairs(new int[]{1,100}));
    }
    public static int minCostClimbingStairs(int nos[]) {
//        return Math.min(climbStairsRec(nos.length-1,nos),climbStairsRec(nos.length-2,nos));
//        return Math.min(climbStairsDp(nos.length-1,nos),climbStairsDp(nos.length-2,nos));
//        return Math.min(climbStairsDp(nos.length-1,nos),climbStairsDp(nos.length-2,nos));
        return climbStairsItr(nos.length,nos);
//                Math.min(climbStairsRec(nos.length-1,nos),climbStairsRec(nos.length-2,nos));
    }

    public static int climbStairsRec(int n,int nos[]) {
        if(n < 0) return Integer.MAX_VALUE;
        return nos[n]+Math.min(climbStairsRec(n,nos),climbStairsRec(n-1,nos));
    }

    public static int climbStairsDp(int n,int nos[]){
        int dp[] = new int[nos.length+1];
        dp[0]=nos[0];
        dp[1]=nos[1];
        for(int i=2;i<n;i++){
            dp[i]=nos[i]+Math.min(dp[i-1],dp[i-2]);
        }
        return dp[n];
    }

    public static int climbStairsItr(int n,int cost[]){
        int prev2=0,prev1=0;
        for(int i=0;i<n;i++){
            int curr=cost[i]+Math.min(prev1,prev2);
            prev2 =prev1;
            prev1=curr;
        }
        return Math.min(prev1,prev2);
    }
}
