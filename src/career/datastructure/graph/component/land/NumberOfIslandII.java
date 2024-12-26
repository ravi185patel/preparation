package career.datastructure.graph.component.land;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UnionFind {
    int[] parent;
    int[] rank;
    int count;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++)
            parent[i] = -1;
        count = 0;
    }

    public void addLand(int x) {
        if (parent[x] >= 0)
            return;
        parent[x] = x;
        count++;
    }

    public boolean isLand(int x) {
        if (parent[x] >= 0) {
            return true;
        } else {
            return false;
        }
    }

    int numberOfIslands() {
        return count;
    }

    public int find(int x) {
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        int xset = find(x), yset = find(y);
        if (xset == yset) {
            return;
        } else if (rank[xset] < rank[yset]) {
            parent[xset] = yset;
        } else if (rank[xset] > rank[yset]) {
            parent[yset] = xset;
        } else {
            parent[yset] = xset;
            rank[xset]++;
        }
        count--;
    }
}

/*
Number of Islands II

Approach
1] Union find => tc = O(M*N+L) sc = O(M*N)
2] DFS =>tc = O(M*N+L) sc = O(M*N)
3] BFS => tc = O(M*N+L) sc = O(M*N)

dfs and bfs not implemented
DFS
1] update matrix and call dfs -> noOfIsland -> will work but gives us TLE
2] improve above method but not found any way to improve

Very very important
1] Edge case
 i) if same point updated multiple time -> output will be same
 ii) if point update is not sorted -> preserve order
    ex:- [[0,0],[1,1],[0,1]] -> output [1,2,1]


 */
public class NumberOfIslandII {

    static int grid[][];
    static boolean visited[][];
    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};
    public static void main(String[] args) {
//        int m = 3, n = 3, positions[][] = {{0,0}, {0,1}, {1,2}, {2,1}};
//        int m = 1, n = 1, positions[][] = {{0,0}};
//        int m = 2, n = 2, positions[][] ={{0,0},{1,1},{0,1}};
          int m = 3, n = 3, positions[][] = {{0,0},{0,1},{1,2},{1,2}};

//        Arrays.sort(positions,(p1,p2) ->{
//            int diff = p1[0]-p2[0];
//            if(diff == 0){
//                return p1[1]-p2[1];
//            }
//            return diff;
//        });
//        List<Integer> res = numIslands2(m,n,positions);
//        System.out.println(res);
        List<Integer> res = new ArrayList<>();
        initData(m,n);
        int noOfIsland = 0;
        for(int position[]:positions){
            int xNode = position[0];
            int yNode = position[1];
            updateData(xNode,yNode);
//            dfsOnGrid(res); // time limit problem
            int coverLand=0;
            boolean flag = false;
            for(int dir[]:directions) {
                int newXNode = xNode +dir[0];
                int newYNode = yNode + dir[1];
                if (newXNode < m && newYNode < n && newXNode >= 0 && newYNode >= 0
                    && visited[newXNode][newYNode] && grid[newXNode][newYNode] == 1) {
                    flag = true;
                    coverLand++;
                }
            }
            if(flag) {
                dfs(m, n, xNode, yNode);
                noOfIsland = noOfIsland - coverLand + 1;
            }else{
                noOfIsland = noOfIsland + 1;
            }
            res.add(noOfIsland);
        }
        System.out.println(res);
    }

    private  static void dfsOnGrid(List<Integer> res){
        int noOfIsland=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(visited[i][j]==false && grid[i][j]==1){
                    dfs(grid.length,grid[0].length,i,j);
                    noOfIsland++;
                }
            }
        }
        res.add(noOfIsland);
        for(boolean v[]:visited){
            Arrays.fill(v,false);
        }
    }

    public static void initData(int m,int n){
        grid = new int[m][n];
        visited = new boolean[m][n];

        for(int i=0;i<m;i++){
            Arrays.fill(grid[i],0);
        }
    }

    public static void updateData(int x,int y){
        grid[x][y] = 1;
        visited[x][y]=true;
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int x[] = { -1, 1, 0, 0 };
        int y[] = { 0, 0, -1, 1 };
        UnionFind dsu = new UnionFind(m * n);
        List<Integer> answer = new ArrayList<>();

        for (int[] position : positions) {
            int landPosition = position[0] * n + position[1];
            dsu.addLand(landPosition);

            for (int i = 0; i < 4; i++) {
                int neighborX = position[0] + x[i];
                int neighborY = position[1] + y[i];
                int neighborPosition = neighborX * n + neighborY;
                // If neighborX and neighborY correspond to a point in the grid and there is a
                // land at that point, then merge it with the current land.
                if (neighborX >= 0 && neighborX < m && neighborY >= 0 && neighborY < n &&
                        dsu.isLand(neighborPosition)) {
                    dsu.union(landPosition, neighborPosition);
                }
            }
            answer.add(dsu.numberOfIslands());
        }
        return answer;
    }

    private static int findDistance(int p1[],int p2[]){
        return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
    }


    private static void dfs(int m,int n,int xNode,int yNode){
        if(xNode >= m || yNode >= n || xNode < 0 || yNode < 0 || visited[xNode][yNode]
        || grid[xNode][yNode]==0){
            return;
        }
        visited[xNode][yNode]=true;
        dfs(m,n,xNode+1,yNode);
        dfs(m,n,xNode,yNode+1);
        dfs(m,n,xNode-1,yNode);
        dfs(m,n,xNode,yNode-1);
    }
}
