package career.datastructure.graph.path;

import java.util.Arrays;
import java.util.PriorityQueue;

class Tuple{
    int row,col,distance;
    public Tuple(int row,int col,int distance){
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}

/*
  same approach as in maze but here we have not uniform weight
  so we used pq
  here we check diff and used min diff to get point.
 */
public class PathWithMinimumEffort {
    public static void main(String[] args) {
//        int heights[][] = {{1,2,2}, {3,8,2}, {5,3,5}};
        int heights[][] = {{1,2,3}, {3,8,4}, {5,3,5}};
        System.out.println(minimumEffortPath(heights));
    }

    public static int minimumEffortPath(int[][] heights) {
        int minEfforts = 0;

        PriorityQueue<Tuple> pq = new PriorityQueue<>((t1, t2) -> t1.distance - t2.distance);
        pq.add(new Tuple(0,0,0));

        int m = heights.length;
        int n = heights[0].length;
        int distance[][] = new int[m][n];
        for(int i[]:distance){
            Arrays.fill(i,(int)1e9);
        }

        int dx[]={0,0,-1,1};
        int dy[]={-1,1,0,0};

        distance[0][0] = 0;
        while(!pq.isEmpty()){
            Tuple node=pq.poll();
            int x=node.row;
            int y=node.col;
            int diff = node.distance;
            if(x == m-1 && y == n-1){
                return diff;
            }

            for(int i=0;i<dx.length;i++){
                int px = dx[i] + x;
                int py = dy[i] + y;

                if(px < 0 || px >= heights.length || py < 0 || py >= heights[px].length ){
                    continue;
                }
                int maxEff =Math.max( Math.abs(heights[px][py] - heights[x][y]) , diff);

                if(maxEff < distance[px][py]){
                    distance[px][py] = maxEff;
                    pq.add(new Tuple(px,py,maxEff));
                }
                // visited[px][py] = true;

            }
        }
        return 0;
    }

}
