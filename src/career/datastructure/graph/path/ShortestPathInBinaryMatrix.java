package career.datastructure.graph.path;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    static Map<Integer, List<Integer>> graph;

    public static void main(String[] args) {
        int grid[][] = {{0,0,0}, {1,1,0}, {1,1,0}};
        int m = grid.length;
        int n = grid[0].length;
        System.out.println(dfs(grid,n,m));
    }

    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1},{1,1},{-1,-1},{-1,1},{1,-1}};

    public static int dfs(int grid[][],int n,int m){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1});
        boolean visited[][]=new boolean[n][m];
        visited[0][0]=true;
        while(!queue.isEmpty()){
            int cell[]=queue.remove();

            if(cell[0] == m-1 && cell[1]==n-1){
                return cell[2];
            }

            for(int dir[]:directions){
                int xPoint = cell[0] + dir[0];
                int yPoint = cell[1] + dir[1];
                int dis = cell[2] + 1;
                if(xPoint >= m || yPoint >= n || xPoint < 0 || yPoint < 0
                        ||visited[xPoint][yPoint] || grid[xPoint][yPoint] == 1){
                    continue;
                }
                visited[xPoint][yPoint] = true;
                queue.add(new int[]{xPoint,yPoint,dis});
            }
        }

        return -1;
    }
}
