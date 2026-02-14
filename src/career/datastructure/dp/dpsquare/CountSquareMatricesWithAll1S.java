package career.datastructure.dp.dpsquare;

import java.util.Arrays;

public class CountSquareMatricesWithAll1S {
    public static void main(String[] args) {
        int[][] arr = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println(countSquares(arr));
    }

    public static int countSquares(int arr[][]){
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];

        // Initialize first row of DP table
        for (int j = 0; j < m; j++)
            dp[0][j] = arr[0][j];

        // Initialize first column of DP table
        for (int i = 0; i < n; i++)
            dp[i][0] = arr[i][0];

        // Fill the rest of the DP table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // If current cell is 0, no square ends here
                if (arr[i][j] == 0)
                    dp[i][j] = 0;
                else {
                    // Minimum of top, left, top-left + 1
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                            Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                }
            }
        }

        // Sum up all values in DP table
        int sum = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
            for (int j = 0; j < m; j++) {
                sum += dp[i][j];
            }
        }

        // Return total count of squares
        return sum;
    }
}
