package career.datastructure.graph.path;

/*

Clarification question
1] is any corner or side is open in grid ?
2] is it possible ball can travel in same direction where it came ?

Important points
1] ball can't move in other direction after get in goal corner.
  -> corner should be cover by three side means one side opened.

2] ball can go through the goal corner if any  side of is opened.


Approach
1] DFS: 
2] BFS
3] union set.

our logic not work so copy solution and try to understand how it work
 */

import java.util.LinkedList;
import java.util.Queue;

class Cell {
    int x;
    int y;
    Cell(int x, int y){
        this.x=x;
        this.y=y;
    }
}

public class TheMaze {
    public static void main(String[] args) {
//        int maze[][] = {{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}},
//        start[] = {0,4}, destination []= {4,4};

//        int maze[][] = {{0,0,0,0,0}, {1,1,0,0,1}, {0,0,0,0,0}, {0,1,0,0,1}, {0,1,0,0,0}},
//                start[] = {4,3}, destination[] = {0,1};

        int maze[][]={
                     {0,0,1,0,0},
                     {0,0,0,0,0},
                     {0,0,0,1,0},
                     {1,1,0,1,1},
                     {0,0,0,0,0}
                    },
        start[]={0,4},
        destination[]={1,2};

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
    public static boolean hasPathDfs(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        Cell cell = new Cell(start[0], start[1]);
        Queue<Cell> q = new LinkedList<>();
        q.add(cell);

        while (!q.isEmpty()){
            Cell cur = q.poll();
            visited[cur.x][cur.y]=true;

            if(cur.x==destination[0] && cur.y==destination[1]) return true;

            for(int[] direction : directions) {
                int newX = cur.x;
                int newY = cur.y;

                while(isValid(maze, newX+direction[0], newY+direction[1])){
                    newX+=direction[0];
                    newY+=direction[1];
                }

                if(!visited[newX][newY]) q.add(new Cell(newX, newY));
            }
        }

        return false;
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
