package career.sixmonthssep.graph;

import java.util.*;

/*
https://leetcode.com/problems/pacific-atlantic-water-flow/editorial/
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        int heights[][] = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic(heights));
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean pacificVisited[][]=new boolean[m][n];
        boolean atlanticVisited[][]=new boolean[m][n];

        Queue<int[]>pacificQueue = new LinkedList<>();
        Queue<int[]>atlanticQueue = new LinkedList<>();

        for(int i=0;i<m;i++){
            pacificQueue.add(new int[]{i,0});
            pacificVisited[i][0] = true;
            atlanticQueue.add(new int[]{i,n-1});
            atlanticVisited[i][n-1] = true;
        }

        for(int i=0;i<n;i++){
            pacificQueue.add(new int[]{0,i});
            pacificVisited[0][i] = true;
            atlanticQueue.add(new int[]{m-1,i});
            atlanticVisited[m-1][i] = true;
        }

        bfs(pacificQueue,heights,pacificVisited);
        bfs(atlanticQueue,heights,atlanticVisited);

        for(int row=0;row<m;row++ ){
            for(int col=0;col<n;col++){
                if(pacificVisited[row][col] && atlanticVisited[row][col]){
                    res.add(Arrays.asList(row,col));
                }
            }
        }

        return res;
    }


    static int DIRECTIONS [][]={{-1,0},{0,-1},{1,0},{0,1}};

    private static boolean isNotValidPoint(int newPointX,int newPointY,
                                           int pointX,int pointY,
                                           int[][]grid,boolean visited[][]){
        return newPointX < 0 || newPointX >= grid.length
                || newPointY < 0 || newPointY >= grid[0].length
                || visited[newPointX][newPointY]
                || grid[newPointX][newPointY] < grid[pointX][pointY];
    }
    private static void bfs(Queue<int[]> queue,int heights[][],boolean visited[][]){
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[]=queue.poll();
                int pointX = point[0];
                int pointY = point[1];
//                visited[pointX][pointY] = true;
                for(int dir[]:DIRECTIONS){
                    int newPointX = pointX + dir[0];
                    int newPointY = pointY + dir[1];
                    if(isNotValidPoint(newPointX,newPointY,pointX,pointY,heights,visited)){
                        continue;
                    }
//                    if(distance[pointX][pointY] + 1 < distance[newPointX][newPointY]){
//                        System.out.println((distance[pointX][pointY])+" = in if distance = "+distance[newPointX][newPointY]);
//                        distance[newPointX][newPointY] = distance[pointX][pointY] +1;
//                        queue.add(new int[]{newPointX,newPointY});
//                    }
                    queue.add(new int[]{newPointX,newPointY});
                    visited[newPointX][newPointY] = true;
                }
            }
        }
    }
}
