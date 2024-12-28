package career.datastructure.graph.component.land;

import career.datastructure.graph.template.DisJoinUnion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Max Area of Island

1] find island and count each node and take maxarea

Approach
1] dfs
1.1] dfs return value
2] bfs
3] union set -> not working -> need to understand

 */
public class MaxAreaOfIsland {
    static int m,n,maxIsland=0;
    static int island=0;
    static boolean visited[][];
    public static void main(String[] args) {
        int[][]grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0}, {0,0,0,0,0,0,0,1,1,1,0,0,0}, {0,1,1,0,1,0,0,0,0,0,0,0,0}, {0,1,0,0,1,1,0,0,1,0,1,0,0}, {0,1,0,0,1,1,0,0,1,1,1,0,0}, {0,0,0,0,0,0,0,0,0,0,1,0,0}, {0,0,0,0,0,0,0,1,1,1,0,0,0}, {0,0,0,0,0,0,0,1,1,0,0,0,0}};
//        int[][] grid = {{0,0,0,0,0,0,0,0}};
        m= grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];
        unionSet(grid);
        System.out.println(maxIsland);
        visited = new boolean[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j] == false && grid[i][j] == 1){
//                    dfs(i,j,grid);
//                    maxIsland = Math.max(maxIsland,island);
//                    island = 0;

//                    maxIsland = Math.max(maxIsland,dfsRet(i,j,grid));

                    maxIsland = Math.max(maxIsland,bfs(i,j,grid));
                }

            }
        }
        System.out.println(maxIsland);
    }

    private static int unionSet(int grid[][]){
        DisJoinUnion ds = new DisJoinUnion(m*n+1);
        Arrays.fill(ds.size,1);

        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if (
//                        visited[i][j] == false &&
                        grid[i][j] == 1) {
                    int nodeNo = i*m+j;
                    for(int dir[]:new int[][]{{-1,0},{0,-1},{1,0},{0,1}}){
                        int x = dir[0]+ i;
                        int y = dir[1] + j;
                        int newNodeNo = x*m+y;
                        if(x < 0 || y < 0 || x >= m || y >= n
//                                || visited[x][y]
                                || grid[x][y] == 0){
                            continue;
                        }
//                        visited[x][y] = true;
                        if(ds.find(nodeNo) != ds.find(newNodeNo)){
                            ds.unionBySize(nodeNo,newNodeNo);
                        }
                    }
                }
                System.out.println(Arrays.toString(ds.parent));
                System.out.println(Arrays.toString(ds.size));
            }
        }

        for(int i=0;i<m*n;i++){
            maxIsland = Math.max(maxIsland,ds.size[i]);
        }

        return maxIsland;
    }

    private static void dfs(int x,int y, int grid[][]){
        if(x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || grid[x][y] == 0){
            return ;
        }
        visited[x][y]= true;
        island++;
        dfs(x+1,y,grid);
        dfs(x,y+1,grid);
        dfs(x-1,y,grid);
        dfs(x,y-1,grid);
    }

    private static int dfsRet(int x,int y, int grid[][]){
        if(x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || grid[x][y] == 0){
            return 0;
        }
        visited[x][y]= true;
        int left = dfsRet(x+1,y,grid);
        int right = dfsRet(x,y+1,grid);
        int up =dfsRet(x-1,y,grid);
        int down =dfsRet(x,y-1,grid);
        int totalNodes = left+right+up+down;
        if(grid[x][y] == 1){
            totalNodes+=1;
        }
        return totalNodes;
    }

    private static int bfs(int x,int y,int grid[][]){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        int land=0;
        while(!queue.isEmpty()){
            int node[]=queue.poll();
            land++;// count (1 cover)
            for(int dir[]:new int[][]{{-1,0},{0,-1},{1,0},{0,1}}){
                x = dir[0]+node[0];
                y = dir[1] + node[1];

                if(x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || grid[x][y] == 0){
                    continue;
                }
                visited[x][y] = true;
                queue.add(new int[]{x,y});

            }
        }

        return land;
    }

}
