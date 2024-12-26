package career.datastructure.graph.path;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*

same approach as in MAZE I
need to return shortest path.

 */

public class TheMazeII {
    static int distance[][];
    public static void main(String[] args) {
        int maze[][] = {{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}},
        start[] = {0,4}, destination []= {4,4};

//        int maze[][] = {{0,0,0,0,0}, {1,1,0,0,1}, {0,0,0,0,0}, {0,1,0,0,1}, {0,1,0,0,0}},
//                start[] = {4,3}, destination[] = {0,1};

//        int maze[][]={
//                {0,0,1,0,0},
//                {0,0,0,0,0},
//                {0,0,0,1,0},
//                {1,1,0,1,1},
//                {0,0,0,0,0}
//        },
//                start[]={0,4},
//                destination[]={1,2};

        System.out.println(hasPathBfs(maze,start,destination));
        System.out.println(hasPathDfs(maze,start,destination));
    }

    static int directions[][]={{-1,0},{0,-1},{0,1},{1,0}};
    static boolean visited[][];
    static int m,n;

    public static boolean hasPathBfs(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        boolean[][] visited = new boolean[row][col];
        return dfs(maze, start, destination, visited);
    }
    public static int hasPathDfs(int[][] maze, int[] start, int[] destination) {
        distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        Cell cell = new Cell(start[0], start[1]);
        distance[start[0]][start[1]]= 0;
        Queue<Cell> q = new LinkedList<>();
        q.add(cell);

        while (!q.isEmpty()){
            Cell cur = q.poll();

            for(int[] direction : directions) {
                int newX = cur.x;
                int newY = cur.y;

                int count=0;

                while(isValid(maze, newX+direction[0], newY+direction[1])){
                    newX+=direction[0];
                    newY+=direction[1];
                    count++;
                }
                if(distance[start[0]][start[1]] + count + 1 < distance[newX][newY] ) {
                    distance[newX][newY] =distance[start[0]][start[1]] + count;
                        q.add(new Cell(newX, newY));
                }
            }
        }

        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1:
                distance[destination[0]][destination[1]];
    }

    private static boolean isValid(int[][] maze, int x, int y) {
        return x >= 0 && y >= 0 && x<maze.length && y<maze[0].length && maze[x][y]==0;
    }

    private static boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        int x = start[0];
        int y = start[1];
        if (x == destination[0] && y == destination[1]) {
            return true;
        }
        visited[x][y] = true;

        for (int[] dir : dirs) {
            int newx = x;
            int newy = y;
            while (isValid(newx + dir[0], newy + dir[1], maze) &&
                    maze[newx + dir[0]][newy + dir[1]] == 0) {
                newx = newx + dir[0];
                newy = newy + dir[1];
            }
            if (!visited[newx][newy]) {
                int[] next = new int[]{newx, newy};
                if (dfs(maze, next, destination, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValid(int x, int y, int[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }
}
