package career.thirtydays.dp.twodiamensional;

import java.util.Arrays;

public class MinPathSum {
    public static void main(String[] args) {
        int grid[][] = {{1,3,1},{1,5,1},{4,2,1}};
//        int grid[][] = {{1,2,3},{4,5,6}};
//        int grid[][]= {{1,2},{1,1}};
        System.out.println(minPathSum(grid));
    }

    private static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int prev[]=new int[n];
        for(int i=0;i<n;i++){
            prev[i] = (i==0 ? 0 :prev[i-1]) + grid[0][i];
        }
        for(int i=1;i<m;i++){
            int curr[]=new int[n];
            for(int j=0;j<n;j++){
                int prevVal = j == 0 ? prev[j]:curr[j-1];
                curr[j] = Math.min(prev[j],prevVal)+grid[i][j];
            }
            prev = curr.clone();
        }

        return prev[n-1];
    }
}
