package career.datastructure.graph.other;

import java.util.*;

public class FindShortesPathInUG {
    public static void main(String[] args) {

    }
    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
        List<List<Integer>> adj = getAdjList(edges,n,m);

        int distance[] = findShortestPath(adj,n,m,src);

        return distance;

    }

    private int[] findShortestPath(List<List<Integer>> adj,int n,int m,int src){
        int distance[]=new int[n];
        Arrays.fill(distance,-1);
        int visited[]=new int[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src,0});
        visited[src] = 1;

        while(!queue.isEmpty()){
            int node[] = queue.remove();
            distance[node[0]] = node[1];
            for(int nb:adj.get(node[0])){
                if(visited[nb] == 0){
                    visited[nb] = 1;
                    queue.add(new int[]{nb,node[1]+1});
                }
            }
        }
        return distance;
    }

    private int[] findShortestPath1(List<List<Integer>> adj,int n,int m,int src){
        int distance[]=new int[n];
        Arrays.fill(distance,(int)1e9);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        distance[src] = 0;

        while(!queue.isEmpty()){
            int node = queue.remove();
            for(int nb:adj.get(node)){
                if(distance[node] + 1 < distance[nb]){
                    distance[nb] = distance[node] + 1;
                    queue.add(nb);
                }
            }
        }

        for(int i=0;i<n;i++){
            if(distance[i] == (int)1e9){
                distance[i] = -1;
            }
        }
        return distance;
    }

    private List<List<Integer>> getAdjList(int[][]edges, int n, int m){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        return adj;
    }
}
