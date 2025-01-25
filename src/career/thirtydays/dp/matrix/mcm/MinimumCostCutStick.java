package career.thirtydays.dp.matrix.mcm;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/

 */
public class MinimumCostCutStick {
    public static void main(String[] args) {
        System.out.println(minCost(7,new int[]{1,3,4,5}));
        System.out.println(minCost(9,new int[]{5,6,1,4,2}));
    }

    static int[][] memo;
    static int newCuts[];

    public static int minCost(int n, int[] cuts) {
        int m = cuts.length;
        newCuts = new int[m+2];
        System.arraycopy(cuts,0,newCuts,1,m);
        newCuts[m+1] = n;

        Arrays.sort(newCuts);
        /*
        memo = new int[m+2][m+2];
        for(int r=0;r<m+2;r++){
            Arrays.fill(memo[r],-1);
        }

        return cost(0, newCuts.length - 1);*/
        return costDp(m,newCuts);
    }

    private static int cost(int left,int right){
        if(memo[left][right] != -1){
            return memo[left][right];
        }

        if(right - left == 1){
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for(int mid = left+1;mid < right;mid++){
            int cost = cost(left,mid) + cost(mid,right)+newCuts[right] - newCuts[left];

            ans = Math.min(ans,cost);
        }

        memo[left][right] = ans;
        return ans;
    }

    private static int costDp(int n,int cuts[]){
        int m = cuts.length;
        int[] newCuts = new int[m + 2];
        System.arraycopy(cuts, 0, newCuts, 1, m);
        newCuts[m + 1] = n;
        Arrays.sort(newCuts);

        int[][] dp = new int[m + 2][m + 2];

        for (int diff = 2; diff < m + 2; ++diff) {
            for (int left = 0; left < m + 2 - diff; ++left) {
                int right = left + diff;
                int ans = Integer.MAX_VALUE;
                for (int mid = left + 1; mid < right; ++mid) {
                    ans = Math.min(ans, dp[left][mid] + dp[mid][right] + newCuts[right] - newCuts[left]);
                }
                dp[left][right] = ans;
            }
        }

        return dp[0][m + 1];
    }
}
