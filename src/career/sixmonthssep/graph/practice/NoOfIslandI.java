package career.sixmonthssep.graph.practice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
https://leetcode.com/problems/number-of-islands/
 */
public class NoOfIslandI {
    public static void main(String[] args) {
//        char grid[][]= {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char grid[][]= {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','0','1','1'},
                {'0','0','0','1','1'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int  m = grid.length;
        int n = grid[0].length;
        boolean visited[][] = new boolean[m][n];
        int island = 0;
        Set<Set<String>> set = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]==false && grid[i][j] == '1'){ // fault
                    Set<String> current = new HashSet<>();
                    dfsOnGrid(i,j,i,j,grid,visited,current);
//                    bfsOnGrid(i,j,grid,visited,current);
                    set.add(current);
                }
            }
        }
        return set.size();
    }

    static int directions[][]={{-1,0},{0,1},{0,-1},{1,0}}; // fault only consider down and right;

    private static void dfsOnGrid(int cellX,int cellY,int originCellX,int originCellY,char[][]grid,boolean visited[][],Set<String> current){ // fault no need to return anything
        if(visited[cellX][cellY]) return ;
        visited[cellX][cellY] = true;
        current.add((cellX-originCellX)+","+(cellY-originCellY));
        for(int dir[]:directions){
            int newCellX = cellX + dir[0];
            int newCellY = cellY + dir[1];

            if(isNotValidPoint(newCellX,newCellY,grid,visited)){
                continue;
            }
            dfsOnGrid(newCellX,newCellY,originCellX,originCellY,grid,visited,current);
        }
    }

    private static void bfsOnGrid(int cellX,int cellY,char[][]grid,boolean visited[][],Set<String> current){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{cellX,cellY});
        visited[cellX][cellY] = true;
        current.add((cellX-cellX)+","+(cellY-cellY));
        while(!queue.isEmpty()){
              int point[] = queue.poll();
              for(int dir[]:directions){
                  int newCellX = point[0]+dir[0];
                  int newCellY = point[1]+dir[1];
                  if(isNotValidPoint(newCellX,newCellY,grid,visited)){
                      continue;
                  }
                  current.add((newCellX-cellX)+","+(newCellY-cellY));
                  visited[newCellX][newCellY] = true;
                  queue.add(new int[]{newCellX,newCellY});
              }
        }
    }
    private static boolean isNotValidPoint(int newPointX,int newPointY,char[][]grid,boolean [][]visited){
        return newPointX < 0 || newPointX >= grid.length || newPointY < 0 || newPointY >= grid[0].length
                || visited[newPointX][newPointY] || grid[newPointX][newPointY] == '0';
    }
}
