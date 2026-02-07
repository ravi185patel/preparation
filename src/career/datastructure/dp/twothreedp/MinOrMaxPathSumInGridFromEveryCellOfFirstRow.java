package career.datastructure.dp.twothreedp;

public class MinOrMaxPathSumInGridFromEveryCellOfFirstRow {
    public static void main(String[] args) {
        System.out.println(pathSumInGridFromFirstRow(new int[][]{
//                {1, 2, 10, 4},{100, 3, 2, 1},{1, 1, 20, 2},{1, 2, 2, 1}
                {1, 4, 3, 1},{2, 3, -1, -1},{1, 1, -1, 8}
        }));
    }
    public static int pathSumInGridFromFirstRow(int grid[][]){
        return solveDp(grid);
//        return solveDpOp(grid);
    }

    public static int solveDp(int grid[][]){
        int n = grid.length;
        int m = grid[0].length;
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<m;i++){
            dp[0][i]= grid[0][i];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int left = j-1 >= 0 ? dp[i-1][j-1]:0;
                int right = j+1 < m ? dp[i-1][j+1]:0;
                int up = dp[i-1][j];

                dp[i][j] = grid[i][j]+Math.max(left,Math.max(right,up));
            }
        }
        int max=0;
        for(int val:dp[n-1]){
            max = Math.max(val,max);
        }
        return max;
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
