package career.sixmonthssep.graph.practice;

import career.common.CommonUtil;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {
    public static void main(String[] args) {
        CommonUtil.print(updateMatrix(new int[][]{
                {0,0,0},{0,1,0},{0,0,0}
        }));
        CommonUtil.print(updateMatrix(new int[][]{
                {0,0,0},{0,1,0},{1,2,1}
        }));
        CommonUtil.print(updateMatrix(new int[][]{
                {0,1,0},
                {0,1,0},
                {0,1,0},
                {0,1,0},
                {0,1,0}
        }));
    }

    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        boolean visited[][]=new boolean[m][n];
        Queue<int[]> queue = getAllSourcePoints(m,n,mat,visited);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int cell[]=queue.poll();
                int cellX = cell[0];
                int cellY = cell[1];
//                int cellStep = cell[2];
                for(int dir[]:CommonUtil.dir){
                    int newCellX = cellX+dir[0];
                    int newCellY = cellY+dir[1];
//                    if(isValid(m,n,newCellX,newCellY,mat,visited)){
                    if(isValid(m,n,newCellX,newCellY,mat)){
                        mat[newCellX][newCellY] = mat[cellX][cellY]+1;
                        queue.add(new int[]{newCellX,newCellY});
//                        visited[newCellX][newCellY]=true;
//                        mat[newCellX][newCellY] = cellStep + 1;
//                        queue.add(new int[]{newCellX,newCellY,cellStep+1});
                    }
                }
            }
        }
        return mat;
    }

    public static boolean isValid(int m,int n,int sr,int sc,int mat[][],boolean visited[][]){
        return sr >= 0 && sr < m && sc >=0 && sc < n && mat[sr][sc] == 1 && visited[sr][sc]==false;
    }
    public static boolean isValid(int m,int n,int sr,int sc,int mat[][]){
        return sr >= 0 && sr < m && sc >=0 && sc < n && mat[sr][sc] == -1;
    }
    public static Queue<int[]> getAllSourcePoints(int m ,int n,int[][]mat,boolean visited[][]){
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j =0;j<n;j++){
                if(mat[i][j]==0){
                    queue.add(new int[]{i,j});
//                    queue.add(new int[]{i,j,0});
//                    visited[i][j]=true;
                }else{
                    mat[i][j]=-1;
                }
            }
        }
        return queue;
    }
}
