package career.sixmonthssep.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/number-of-islands/
 */
public class NoOfIsland {
    public static void main(String[] args) {
//        char grid[][]= {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char grid[][]= {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int  m = grid.length;
        int n = grid[0].length;
        boolean visited[][] = new boolean[m][n];
        int island = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]==false && grid[i][j] == '1'){
                    bfs(grid,i,j,visited);
                    island++;
                }
            }
        }
        return island;
    }

    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};
    private static void bfs(char[][]grid,int x,int y,boolean visited[][]){

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[] = queue.poll();
                for(int dir[]:directions){
                    int newPointX = point[0] + dir[0];
                    int newPointY = point[1] + dir[1];
                    if(isNotValidPoint(newPointX,newPointY,grid,visited)){
                        continue;
                    }
                    queue.add(new int[]{newPointX,newPointY});
                    visited[newPointX][newPointY] = true;
                }
            }
        }
    }
    private static boolean isNotValidPoint(int newPointX,int newPointY,char[][]grid,boolean [][]visited){
        return newPointX < 0 || newPointX >= grid.length || newPointY < 0 || newPointY >= grid[0].length
                || visited[newPointX][newPointY] || grid[newPointX][newPointY] == '0';
    }
}
