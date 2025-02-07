package career.thirtydays.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/map-of-highest-peak/description/
 */
public class MapOfHighestPeak {
    public static void main(String[] args) {
//        int isWater[][] = {{0,1},{0,0}};
        int isWater[][] = {{0,0,1},{1,0,0},{0,0,0}};
        int res[][] = highestPeak(isWater);
        for(int row[]:res) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int res[][]=new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(isWater[i][j] == 1){
                    res[i][j]=0;
                    queue.add(new int[]{i,j});
                }else{
                    res[i][j] = -1;
                }
            }
        }

        bfs(queue,res);
        return res;
    }

    static int DIRECTIONS[][]={{-1,0},{0,-1},{1,0},{0,1}};

    private static void bfs(Queue<int[]> queue,int res[][]){
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[]= queue.poll();
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
                    queue.add(new int[]{newPointX,newPointY});
                }
            }
        }
    }
}
