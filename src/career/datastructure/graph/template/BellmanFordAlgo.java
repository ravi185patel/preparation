package career.datastructure.graph.template;

import java.util.Arrays;

public class BellmanFordAlgo {
    public static void main(String[] args) {
        int edges[][] = {{0,1,5}, {1,0,3}, {1,2,-1}, {2,0,1}}, src = 2;
        int res[]=pathBellmanFord(edges,src);
        System.out.println(Arrays.toString(res));
    }

    private static int[] pathBellmanFord(int edges[][],int src){
        int V = edges.length;
        int distance[]=new int[V-1];
        Arrays.fill(distance,(int)(1e9));
        distance[src]=0;

        for(int i=0;i<V-1;i++){
            for(int edge[]:edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if(distance[u] != (int)(1e9) && distance[u]+wt < distance[v]){
                    distance[v] = distance[u]+wt;
                }
            }
        }

        for(int i=0;i<1;i++){
            for(int edge[]:edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if(distance[u] != (int)(1e9) && distance[u]+wt < distance[v]){
                    return new int[]{-1};
                }
            }
        }
        return distance;
    }
}
