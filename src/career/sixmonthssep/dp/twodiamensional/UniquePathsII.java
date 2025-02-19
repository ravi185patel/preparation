package career.sixmonthssep.dp.twodiamensional;

import java.util.Arrays;

public class UniquePathsII {
    public static void main(String[] args) {
        int obstacleGrid[][] = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int obstacleGrid[][] = {{0, 1}, {0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));

    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
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
            System.out.println(Arrays.toString(temp));
            prev = temp.clone();
        }

        return prev[n-1];
    }
}