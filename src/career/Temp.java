package career;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Temp {
    public static void main(String[] args) {

        Map<Integer,Integer> map = new HashMap<>();
        System.out.println(map.put(1,1));
        System.out.println(map.put(1,1));
//        String res = fractionToDecimal(4,333);
//        System.out.println(res);
//        System.out.println(maxProduct(267));
//        System.out.println(maxProduct(31));
//        System.out.println(maxProduct(22));
//        System.out.println(maxProduct(124));
//        System.out.println(maxProduct(13));
//        makeSpecialGrid(2);
//     /   printGrid(grid);
    }

    public static int maxProduct(int n) {
        String no = String.valueOf(n);
        int max=0;
        for(int i=0;i<no.length();i++){
            for(int j=i+1;j<no.length();j++){
                int no1=Integer.parseInt(no.charAt(i)+"");
                int no2=Integer.parseInt(no.charAt(j)+"");
                max=Math.max(max,no1*no2);
            }
        }
        return max;
    }
    public static int[][] makeSpecialGrid(int N) {
        return buildGrid(N, 0);
    }

    private static int[][] buildGrid(int n, int start) {
        if (n == 0) {
            return new int[][]{{start}};
        }

        int size = (int) Math.pow(2,n);
        int half = size / 2;
        int block = (size * size) / 4;

        int[][] first = buildGrid(n - 1, start);
        int[][] second = buildGrid(n - 1, start + block);
        int[][] third = buildGrid(n - 1, start + 2 * block);
        int[][] fourth = buildGrid(n - 1, start + 3 * block);

        int[][] grid = new int[size][size];
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                grid[i][j + half] = first[i][j];
                grid[i + half][j + half] = second[i][j];
                grid[i + half][j] = third[i][j];
                grid[i][j] = fourth[i][j];
            }
        }
        return grid;
    }

    // Optional: Method to print the grid
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }
}
