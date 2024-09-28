package career.datastructure.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class NoOfDistinctIsland {
    public static void main(String[] args) {
        int arr[][]={
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        };
        NoOfDistinctIsland no=new NoOfDistinctIsland();
        int res = no.countDistinctIslands(arr);
        System.out.println(res);
    }

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        int count =0;
        int m = grid.length;
        int n = grid[0].length;
        HashSet <ArrayList< String >> st = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    ArrayList < String > vec = new ArrayList < > ();
                    dfs(i,j,i,j,m,n,vec,grid);
                    System.out.println(vec);
                    st.add(vec);
                }
            }
        }

        return st.size();
    }

    private String toString(int r, int c) {
        return Integer.toString(r) + " " + Integer.toString(c);
    }

    static int dx[]={-1, 0, +1, 0};
    static int dy[]={0, -1, 0, +1};

    private void dfs(int x,int y,int px,int py,int m,int n,
                     ArrayList < String > vec,int grid[][]){
        grid[x][y] = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int node[] = queue.remove();
            x = node[0];
            y = node[1];

            vec.add(toString(x-px,y-py));
            for(int i=0;i<4;i++){
                int cx = x+dx[i];
                int cy = y+dy[i];

                if(cx < 0 || cx >= m || cy < 0 || cy >= n || grid[cx][cy] != 1){
                    continue;
                }

                grid[cx][cy] = -1;
                queue.add(new int[]{cx,cy});
            }
        }
    }
}
