package career.datastructure.graph.component;

import java.util.*;

/*
https://leetcode.com/problems/pacific-atlantic-water-flow/description/
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        List<List<Integer>> res = new ArrayList<>();

        boolean pacific[][]=new boolean[m][n];
        boolean atlantic[][]=new boolean[m][n];
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanicQueue = new LinkedList<>();


        for(int row=0;row<m;row++ ){
            pacificQueue.add(new int[]{row,0});
            atlanicQueue.add(new int[]{row,n-1});
            pacific[row][0]= true;
            atlantic[row][n-1]= true;
        }

        for(int col=0;col<n;col++ ){
            pacificQueue.add(new int[]{0,col});
            atlanicQueue.add(new int[]{m-1,col});
            pacific[0][col]= true;
            atlantic[m-1][col]= true;
        }


        traverse(m,n,pacificQueue,pacific,heights);
        traverse(m,n,atlanicQueue,atlantic,heights);

        for(int row=0;row<m;row++ ){
            for(int col=0;col<n;col++){
                if(pacific[row][col] && atlantic[row][col]){
                    res.add(Arrays.asList(row,col));
                }
            }
        }

        return res;
    }

    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};

    private static void traverse(int m,int n,Queue<int[]> queue,boolean visited[][],int heights[][]){

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[] =  queue.poll();
                int px = point[0];
                int py = point[1];
                int ht = heights[px][py];
                visited[px][py]  = true;
                for(int dir[]:directions){
                    int newPx = dir[0] + px;
                    int newPy = dir[1] + py;
                    if( newPx < 0 || newPx >= m || newPy < 0 || newPy >= n
                            || visited[newPx][newPy]
                            || ht > heights[newPx][newPy]){
                        continue;
                    }

                    visited[newPx][newPy] = true;
                    queue.add(new int[]{newPx,newPy});
                }
            }
        }
    }
}
