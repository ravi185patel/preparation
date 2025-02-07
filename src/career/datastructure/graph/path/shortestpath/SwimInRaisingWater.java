package career.datastructure.graph.path.shortestpath;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/swim-in-rising-water/description/?envType=problem-list-v2&envId=depth-first-search
 */
public class SwimInRaisingWater {
    public static void main(String[] args) {
        int grid[][] = {{1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};

        swimInWater(grid);
    }

    static int DIRECTIONS[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static int swimInWater(int[][] grid) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[2] - p2[2]);
        int m = grid.length, n = grid[0].length;
        boolean visited[][] = new boolean[m][n];


        pq.add(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int point[] = pq.poll();
            int pointX = point[0];
            int pointY = point[1];
            int time = point[2];
            if (pointX == grid.length - 1 && pointY == grid[0].length - 1) {
                return time;
            }
            for (int dir[] : DIRECTIONS) {
                int newPointX = pointX + dir[0];
                int newPointY = pointY + dir[1];

                if (newPointX < 0 || newPointX >= grid.length
                        || newPointY < 0 || newPointY >= grid[0].length
                        || visited[newPointX][newPointY]) {
                    continue;
                }

                visited[newPointX][newPointY] = true;
                pq.add(new int[]{newPointX, newPointY, Math.max(grid[newPointX][newPointY], time)});
            }
        }
        return -1;
    }
}
