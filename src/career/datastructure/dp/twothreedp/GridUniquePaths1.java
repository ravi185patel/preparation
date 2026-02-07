package career.datastructure.dp.twothreedp;

import java.util.Arrays;

public class GridUniquePaths1 {
    public static void main(String[] args) {
        System.out.println(gridUniquePaths(
                new int[][]{
                        {0, 0, 0},{0, 1, 0},{0, 0, 0}
//                        {0, 0, 0},{0, 0, 1},{0, 1, 0}
//                        {0,1,0,0}
                }));
    }
    public static int gridUniquePaths(int matrix[][]){  // either used matrix and calculate
//      return solveDp(matrix);
        return solveDpMy(matrix);
    }

    public static int solveRec(int m,int n){
        if(n == 0 && m == 0) return 1;
        return solveRec(m-1,n) + solveRec(m,n-1);
    }

    public static int solveDpMy(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int prev[]=new int[n];
        for(int i=0;i<n;i++){
            if(obstacleGrid[0][i] == 1){
                break;
            }else{
                prev[i] += (i == 0 ? 1:prev[i-1]);
            }
        }
        for(int i=1;i<m;i++){
            int curr[]=new int[n];
            boolean flag = false;
            if(obstacleGrid[i][0] == 0) {
                curr[0] = 1;
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        flag = true;
                        continue;
                    }
                    curr[j] = (flag ? 0 : curr[j - 1]) + prev[j];
                }
            }
            // System.out.println(Arrays.toString(temp));
            prev = curr.clone();
        }

        return prev[n-1];
    }
    public static int solveDpOp(int[][] obstacleGrid) { // another optimize approach
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
    public static int solveDp(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int prev[]=new int[n];
        for(int i=0;i<m;i++){
            int temp[]=new int[n];
            for(int j=0;j<n;j++){
                if(i >= 0 && j >= 0 && obstacleGrid[i][j] == 1){
                    temp[j]=0;
                    continue;
                }
                if(i == 0 && j == 0){
                    temp[j]=1;
                    continue;
                }

                int up = 0;
                int left = 0;
                if(i > 0 ){
                    up = prev[j];
                }
                if(j > 0){
                    left = temp[j-1];
                }

                temp[j] = up + left;
            }
            // System.out.println(Arrays.toString(temp));
            prev = temp.clone();
        }

        return prev[n-1];
    }
}
