package career.datastructure.graph.path;



import java.util.ArrayList;
import java.util.*;
import java.util.PriorityQueue;
class Pair{
    int first;
    int second;
    public Pair(int first,int second){
        this.first = first;
        this.second = second;
    }
}

public class ShortestPathWgUg {
    public static void main(String[] args) {
        int graph[][] =
                {{1,2,2}, {1,4,1}, {2,1,2}, {2,3,4}, {2,5,5}, {3,2,4}, {3,4,3}, {3,5,1}, {4,1,1}, {4,3,3}, {5,2,5}, {5,3,1}};
        List<Integer> ls =shortestPath(5,12,graph);
        System.out.println(ls);
    }
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<ArrayList<Pair>>();
        for(int i = 0;i<=n;i++) {
            adj.add(new ArrayList<>());
        }
        for(int i = 0;i<m;i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }

        PriorityQueue<Pair> pq =
                new PriorityQueue<Pair>((x,y) -> x.first - y.first);

        int[] dist = new int[n+1];
        int[] parent =new int[n+1];
        for(int i = 1;i<=n;i++) {
            dist[i] = (int)(1e9);
            parent[i] = i;
        }

        dist[1] = 0;

        pq.add(new Pair(0, 1));
        while(pq.size() != 0) {

            Pair it = pq.peek();
            int node = it.second;
            int dis = it.first;
            pq.remove();

            for(Pair iter : adj.get(node)) {
                int adjNode = iter.first;
                int edW = iter.second;

                if(dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    pq.add(new Pair(dis + edW, adjNode));
                    parent[adjNode] = node;
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        if(dist[n] == 1e9) {
            path.add(-1);
            return path;
        }

        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(dist));
        int node = n;

        /*
         very important part where asked for find path.
         it has been used in many problems to find path or say print path.
         */
        while(parent[node] != node) {
            path.add(node);
            node = parent[node];
        }
        path.add(1);

        Collections.reverse(path);
        return path;
    }
}
