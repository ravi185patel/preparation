package career.sixmonthssep.graph.practice;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/number-of-islands/
 */
public class MaxOfIsland {
    public static void main(String[] args) {
//        char grid[][]= {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};
//        int grid[][]= {{1,1,0,0,0},
//                        {1,1,0,0,0},
//                        {0,0,1,0,0},
//                        {0,0,0,1,1}
//        };
        
        int grid[][]={
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
//        int grid[][]={
//                {0,0,0,0,0,0,0,0,0,0,0,0,0}
//        };
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int  m = grid.length;
        int n = grid[0].length;
        boolean visited[][] = new boolean[m][n];
        int island = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]==false && grid[i][j] == 1){ // fault
                    island = Math.max(island,dfsOnGrid(i,j,grid,visited));
                    island = Math.max(island,bfsOnGrid(i,j,grid,visited));
                }
            }
        }
        return island;
    }

    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}}; // fault only consider down and right;

    private static int dfsOnGrid(int cellX,int cellY,int[][]grid,boolean visited[][]){ // fault no need to return anything
        if(visited[cellX][cellY]) return 1;
        visited[cellX][cellY] = true;
        int noOfPoints=1;
        for(int dir[]:directions){
            int newCellX = cellX + dir[0];
            int newCellY = cellY + dir[1];

            if(isNotValidPoint(newCellX,newCellY,grid,visited)){
                continue;
            }
            noOfPoints+=dfsOnGrid(newCellX,newCellY,grid,visited);
        }
        return noOfPoints;
    }

    private static int bfsOnGrid(int cellX,int cellY,int[][]grid,boolean visited[][]){

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{cellX,cellY});
        visited[cellX][cellY] = true;
        int noOfPoints=0;
        while(!queue.isEmpty()){
              int point[] = queue.poll();
              noOfPoints++;
              for(int dir[]:directions){
                  int newCellX = point[0]+dir[0];
                  int newCellY = point[1]+dir[1];
                  if(isNotValidPoint(newCellX,newCellY,grid,visited)){
                      continue;
                  }
                  visited[newCellX][newCellY] = true;
                  queue.add(new int[]{newCellX,newCellY});
              }
        }
        return noOfPoints;
    }
    private static boolean isNotValidPoint(int newPointX,int newPointY,int[][]grid,boolean [][]visited){
        return newPointX < 0 || newPointX >= grid.length || newPointY < 0 || newPointY >= grid[0].length
                || visited[newPointX][newPointY] || grid[newPointX][newPointY] == 0;
    }
}
