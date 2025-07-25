package career.datastructure.recurssion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PaintaingAGridWithThreeDiffColor {
    private static final int MOD = 1_000_000_007;
    private int m, n;
    private int result = 0;

    public int colorTheGrid(int m, int n) {
        this.m = m;
        this.n = n;

        int memo[][] = new int[m+1][n+1];
        for(int i[]:memo){
            Arrays.fill(i,-1);
        }
        // Start from the first column
        result = buildColumn(0, new int[m], 0, new int[m],memo);
        for(int i[]:memo){
            System.out.println(Arrays.toString(i));
        }
//        return memo[m-1][n-1];
        return result;
    }

    // Recursive function to build columns cell by cell
    private int buildColumn(int colIndex, int[] prevCol, int row, int[] currCol,int memo[][]) {
        if (colIndex == n) {
//            result = (result + 1) % MOD;
            return 1;
        }

        if (row == m) {
            // Current column complete, move to next column
            return buildColumn(colIndex + 1, currCol.clone(), 0, new int[m],memo);
//            return;
        }
//        if(memo[row][colIndex] != -1){
//            return memo[row][colIndex];
//        }
        int total= 0;
        for (int color = 0; color < 3; color++) {
            // Check vertical (same column)
            if (row > 0 && currCol[row - 1] == color) continue;
            // Check horizontal (same row, previous column)
            if (colIndex > 0 && prevCol[row] == color) continue;

            currCol[row] = color;
            total = (total + buildColumn(colIndex, prevCol, row + 1, currCol,memo))%MOD;
        }
        return memo[row][colIndex] =total;
    }

    public static void main(String[] args) {
        PaintaingAGridWithThreeDiffColor solution = new PaintaingAGridWithThreeDiffColor();
//        System.out.println(solution.colorTheGrid(1, 1)); // Output: 3
//        System.out.println(solution.colorTheGrid(2, 2)); // Output: 54
        System.out.println(solution.colorTheGrid(2, 3)); // Output: 246

    }
}
