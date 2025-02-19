package career.sixmonthssep.graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumEnclaves {
    public static void main(String[] args) {
//        int grid[][] = {{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
        int grid[][] =
                {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
//                {{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};

        System.out.println(numEnclaves(grid));
    }

    static int DIRECTIONS [][]={{-1,0},{0,-1},{1,0},{0,1}};
    public static int numEnclaves(int[][] grid) {
        return numEnclavesBFS(grid);
    }
    public static int numEnclavesBFS(int[][] grid) {
        int m = grid.length,n=grid[0].length;
        boolean visited[][]=new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            if(grid[i][0] == 1){
                queue.add(new int[]{i,0});
                visited[i][0] = true;
            }
            if(grid[i][n-1]==1){
                queue.add(new int[]{i,n-1});
                visited[i][n-1] = true;
            }
        }


        for(int i=0;i<n;i++){
            if(grid[0][i] == 1){
                queue.add(new int[]{0,i});
                visited[0][i] = true;
            }
            if(grid[m-1][i]==1){
                queue.add(new int[]{m-1,i});
                visited[m-1][i] = true;
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[]=queue.poll();
                int pointX = point[0];
                int pointY = point[1];

                for(int dir[]:DIRECTIONS){
                    int newPointX = pointX + dir[0];
                    int newPointY = pointY + dir[0];

                    if(newPointX < 0 || newPointX >= grid.length
                        || newPointY < 0 || newPointY >= grid[0].length
                        || visited[newPointX][newPointY]
                        || grid[newPointX][newPointY] != 1){
                        continue;
                    }
                    visited[newPointX][newPointY] = true;
                    queue.add(new int[]{newPointX,newPointY});
                }
            }
        }
        int lands = 0;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(visited[i][j] == false && grid[i][j] == 1){
                    lands++;
                }
            }
        }
        return lands;
    }
    public static int numEnclavesDfs(int[][] grid) {
        int m = grid.length,n=grid[0].length;
        boolean visited[][]=new boolean[m][n];

        for(int i=0;i<m;i++){
            if(grid[i][0] == 1){
                dfs(i,0,grid,visited);
            }
            if(grid[i][n-1]==1){
                dfs(i,0,grid,visited);
            }
        }


        for(int i=0;i<n;i++){
            if(grid[0][i] == 1){
                dfs(0,i,grid,visited);
            }
            if(grid[m-1][i]==1){
                dfs(m-1,i,grid,visited);
            }
        }

//        for(boolean row[]:visited){
//            System.out.println(Arrays.toString(row));
//        }
        int lands = 0;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(visited[i][j] == false && grid[i][j] == 1){
                    lands++;
//                    maxLand = Math.max(maxLand,dfs(i,j,grid,visited));
                }
            }
        }
        return lands;
    }

    private static int dfs(int x,int y,int grid[][],boolean visited[][]){
        if(x < 0 || x >= grid.length || y < 0 || y >=grid[0].length
            || grid[x][y] == 0 || visited[x][y]){
            return 0;
        }
        visited[x][y]=true;
        int left = dfs(x+1,y,grid,visited);
        int right = dfs(x,y+1,grid,visited);
        int up =dfs(x-1,y,grid,visited);
        int down =dfs(x,y-1,grid,visited);
        return Math.max(left,Math.max(right,Math.max(up,down)))+1;
    }
}
