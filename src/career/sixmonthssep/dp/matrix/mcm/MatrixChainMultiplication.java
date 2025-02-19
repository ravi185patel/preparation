package career.sixmonthssep.dp.matrix.mcm;

import java.util.Arrays;

/*

https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1

 */


public class MatrixChainMultiplication {
    public static void main(String[] args) {
        System.out.println(matrixMultiplication(new int[]{2, 1, 3, 4}));
        System.out.println(matrixMultiplication(new int[]{1, 2, 3, 4, 3}));
        System.out.println(matrixMultiplication(new int[]{3,4}));
    }

    public static int matrixMultiplication(int arr[]) {
//        return mcmDp(1,arr.length-1,arr);
        int n = arr.length;
        int dp[][]=new int[n][n];
        for(int m[]:dp){
            Arrays.fill(m,-1);
        }
        return mcmDp(1,arr.length-1,arr,dp);
    }

    private static int mcm(int i,int j,int arr[]){
        if(i == j){
            return 0;
        }

        int min = (int)1e9;
        for(int k=i;k<j;k++){
            int steps = arr[i-1]*arr[k]*arr[j]
                    + mcm(i,k,arr) + mcm(k+1,j,arr);

            if(steps < min){
                min = steps;
            }
        }
        return min;
    }

    private static int mcmDp(int i,int j,int arr[],int dp[][]){
        if(i == j){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int min = (int)1e9;
        for(int k=i;k<j;k++){
            int steps = arr[i-1]*arr[k]*arr[j]
                    + mcmDp(i,k,arr,dp) + mcmDp(k+1,j,arr,dp);

            if(steps < min){
                min = steps;
            }
        }
        return dp[i][j]=min;
    }
}
