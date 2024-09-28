package career.datastructure.graph.medium;

/*
 MST prim's alogrithm

 Graph -> acyclic graph + weight + undirected + min (cost to connect) = disjkatra algorithm

 Dijkstra gives you a way from the source node to the destination node such
 that the cost is minimum.

 Prim's algorithm gives you a minimum spanning tree such that all nodes are connected
 and the total cost is minimum

 Here problem has mini cost to connect all nodes. so use prim algorithm.

 In the computation aspect, Prim’s and Dijkstra’s algorithms have three main differences:
    1] Dijkstra’s algorithm finds the shortest path, but Prim’s algorithm finds the MST
    2] Dijkstra’s algorithm can work on both directed and undirected graphs,
    but Prim’s algorithm only works on undirected graphs
(VIMP) 3] Prim’s algorithm can handle negative edge weights,
    but Dijkstra’s algorithm may fail to accurately compute distances
    if at least one negative edge weight exists
 */
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Pair{
    int node;
    int distance;

    public Pair(int node,int distance){
        this.node = node;
        this.distance = distance;
    }

    public String toString(){
        return "{node: "+node+", distance: "+distance+"},";
    }
}
public class MinimumCostToConnectAllCities {
    public static void main(String[] args) {
//        int n=5,m=6;
//        int connections[][]=
//                    {
//                    {1,2,6},
//                    {2,3,5},
//                    {3,4,4},
//                    {1,4,1},
//                    {1,3,2},
//                    {3,5,3}};

        int n=3,m=1;
        int connections[][]={{1,2,4}};

        int ans=getMinimumCost(n,m,connections);
        System.out.println(ans);
    }

    public static int getMinimumCost(int n, int m, int[][] connections) {
        // Write your code here.

        List<List<Pair>> adjList = createAdjList(n,m,connections);

        int minPath = shortestPath(n,adjList);
        return minPath;
    }

    private static List<List<Pair>> createAdjList(int n, int m, int[][] connections){
        List<List<Pair>> adjList = new ArrayList<>();
        for(int i=1;i<=n+1;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            // System.out.println(adjList);
            adjList.get(connections[i][1]).add(new Pair(connections[i][0],connections[i][2]));
            adjList.get(connections[i][0]).add(new Pair(connections[i][1],connections[i][2]));
        }

        return adjList;
    }

    private static int shortestPath(int n,List<List<Pair>> adjList){
        PriorityQueue<Pair> queue = new PriorityQueue<>((pair1, pair2)
                -> pair1.distance-pair2.distance);

        queue.add(new Pair(1,0));
        boolean visited[]=new boolean[n+1];

        int minPath =0;

        int count=0;
        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            int distance = pair.distance;
            int node = pair.node;

            if(visited[node]){
                continue;
            }

            visited[node] = true;
            minPath+=distance;
            count++;

            for(Pair nb:adjList.get(node)){
                int nbNode = nb.node;
                if(visited[nbNode] == false){
                    queue.add(nb);
                }
            }
        }
        return count == n ? minPath : -1;
    }

}