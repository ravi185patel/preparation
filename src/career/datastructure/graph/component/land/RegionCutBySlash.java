package career.datastructure.graph.component.land;

/*

Regions Cut By Slashes

1] convert string into matrix
2] no of island approach;

 */

import career.datastructure.graph.template.DisJoinUnion;

import java.util.LinkedList;
import java.util.Queue;

public class RegionCutBySlash {
    static int mat[][];
    static boolean visited[][];

    public static void main(String[] args) {
//        String grid[] = {" /","/ "};
        String []grid = {"/\\","\\/"};
        initData(grid);
        System.out.println(numIslandsBFS(mat));

    }

    private static void initData(String grid[]){
        int length = grid.length;
        int m = length*3;
        mat = new int[m][m];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int baseRow = i * 3;
                int baseCol = j * 3;
                // Check the character in the original grid
                if (grid[i].charAt(j) == '\\') {
                    // Mark diagonal for backslash
                    mat[baseRow][baseCol] = 1;
                    mat[baseRow + 1][baseCol + 1] = 1;
                    mat[baseRow + 2][baseCol + 2] = 1;
                } else if (grid[i].charAt(j) == '/') {
                    // Mark diagonal for forward slash
                    mat[baseRow][baseCol + 2] = 1;
                    mat[baseRow + 1][baseCol + 1] = 1;
                    mat[baseRow + 2][baseCol] = 1;
                }
            }
        }


    }

    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};

    public static int numIslandsBFS(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        boolean visited[][] = new boolean[m][n];
        int island = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j] == false && grid[i][j] == 0){
                    bfs(i,j,grid,visited);
                    island++;
                }
            }
        }
        return island;
    }
    private static void bfs(int x,int y,int[][]grid,boolean visited[][]){
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});


        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[]=queue.poll();

                for(int dir[]:directions){
                    int newX = dir[0] + point[0];
                    int newY = dir[1] + point[1];

                    if(newX >= m || newX < 0 || newY >= n || newY < 0 || visited[newX][newY] || grid[newX][newY] == 1){
                        continue;
                    }

                    visited[newX][newY] = true;
                    queue.add(new int[]{newX,newY});
                }
            }
        }
    }
}
