package career.sixmonthssep.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/01-matrix/description/

 */
public class Matrix01 {
    public static void main(String[] args) {
//        int mat[][] = {{0,0,0},{0,1,0},{0,0,0}};
        int mat[][] = {{0,0,0},{0,1,0},{1,1,1}};

        int res[][]= updateMatrix(mat);
        for(int row[]:res){
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] updateMatrix(int[][] mat) {
        Queue<int[]> points = new LinkedList<>();
        int m = mat.length,n=mat[0].length;
        int res[][]=new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++){
                if(mat[i][j] == 0) {
                    points.add(new int[]{i, j});
                    res[i][j]=0;
                }else{
                    res[i][j]=-1;
                }
            }
        }

        bfs(points,res);
        return res;
    }


    static int DIRECTIONS[][]={{-1,0},{0,-1},{1,0},{0,1}};

    private static void bfs(Queue<int[]> points,int[][]res){
        while(!points.isEmpty()){
            int size= points.size();
            for(int i=0;i<size;i++){
                int point[]=points.poll();

                int pointX = point[0];
                int pointY = point[1];

                for(int dir[]:DIRECTIONS){
                    int newPointX = pointX + dir[0];
                    int newPointY = pointY + dir[1];


                    if(newPointX < 0 || newPointX >= res.length
                            || newPointY < 0 || newPointY >= res[0].length
                            || res[newPointX][newPointY] != -1){
                        continue;
                    }

                    res[newPointX][newPointY] = res[pointX][pointY] + 1;
                    points.add(new int[]{newPointX,newPointY});
                }
            }
        }
    }
}
