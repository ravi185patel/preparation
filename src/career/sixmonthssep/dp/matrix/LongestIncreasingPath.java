package career.sixmonthssep.dp.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LongestIncreasingPath {
    public static void main(String[] args) {

        System.out.println(longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
        System.out.println(longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}}));
    }
    public static int longestIncreasingPath(int[][] matrix) {
//        return longestIncreasingPathDfs(matrix);
        return bfsTopoSort(matrix);
    }

    public static int longestIncreasingPathDfs(int[][] matrix) {
        int longestPath = 0;
        int  rows = matrix.length;
        int  cols = matrix[0].length;
        int memo[][]=new int[rows][cols];
        for(int row[]:memo){
            Arrays.fill(row,-1);
        }
        for(int row=0;row<rows;row++){
            for(int col = 0;col < cols; col++){
//                longestPath = Math.max(longestPath,dfs(row,col,rows,cols,matrix));
//                longestPath = Math.max(longestPath,dfsMemo(row,col,rows,cols,matrix,memo));
                longestPath = Math.max(longestPath,bfs(row,col,rows,cols,matrix));
            }
        }
        return longestPath;
    }
    static int directions[][]={{0,1},{1,0},{-1,0},{0,-1}};
    private static int bfs(int row,int col,int rows,int cols,int matrix[][]){
        int cache[][] = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row,col,1});
        int max=1;
        while(!queue.isEmpty()){
            int point[] = queue.poll();
            max = Math.max(point[2],max);
            for(int direction[]:directions){
                int pointX = point[0] + direction[0];
                int pointY = point[1] + direction[1];
                int stepN = point[2];

                if(pointX >= rows || pointX < 0
                        || pointY >= cols || pointY < 0
                        || matrix[pointX][pointY] <= matrix[point[0]][point[1]])
                    continue;

                queue.add(new int[]{pointX,pointY,stepN+1});
            }
        }
        return max;
    }

    private static int bfsTopoSort(int matrix[][]){
        int m = matrix.length;
        int n = matrix[0].length;
        int inDegree[][]=new int[m][n];
        for(int row=0;row<m;row++) {
            for (int col = 0; col < n; col++) {

                for(int direction[]:directions){
                    int pointX = row + direction[0];
                    int pointY = col + direction[1];

                    if(pointX >= m || pointX < 0
                            || pointY >= n || pointY < 0
                            || matrix[pointX][pointY] <= matrix[row][col]) {
                        continue;
                    }
                    inDegree[pointX][pointY]++;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        for(int row=0;row<m;row++) {
            System.out.println(Arrays.toString(inDegree[row]));
            for (int col = 0; col < n; col++) {
                if(inDegree[row][col] == 0){
                    queue.add(new int[]{row,col});
                }
            }
        }

        int longestIncreasingPath=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            longestIncreasingPath++;
            for(int level =0;level<size;level++){
                int point[]=queue.poll();
                for(int direction[]:directions){
                    int pointX = point[0] + direction[0];
                    int pointY = point[1] + direction[1];

                    if(pointX >= m || pointX < 0
                            || pointY >= n || pointY < 0
                            || matrix[pointX][pointY] <= matrix[point[0]][point[1]]) {
                        continue;
                    }
                    inDegree[pointX][pointY]--;
                    if(inDegree[pointX][pointY] == 0) {
                        queue.add(new int[]{pointX, pointY});
                    }
                }
            }
        }
        return longestIncreasingPath;
    }

    private static int dfs(int row,int col,int rows,int cols,int matrix[][]){
        int max =0;
        for(int direction[]:directions){
            int pointX = row + direction[0];
            int pointY = col + direction[1];
            if(pointX < rows && pointX >= 0
                    && pointY < cols && pointY >=0
                    && matrix[pointX][pointY] > matrix[row][col]) {
                max = Math.max(max, dfs(pointX, pointY, rows, cols, matrix));
            }
        }
        return ++max;
    }

    private static int dfsMemo(int row,int col,int rows,int cols,int matrix[][],int memo[][]){
        if(memo[row][col]!= 0) return memo[row][col];
        int max =0;
        for(int direction[]:directions){
            int pointX = row + direction[0];
            int pointY = col + direction[1];
            if(pointX < rows && pointX >= 0
                    && pointY < cols && pointY >=0
                    && matrix[pointX][pointY] > matrix[row][col]) {
                max = Math.max(max, dfsMemo(pointX, pointY, rows, cols, matrix,memo));
            }
        }
        return memo[row][col]=++max;
    }
}