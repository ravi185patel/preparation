package career.sixmonthssep.dp.twodiamensional;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-falling-path-sum/?envType=study-plan-v2&envId=dynamic-programming

 */

public class MinFallingPathSum {
    public static void main(String[] args) {
//        int matrix[][] = {{2,1,3},{6,5,4},{7,8,9}};
        int matrix[][] = {{-19,57},{-40,-5}};
        System.out.println(minFallingPathSum(matrix));
    }

    private static int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int prev[]=new int[n];

        for(int i=0;i<n;i++){
            prev[i]=matrix[0][i];
        }
        System.out.println(Arrays.toString(prev));
        for(int i=1;i<m;i++){
            int curr[]=new int[n];
            for(int j=0;j<n;j++){
//                (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
                int min = Integer.MAX_VALUE;

                if(j-1 >= 0){
                    min = Math.min(min,prev[j-1]);
                }

                min = Math.min(min,prev[j]);

                if(j+1 < n){
                    min = Math.min(min,prev[j+1]);
                }

                curr[j] = min + matrix[i][j];
            }
//            System.out.println(Arrays.toString(curr));
            prev = curr.clone();
        }

        int min = Integer.MAX_VALUE;
        for(int i:prev){
            min = Math.min(min,i);
        }
        return min;
    }
}
