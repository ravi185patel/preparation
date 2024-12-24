package career.datastructure.graph.other;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
//        int grid[][] = {{0,1},{1,0}};
        int grid[][] = {{0,0,0},{1,1,0},{1,1,0}};;
        ShortestPathInBinaryMatrix obj = new ShortestPathInBinaryMatrix();
        int res = obj.shortestPathBinaryMatrix(grid);
        System.out.println(res);
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1) return -1;
        int length =0;
        int m = grid.length;
        int n = grid[0].length;
        int distance[][] = new int[n][m];

        for(int i[]:distance){
            Arrays.fill(i,(int)(1e9));
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        distance[0][0]=1;

        int dx[]={0,0,-1,1,1,-1,-1,1};
        int dy[]={-1,1,0,0,1,-1,1,-1};

        while(!queue.isEmpty()){
            int node[]=queue.remove();
            int x = node[0];
            int y = node[1];

            if(x == n-1 && y == m-1){
                return distance[n-1][m-1];
            }

            for(int i=0;i<dx.length;i++){
                int px = dx[i]+x;
                int py = dy[i]+y;
                if(px < 0 || px >= grid.length || py < 0 || py >=grid[px].length
                        || grid[px][py] != 0){
                    continue;
                }
//                System.out.println(distance[x][y]+" <> "+distance[px][py]);
                if(distance[x][y] + 1 < distance[px][py]){
                    distance[px][py] = distance[x][y] + 1;
                }

                //  distance[px][py] = len+1;
                grid[px][py]=-1;
                queue.add(new int[]{px,py});
            }
        }

        return -1;
    }
}
