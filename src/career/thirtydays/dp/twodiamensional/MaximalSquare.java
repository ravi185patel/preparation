package career.thirtydays.dp.twodiamensional;

/*

Given an m x n binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.



 */
public class MaximalSquare {
    public static void main(String[] args) {
//        char matrix[][] = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        char matrix[][] = {{'0'}};
        char matrix[][] = {{'0','1'},{'1','0'}};
        System.out.println(maximalSquare(matrix));
    }


    private static int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int dp[][]=new int[m+1][n+1];
        int min = 0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j-1],dp[i-1][j]),dp[i-1][j-1])+1;
                    min = Math.max(min,dp[i][j]);
                }
            }
        }

        return min*min;
    }
}
