package career.datastructure.graph.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindCityWithSmallestNoOfNbAtDistance {
    public static void main(String[] args) {

    }
    private int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<Pair>> adj = getAdjList(n,edges);
        int m = edges[0].length;
        int distance[][]=new int[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(distance[i],(int)1e9);
            distance[i][i] = 0;
        }

        for(int i=0;i<n;i++){
            dfs(i,adj,distance[i],distanceThreshold);
        }

        return findMinCity(n,distance,distanceThreshold);
    }

    private void dfs(int parent,List<List<Pair>> adj,int[]dist,int threshold){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1]-b[1]));
        pq.add(new int[]{parent,0});
        while(!pq.isEmpty()){
            int[] cur = pq.remove();
            int u = cur[0];
            int du = cur[1];
            if(du > dist[u]){continue;}

            for(Pair nb:adj.get(u)){
                int v  = nb.node;
                int duv = nb.wg;
                if(dist[v] > du + duv){
                    dist[v] = du + duv;
                    pq.add(new int[]{v,dist[v]});
                }
            }
        }
    }

    private int findMinCity(int n,int distance[][],int disThreshold){
        int minCity = -1,minCount = n;
        for(int i=0;i<n;i++){
            int curCount =0;

            for(int j=0;j<n;j++){
                if(i == j) continue;
                if(distance[i][j] == disThreshold){
                    curCount++;
                }
            }
            if(curCount <= minCount){
                minCount = curCount;
                minCity = i;
            }
        }
        return minCity;
    }

    private List<List<Pair>> getAdjList(int n,int edges[][]){
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<n;i++){
            adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0],edges[i][2]));
        }

        return adj;
    }
}
