package career.sixmonthssep.graph.practice;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}
        }));
    }

    public static int orangesRotting(int[][] grid) {
        return bfs(grid);
    }
    public static int[][]directions={{-1,0},{0,-1},{1,0},{0,1}};

    public static int bfs(int grid[][]){
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int noOfFreshOrange = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    queue.add(new int[]{i,j});
                }else if(grid[i][j] == 1){
                    noOfFreshOrange++;
                }
            }
        }
        if(noOfFreshOrange == 0 || queue.isEmpty()) return 0;
        int min=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int orange[]=queue.poll();
                for(int dir[]:directions){
                    int newX = dir[0]+orange[0];
                    int newY = dir[1]+orange[1];

                    if(isNotValidOrange(newX,newY,m,n,grid)){
                        continue;
                    }
                    grid[newX][newY]=2;
                    noOfFreshOrange--;
                    queue.add(new int[]{newX,newY});
                }
            }
            min++;
        }
        return noOfFreshOrange == 0 ? min - 1: -1;
    }

    public static boolean isNotValidOrange(int x,int y,int m,int n,int grid[][]){
        return x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1;
    }
}
