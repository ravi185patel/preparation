package career.datastructure.graph.component.land;

import career.datastructure.graph.template.DisJoinUnion;

import java.util.ArrayList;
import java.util.List;

/*
It is very clear from problem statement it is graph
approach
1] dfs -> set 0 to 1 and find maxArea of 1s
2] bfs -> same as dfs
TC = O(M^4) -> each time you change 0 to 1 and visit all matrix point(n2)-> O(m*n(m*n))
3] union join -> connect all points into single point.
again run same way but for 0 not for 1 and pick 0 and check any side out of 4 has 1 or not if yes add that as parent.
TC = O(M*M) -> traverse matrix only one time.

 */
public class MakingLargeIsland {
    public static void main(String[] args) {
        int grid[][] = {{1,0}, {0,1}};
        int res = largestIsland(grid);
        System.out.println(res);
    }

    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};
    private static int largestIsland(int[][] grid) {
        int largestIsland=0;
        int m = grid.length;
        int n = grid[0].length;
        DisJoinUnion ds = new DisJoinUnion(m*n+1);

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 0) continue;;
                for(int dir[]:directions) {
                    int newR = i + dir[0];
                    int newC = j + dir[1];
                    if(isValid(newR,newC,m)
                        && grid[newR][newC] == 1) {

                        int nodeNo = i*n + j;
                        int adjNodeNo = newR*n + newC;
                        if (ds.find(nodeNo) != ds.find(adjNodeNo)) {
                            ds.unionBySize(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
        }


        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1) continue;;
                List<Integer> maxArea = new ArrayList<>();
                for(int dir[]:directions) {
                    int newR = i + dir[0];
                    int newC = j + dir[1];
                    if(isValid(newR,newC,m)
                            && grid[newR][newC] == 1) {
                        int adjNodeNo = newR*n + newC;
                        maxArea.add(ds.find(adjNodeNo));
                    }
                }
                int totalSize =0;
                for(int parent:maxArea){
                    totalSize += ds.size[parent];
                }
                largestIsland = Math.max(largestIsland,totalSize+1);
            }
        }

        for(int i=0;i<m*n;i++){
            largestIsland = Math.max(largestIsland,ds.size[ds.find(i)]);
        }
        return largestIsland;
    }

    private static boolean isValid(int newR,int newC,int n){
        return newR >= 0 && newC >=0 && newR < n && newC < n;
    }


}
