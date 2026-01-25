package career.datastructure.dp.onedp;

import java.util.Arrays;

public class FrogJump {
    public static void main(String[] args) {
        System.out.println(frogJump(new int[]{20, 30, 40, 20}));
        System.out.println(frogJump(new int[]{30, 20, 50, 10, 40}));
    }
    public static int frogJump(int[] heights){
//        return solve(heights,heights.length-1);
//        return solveDp(heights,heights.length-1);
        return solveItr(heights,heights.length-1);
//        return totalStep;
    }
    static int totalStep=0;
    public static int solve(int[] heights,int index){
        if(index == 0) return 0;
        if(index < 0) return Integer.MAX_VALUE;
        int singleStep = solve(heights,index-1)+Math.abs(heights[index]-heights[index-1]);
        int doubleStep = Integer.MAX_VALUE;
        if(index > 1) {
            doubleStep = solve(heights, index - 2) + Math.abs(heights[index] - heights[index - 2]);
        }
        return Math.min(singleStep,doubleStep);
    }

    public static int solveDp(int[] heights,int index){
        int n= heights.length;
        int dp[]=new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<n;i++){
            int singleStep = dp[i-1]+Math.abs(heights[i]-heights[i-1]);
            int doubleStep = Integer.MAX_VALUE;
            if(i > 1){
                doubleStep = dp[i-2]+Math.abs(heights[i]-heights[i-2]);
            }
            dp[i] = Math.min(singleStep,doubleStep);
        }
        return dp[n-1];
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
