package career.datastructure.graph.template;

import java.util.Arrays;

public class FloydWarshallAlgo {
    public static void main(String[] args) {
        int mat[][] = {{0, 1, 43}, {1, 0, 6}, {-1, -1, 0}};
        shortestDistance(mat);
        for(int row[]:mat) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void shortestDistance(int mat[][]) {
            int m = mat.length;
            int n = mat[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == -1) {
                        mat[i][j] = 10000;
                    }
                }
            }

            for (int vai = 0; vai < m; vai++) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        mat[i][j] = Math.min(mat[i][j],mat[i][vai]+mat[vai][j]);
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 10000) {
                        mat[i][j] = -1;
                    }
                }
            }
    }
}
