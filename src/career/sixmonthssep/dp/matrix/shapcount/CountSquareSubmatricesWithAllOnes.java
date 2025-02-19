package career.sixmonthssep.dp.matrix.shapcount;

public class CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args) {
        int [][] matrix =
                {{0, 1, 1, 1},{1, 1, 1, 1},{0, 1, 1, 1}};

        System.out.println(countSquares(matrix));
    }   
    public static int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        /*
         this won't work
         why because when you have 3X3 side square then how you will find it is 3x3 sqaure
         same way for 4x4 side
         so instead of sum each square will do min of each side and plus 1

         */

        int dp[][] = new int[m][n];

        for(int i=0;i<m;i++){
            dp[i] = matrix[i];
        }

//        for(int i=1;i<m;i++){
//            dp[i][0] += dp[i-1][0];
//        }
//
//        for(int i=1;i<n;i++){
//            dp[0][i] += dp[0][i-1];
//        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
//                if(matrix[i-1][j] == 1 && matrix[i][j-1] == 1){
//                    dp[i][j] = dp[i-1][j-1];
//                }
//                dp[i][j] = dp[i-1][j]+dp[i][j-1];

                if(matrix[i][j] == 0){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
                }
            }
        }

        int sum=0;
        for(int me[]:dp){
            for(int k:me){
                sum+=k;
            }
        }

        return sum;
    }
}
