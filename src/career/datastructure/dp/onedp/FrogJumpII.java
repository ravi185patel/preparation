package career.datastructure.dp.onedp;

import java.util.Arrays;

public class FrogJumpII {
    public static void main(String[] args) {
        System.out.println(frogJump(new int[]{20, 30, 40, 20},3));//0
        System.out.println(frogJump(new int[]{30, 20, 50, 10, 40},2));//30
        System.out.println(frogJump(new int[]{10, 5, 20, 0, 15},2)); //15
        System.out.println(frogJump(new int[]{15, 4, 1, 14, 15},3)); //2
    }
    public static int frogJump(int[] heights,int k){
//        return solve(heights,heights.length-1,k);
        return solveDp(heights,heights.length-1,k);
    }

    public static int solve(int[] heights,int index,int K){
        if(index == 0) return 0;
        int costing = Integer.MAX_VALUE;
        for(int k=1;k<=K;k++){
            if(index-k < 0) continue;
            costing = Math.min(costing,solve(heights,index-k,K) + Math.abs(heights[index]-heights[index-k]));
        }
        return costing;
    }

    public static int solveDp(int[] heights,int index,int K){
        int n = heights.length;
        int dp[]=new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int mmSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= K; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    mmSteps = Math.min(mmSteps, jump);
                }
            }
            dp[i] = mmSteps;
        }
        return dp[n - 1];
    }

    public static int solveItr(int[] heights,int index){
        int n= heights.length;
//        int dp[]=new int[n+1];
//        Arrays.fill(dp, Integer.MAX_VALUE);
//        dp[0]=0;
        int prev2=Integer.MAX_VALUE,prev1=0;
        for(int i=1;i<n;i++){
            int singleStep = prev1+Math.abs(heights[i]-heights[i-1]);
            int doubleStep = Integer.MAX_VALUE;
            if(i > 1){
                doubleStep = prev2+Math.abs(heights[i]-heights[i-2]);
            }
            int curr = Math.min(singleStep,doubleStep);
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }
}
