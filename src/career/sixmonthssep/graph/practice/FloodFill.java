package career.sixmonthssep.graph.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    public static void main(String[] args) {
        int res[][]=floodFill(new int[][]{
                {1,1,1},
                {1,1,0},
                {1,0,1}
        },1,1,2);

        for(int row[]:res){
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        boolean visited[][]=new boolean[m][n];
//        DFS(image,sr,sc,image[sr][sc],color,visited);
        bfs(image,sr,sc,color,visited);
        return image;
    }

    public static int directions[][] = {{0,1},{1,0},{-1,0},{0,-1}};
    public static int[][] bfs(int[][] image, int sr, int sc, int color,boolean visited[][]) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr,sc,image[sr][sc]});
        image[sr][sc]=color;
        visited[sr][sc]=true;

        while(!queue.isEmpty()){
            int cell[] = queue.poll();
            int cellColor = cell[2];
            for(int dir[]:directions){
                int newCellX = cell[0]+dir[0];
                int newCellY = cell[1]+dir[1];

                if(isNotValidCell(newCellX,newCellY,image,cellColor,visited)){
                    continue;
                }
                int prevColor = image[newCellX][newCellY];
                queue.add(new int[]{newCellX, newCellY,prevColor });
                image[newCellX][newCellY] = color;
                visited[newCellX][newCellY] = true;
            }
        }
        return image;
    }

    public static void DFS(int[][] image, int sr, int sc, int color,int newColor,boolean visited[][]) {
        if(isNotValidCell(sr,sc,image,color,visited)){
            return;
        }
        image[sr][sc]=newColor;
        visited[sr][sc] = true;
        DFS(image,sr+1,sc,color,newColor,visited);
        DFS(image,sr,sc+1,color,newColor,visited);
        DFS(image,sr-1,sc,color,newColor,visited);
        DFS(image,sr,sc-1,color,newColor,visited);
    }


    public static boolean isNotValidCell(int x,int y, int image[][],int color,boolean visited[][]){
        return x < 0 || y < 0 || x >= image.length || y >= image[0].length
                || image[x][y] != color
                || visited[x][y];
    }
}
