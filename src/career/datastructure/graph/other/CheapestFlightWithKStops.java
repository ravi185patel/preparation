package career.datastructure.graph.other;

import java.util.*;

public class CheapestFlightWithKStops {
    public static void main(String[] args) {

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair>> adjList = getAdjList(flights,n);
        int m = flights[0].length;
        int distance []=new int[n];
        Arrays.fill(distance,(int)1e9);;

        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(0,src,0));

        distance[src] = 0;
        while(!queue.isEmpty()){
            Tuple tuple = queue.remove();
            int stops = tuple.row;
            int node = tuple.col;
            int cost = tuple.distance;
            if(tuple.col > k) continue;

            for(Pair pair:adjList.get(node) ){
                int adjNode = pair.node;
                int edw = pair.dist;
                if(cost + edw < distance[adjNode] && stops <= k ){
                    distance[adjNode] = cost+ edw;
                    queue.add(new Tuple(stops+1,adjNode,cost + edw));
                }
            }
        }

        if(distance[dst] == (int)(1e9)) return -1;
        return distance[dst];
    }

    private List<List<Pair>> getAdjList(int[][]flights,int n){
        List<List<Pair>> adjList = new ArrayList<>();
        for(int i=0;i<n;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<n;i++){
            adjList.get(flights[i][0]).add(new Pair(flights[i][1],flights[i][2]));
        }

        return adjList;
    }
}
