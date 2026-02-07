package career.datastructure.dp.twothreedp;

import java.util.Arrays;

public class MinPathSumInGrid {
    public static void main(String[] args) {
        System.out.println(minPathSumInGrid(new int[][]{
//                {5,9,6},{11,5,2}
//                {1,2,3},{4,5,6}
                {1,2},{1,1}
        }));
    }
    public static int minPathSumInGrid(int grid[][]){
//        return solveDp(grid);
        return solveDpOp(grid);
    }

    public static int solveDp(int grid[][]){
        int n = grid.length;
        int m = grid[0].length;
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<n;i++){
            dp[i][0]= (i > 0 ) ? dp[i-1][0]+grid[i][0]:grid[i][0];
        }
        for(int i=0;i<m;i++){
            dp[0][i]=(i > 0 ) ? dp[0][i-1]+grid[0][i]:grid[0][i];
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }

        return dp[n-1][m-1];
    }

    public static int solveDpOp(int grid[][]){
        int n = grid.length;
        int m = grid[0].length;
        int prev[]=new int[m+1];
        for(int i=0;i<m;i++){
            prev[i]=(i == 0 ? 0:prev[i-1])+grid[0][i];
        }
        for(int i=1;i<n;i++){
            int curr[]=new int[m+1];
            for(int j=0;j<m;j++){
                int prevVal = j == 0 ? prev[j]:curr[j-1];
                curr[j] = Math.min(prev[j],prevVal)+grid[i][j];
            }
            prev = curr.clone();
        }

        return prev[m-1];
    }
}
