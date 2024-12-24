package career.datastructure.graph.path;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
1] dfs and bruteforce -> O(3^mn) => we have 4 choice but at a time we take one path and mark it so 3
2] dijkstra algp -> O(m.n.log(m.n)) - O(m.n) --> implemented
3] union disjoint -> O(m.n.log(m.n)) - O(m.n)
4] binary search with bfs -> O(m.n) - O(m.n)
5] binary search with dfs -> O(m.n) - O(m.n)
 */
public class PathWithMinEffort {
    public static void main(String[] args) {
        int heights[][] = {{1,2,2}, {3,8,2}, {5,3,5}};
        System.out.println(findMinEffortPath(heights));
        System.out.println(binarySearch(heights));
    }
    static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};
    private static int findMinEffortPath(int heights[][]){
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2) -> p1[1]-p2[1]);
        int n = heights.length;
        int distance[][]=new int[n][n];
        distance[0][0]=0;
        pq.add(new int[]{0,0,0});
        int minAbsDiff = 0;
        while (!pq.isEmpty()){
            int node[]=pq.poll();
            int x = node[0];
            int y = node[1];
            if(x == n-1 && y ==n-1){
                return node[2];
            }
            for(int dir[]:directions){
                int xPoint= x+dir[0];
                int yPoint= y+dir[1];
                if(xPoint >= n || yPoint >= n || xPoint < 0 || yPoint < 0){
                    continue;
                }

                int difference =  Math.max(node[2],Math.abs(heights[x][y]-heights[xPoint][yPoint]));
                // why max because we need max at destination cell so we can find out minimum abs difference.
                if(distance[xPoint][yPoint] < difference ){
                    distance[xPoint][yPoint] = difference;
                    pq.add(new int[]{xPoint,yPoint,difference});
                }

            }
        }
        return 0;
    }

    private static int binarySearch(int heights[][]){
        int left=0,right = 1000000;
        int res = right;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(
//                    bfs(heights,mid)
                    dfsUtil(heights,mid)
            ){
                res = Math.min(mid,res);
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return res;
    }
    private static boolean bfs(int heights[][],int k){

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0,0});

        int n = heights.length;
        boolean visited[][]=new boolean[n][n];
        visited[0][0]=true;
        queue.add(new int[]{0,0,0});

        while (!queue.isEmpty()){
            int node[]=queue.remove();
            int x = node[0];
            int y = node[1];
            if(x == n-1 && y ==n-1){
                return true;
            }
            for(int dir[]:directions){
                int xPoint= x+dir[0];
                int yPoint= y+dir[1];
                if(xPoint >= n || yPoint >= n || xPoint < 0 || yPoint < 0 || visited[xPoint][yPoint]){
                    continue;
                }

                int difference =  Math.max(node[2],Math.abs(heights[x][y]-heights[xPoint][yPoint]));
                // why max because we need max at destination cell so we can find out minimum abs difference.
                if(difference <= k){
                    visited[xPoint][yPoint] = true;
                    queue.addLast(new int[]{xPoint,yPoint,difference});
                }
            }
        }
        return false;
    }

    private static boolean dfsUtil(int heights[][],int k){
        int n = heights.length;
        boolean visited[][]=new boolean[n][n];
//        visited[0][0]=true;
        return dfs(0,0,heights,visited,k,heights.length,0);
    }

    private static boolean dfs(int x,int y,int heights[][],boolean visited[][],int k,int n,int dis){
        if(x >= heights.length || y >= heights.length || visited[x][y]) return false;

        visited[x][y]=true;
        if(x == n-1 && y ==n-1){
            return true;
        }
        boolean flag = true;
        for(int dir[]:directions){
            int xPoint= x+dir[0];
            int yPoint= y+dir[1];
            if(xPoint >= n || yPoint >= n || xPoint < 0 || yPoint < 0 || visited[xPoint][yPoint]){
                continue;
            }

            int difference =  Math.max(dis,Math.abs(heights[x][y]-heights[xPoint][yPoint]));
            if(difference <= k){
                if(dfs(xPoint,yPoint,heights,visited,k,n,difference)){
                    // same logic as we used in cycle detect
                    // process other point only if valid point not found
                    // if we do flag &= dfs(...)
                    // it will return wrong ans as we explore other path and one of path will return false for this node.

                    return true;
                }
            }
        }
        return false;
    }

}
