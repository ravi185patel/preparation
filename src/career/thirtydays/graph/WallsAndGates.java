package career.thirtydays.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*

 */
public class WallsAndGates {
    public static void main(String[] args) {
        int rooms[][] = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        wallsAndGates(rooms);
    }

    static int CONST_VAL = 2147483647;
    static int DIRECTIONS [][]={{-1,0},{0,-1},{1,0},{0,1}};

    public static void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        int m = rooms.length;
        int n = rooms[0].length;

        int distance[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rooms[i][j] == 0){
                    queue.add(new int[]{i,j});
                }
                distance[i][j]=rooms[i][j];
            }
        }


        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[]=queue.poll();
                int pointX = point[0];
                int pointY = point[1];
                for(int dir[]:DIRECTIONS){
                    int newPointX = pointX + dir[0];
                    int newPointY = pointY + dir[1];
                    if(isNotValidPoint(newPointX,newPointY,rooms)){
                        continue;
                    }
                    if(distance[pointX][pointY] + 1 < distance[newPointX][newPointY]){
                        System.out.println((distance[pointX][pointY])+" = in if distance = "+distance[newPointX][newPointY]);
                         distance[newPointX][newPointY] = distance[pointX][pointY] +1;
                         queue.add(new int[]{newPointX,newPointY});
                    }
                }
            }
        }

        for(int row[]:distance){
            System.out.println(Arrays.toString(row));
        }

    }

    private static boolean isNotValidPoint(int newPointX,int newPointY,int[][]grid){
        return newPointX < 0 || newPointX >= grid.length || newPointY < 0 || newPointY >= grid[0].length
                || grid[newPointX][newPointY] != CONST_VAL ;
    }
}
