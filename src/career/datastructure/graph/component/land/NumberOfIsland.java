package career.datastructure.graph.component.land;

import career.datastructure.graph.template.DisJoinUnion;

import java.util.LinkedList;
import java.util.Queue;

/*
Number of Islands

clarification question
1] it is matrix so what will be size n*m or n*n ?
2] what will be shape of area square or rectangle
3] how many directions like 4 direction or 8 directions

Approach
1] dfs => tc = O(M*N) sc=O(M+N)
2] bfs => tc = O(M*N) sc=O(M+N)
3] union find => tc = O(M*N) sc=O(M*N)

Implement which is easy for you not for interviewer.
If interviewer ask to implement then you have to know all of above implementation

 */
public class NumberOfIsland {
    static int islandCount=0;
    public static void main(String[] args) {
       String[][] grid = 
//               {{"1","1","1","1","0"},
//                          {"1","1","0","1","0"},
//                          {"1","1","0","0","0"},
//                          {"0","0","0","0","0"}};
               {{"1","1","0","0","0"}, {"1","1","0","0","0"}, {"0","0","1","0","0"}, {"0","0","0","1","1"}};
       
       System.out.println(findNoOfIslandDSU(grid));
       System.out.println(numIslandsBFS(grid));
    }

    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};
    public static int findNoOfIslandDSU(String grid[][]){
        int m = grid.length;
        int n = grid[0].length;
        DisJoinUnion ds = new DisJoinUnion(m*n);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j].equals("1")) {
                    islandCount++;
                    for (int dir[] : directions) {

                        int newR = i + dir[0];
                        int newC = j + dir[1];

                        if (newR >= 0 && newC >= 0 && newR < m && newC < n
                                && grid[newR][newC].equals("1")) {
                            int nodeNo = i * m + j;
                            int adjNodeNo = newR * m + newC;
                            if (ds.find(nodeNo) != ds.find(adjNodeNo)) {
                                ds.unionBySize(nodeNo, adjNodeNo);
                            }
                        }
                    }
                }
            }
        }
        return islandCount;
    }

    public static int numIslandsBFS(String[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        boolean visited[][] = new boolean[m][n];
        int island = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j] == false && grid[i][j] == "1"){
                    bfs(i,j,grid,visited);
                    island++;
                }
            }
        }
        return island;
    }
    private static void bfs(int x,int y,String[][]grid,boolean visited[][]){
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

                    if(newX >= m || newX < 0 || newY >= n || newY < 0 || visited[newX][newY] || grid[newX][newY] == "0"){
                        continue;
                    }

                    visited[newX][newY] = true;
                    queue.add(new int[]{newX,newY});
                }
            }
        }
    }
}
