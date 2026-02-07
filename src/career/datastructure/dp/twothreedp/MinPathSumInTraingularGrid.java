package career.datastructure.dp.twothreedp;

import career.java.concept.concurrency.Pool.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MinPathSumInTraingularGrid {
    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{
//                {1},{2,3},{3,6,7},{8,9,6,10}
                {1},
                {1,2},
                {1,2,4}
        }));
    }

    public static int minPathSum(int triangle[][]){
        // Create dp array
        int n = triangle.length;
        int[][] dp = new int[n][n];

        // Fill last row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n-1][j];
        }

        // Fill rest of dp from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Take min of down and diagonal
                int down = triangle[i][j] + dp[i + 1][j];
                int diag = triangle[i][j] + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diag);
            }
        }

        // Return top element
        return dp[0][0];
    }
}
