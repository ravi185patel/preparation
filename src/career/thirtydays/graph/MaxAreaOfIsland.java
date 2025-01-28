package career.thirtydays.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/max-area-of-island/description/
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int  grid[][] = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};

        System.out.println(maxAreaOfIsland(grid));
    }
    public static int maxAreaOfIsland(int[][] grid) {
        int maxLand = 0;
        int m = grid.length;
        int n = grid[0].length;

        boolean visited[][] = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j] == false && grid[i][j] == 1){
                    maxLand = Math.max(maxLand,findLandBfs(i,j,grid,visited));
                }
            }
        }
        return maxLand;
    }

    static int direction[][]={{-1,0},{0,-1},{0,1},{1,0}};

    private static int findLandBfs(int x, int y,int[][]grid,boolean visited[][]){
        int landArea =0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y]=true;
        while(!queue.isEmpty()){
            int point[] = queue.poll();
            landArea++;
            for(int dir[]:direction){
                int newPointX = point[0]+dir[0];
                int newPointY = point[1]+dir[1];
                if(isNotValidPoint(newPointX,newPointY,grid,visited)){
                    continue;
                }
                visited[newPointX][newPointY]=true;
                queue.add(new int[]{newPointX,newPointY});
            }
        }
        return landArea;
    }

    private static boolean isNotValidPoint(int newPointX,int newPointY,int[][]grid,boolean visited[][]){
        return newPointX < 0 || newPointX >= grid.length || newPointY < 0 || newPointY >= grid[0].length
                || visited[newPointX][newPointY]
                || grid[newPointX][newPointY] != 1 ;
    }
}
