package career.sixmonthssep.graph;

import java.util.PriorityQueue;

public class SwimInRisingWater {
    public static void main(String[] args) {
        int grid[][] = {{0,2},{1,3}};
//        int grid[][] = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        System.out.println(swimInWater(grid));
    }
    static int DIR[][]={{-1,0},{0,-1},{1,0},{0,1}};
    public static int swimInWater(int[][] grid) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2) -> p1[2]-p2[2]);
        int n = grid.length,max = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[n][n];
        pq.add(new int[]{0,0,grid[0][0]});
        visited[0][0]=true;
        while(!pq.isEmpty()){
            int point[] = pq.poll();
            int x = point[0];
            int y = point[1];
            int t = point[2];
            if(x == grid.length-1 && y == grid[0].length-1){
                return t;
            }

            for(int dir[]:DIR){
                int newX = x + dir[0];
                int newY = y + dir[1];

                if(newX < 0 || newY < 0 || newX >= grid.length
                    || newY >= grid[0].length || visited[newX][newY]){
                    continue;
                }

                visited[newX][newY] = true;

                pq.add(new int[]{newX,newY,Math.max(t,grid[newX][newY])});
            }
        }

        return 0;
    }
}
