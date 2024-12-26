package career.datastructure.graph.dfsbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*

clarification question
1] direction like 4/8 directions:
2] What will be range and matrix(m*n/ n*n)

Approach
1] dfs => tc (m*n) sc = (m*n)
2] bfs => tc (m*n) sc = (m*n)
 */
public class WallsAndGates {
    static int distances[][];
    static int m,n;
    public static void main(String[] args) {
        int [][] rooms = {{2147483647,-1,0,2147483647}, {2147483647,2147483647,2147483647,-1}, {2147483647,-1,2147483647,-1}, {0,-1,2147483647,2147483647}};
//        int [][] rooms = {{-1}};
        m = rooms.length;
        n = rooms[0].length;
        distances=new int[m][n];
        for(int distance[]:distances){
            Arrays.fill(distance,2147483647);
        }

        /*  dfs */
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rooms[i][j] == 0){
                    rooms[i][j] = 2147483647;
                    dfs(rooms,i,j,0);
//                    bfs(rooms,i,j,0);
                    rooms[i][j] = 0;
                }else if(rooms[i][j] == -1){
                    distances[i][j]=-1;
                }
            }
        }
        /*dfs end*/

        /* bfs start */

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rooms[i][j] == 0){
                    queue.add(new int[]{i,j,1});
                    distances[i][j]=0;
                }else if(rooms[i][j] == -1){
                    distances[i][j]=-1;
                }
            }
        }

        bfs(rooms,queue);
        /* bfs end */
        for(int distance[]:distances){
            System.out.println(Arrays.toString(distance));
        }
    }

        private static void dfs(int rooms[][],int x,int y,int steps){
            if(x < 0 || y < 0 || x >= m || y >= n || rooms[x][y] != 2147483647){
                return;
            }
            if(distances[x][y] <= steps){
                return;
            }
            distances[x][y]=Math.min(distances[x][y],steps);
    //        System.out.println(distances[x][y]);
            dfs(rooms,x+1,y,steps+1);
            dfs(rooms,x,y+1,steps+1);
            dfs(rooms,x-1,y,steps+1);
            dfs(rooms,x,y-1,steps+1);
        }

        private static void bfs(int rooms[][],Queue<int[]> queue){
           while(!queue.isEmpty()){
               int point[]=  queue.poll();
               int steps = point[2];

               for(int dir[]:new int[][]{{-1,0},{0,-1},{1,0},{0,1}}){
                   int x = point[0]+dir[0];
                   int y = point[1]+dir[1];

                   if(x < 0 || y < 0 || x >= m || y >= n || rooms[x][y] != 2147483647){
                       continue;
                   }
                   if(distances[x][y] <= steps){
                       continue;
                   }
                   distances[x][y]=Math.min(distances[x][y],steps);
                   queue.add(new int[]{x,y,steps+1});
               }
           }
        }
}
