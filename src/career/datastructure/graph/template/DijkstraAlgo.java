package career.datastructure.graph.template;

import career.lld.LLDTicTacToe.Model.Pair;

import java.util.*;

public class DijkstraAlgo {

    static Map<Integer, List<int[]>> adjList;
    static int distance[];
    static int parent[];
    static int noOfEdges=0;

    public static void main(String[] args) {
        int graph[][] =
                {{1,2,2}, {1,4,1}, {2,1,2}, {2,3,4}, {2,5,5}, {3,2,4}, {3,4,3}, {3,5,1}, {4,1,1}, {4,3,3}, {5,2,5}, {5,3,1}};

        createAdjList(graph);

        noOfEdges = 5;
        distance = new int[noOfEdges+1];
        parent = new int[noOfEdges+1];
        for(int i = 1;i<=noOfEdges;i++) {
            distance[i] = (int)(1e9);
            parent[i] = i;
        }

        int source = 1,destination=3;

        distance[source] = 0;
        findShortestPath(graph,source,destination);

        System.out.println(Arrays.toString(distance));
        System.out.println(Arrays.toString(parent));
    }

    private static void findShortestPath(int graph[][],int source,int destination){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);

        pq.add(new int[]{source,0});
        distance[source]=0;
        while(!pq.isEmpty()){
            int nodeData[]=pq.poll();
            int node = nodeData[0];
//            if(distance[node] != Integer.MAX_VALUE && node == destination){ // get source and destination
//                System.out.println(distance[node]);
//            }

            for(int nbNodeData[]:adjList.get(node)){
                int nbNode = nbNodeData[0];
                int nbNodeWt = nbNodeData[1];
                if(distance[node] + nbNodeWt < distance[nbNode]){
                    distance[nbNode] = distance[node] + nbNodeWt;
                    parent[nbNode]=node;
                    pq.add(new int[]{nbNode,distance[nbNode]});
                }
            }
        }

        // if ask for specific source to destination
//        return distance[destination];
//        return distance;
    }

    private static void createAdjList(int graph[][]){
        adjList = new HashMap<>();
        for(int i=0;i<graph.length;i++){
            adjList.put(i,new ArrayList<>());
        }

        for(int i=0;i<graph.length;i++){
           adjList.get(graph[i][0]).add(new int[]{graph[i][1],graph[i][2]});
        }
    }

}
