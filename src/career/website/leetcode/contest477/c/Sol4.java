package career.website.leetcode.contest477.c;

import java.util.*;

public class Sol4 {
    public static void main(String[] args) {
        int grid[][]={{1,2,4},{2,3,5}};
        Sol4 sol4 = new Sol4();
        System.out.println(sol4.canPartitionGrid(grid));
    }
    public boolean canPartitionGrid(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        int total = 0;
        for (int[] row : A) {
            for (int val : row) {
                total += val;
            }
        }

        if (check(A, total, m, n)) return true;
        int[][] reversed = reverseRows(A);
        if (check(reversed, total, m, n)) return true;

        int[][] transposed = transpose(A);
        if (check(transposed, total, n, m)) return true;
        int[][] reversedT = reverseRows(transposed);
        return check(reversedT, total, n, m);
    }

    private static boolean check(int[][] A, int total, int m, int n) {
        Set<Integer> seen = new HashSet<>();
        int top = 0;

        for (int i = 0; i < m; i++) {
            for (int val : A[i]) {
                seen.add(val);
            }

            int rowSum = 0;
            for (int val : A[i]) rowSum += val;
            top += rowSum;
            int bot = total - top;

            int diff = top - bot;
            if (diff == 0 || diff == A[0][0] || diff == A[0][n - 1] || diff == A[i][0]) return true;
            if (n > 1 && i > 0 && seen.contains(diff)) return true;
        }

        return false;
    }

    private static int[][] reverseRows(int[][] A) {
        int m = A.length;
        int[][] reversed = new int[m][];
        for (int i = 0; i < m; i++) {
            reversed[i] = A[m - 1 - i].clone();
        }
        return reversed;
    }

    private static int[][] transpose(int[][] A) {
        int m = A.length, n = A[0].length;
        int[][] T = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                T[j][i] = A[i][j];
            }
        }
        return T;
    }

}
