package career.datastructure.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
class PathCostPair {
    int discount =0;
    int cost=0;
    int node=0;
    public PathCostPair(int node, int cost,int discount) {
        this.node = node;
        this.cost = cost;
        this.discount=discount;
    }
}
public class MinCostToReachCityWithDiscount {
    public static void main(String[] args) {
        int n = 5, highways[][] = {{0,1,4},{2,1,3},{1,4,11},{3,2,3},{3,4,2}}, discounts = 1;

        List<List<PathCostPair>> graph = createGraph(n,highways);
        int minCostPath = findMinCostPath(n,discounts,graph);
        System.out.println(minCostPath);
    }

    private static int findMinCostPath(int n,int discount,List<List<PathCostPair>> adjList){
        PriorityQueue<PathCostPair> pq = new PriorityQueue<>((p1,p2) -> p1.cost-p2.cost);
        pq.add(new PathCostPair(0,0,0));

        int[][] dist = new int[n][discount + 1];
        for (int[] distances : dist) {
            Arrays.fill(distances, Integer.MAX_VALUE);
        }

        while(!pq.isEmpty()){
            PathCostPair pair = pq.poll();
            if (pair.discount > discount || dist[pair.node][pair.discount] <= pair.cost) {
                continue;
            }

            if (pair.node == n - 1) {
                return pair.cost;
            }

            dist[pair.node][pair.discount] = pair.cost;

            for(PathCostPair nb:adjList.get(pair.node)){
                pq.add(new PathCostPair(nb.node,pair.cost + nb.cost,pair.discount));
                if(nb.discount < discount ){
                    pq.add(new PathCostPair(nb.node,pair.cost + nb.cost/2,pair.discount+1));
                }
            }
        }
        return -1;
    }
    private static List<List<PathCostPair>> createGraph(int n,int highways[][]){
        List<List<PathCostPair>> adjList = new ArrayList<>();
        for(int i=0;i<n;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<highways.length;i++){
            adjList.get(highways[i][0]).add(new PathCostPair(highways[i][1],highways[i][2],0));
            adjList.get(highways[i][1]).add(new PathCostPair(highways[i][0],highways[i][2],0));
        }

        return adjList;
    }

}
