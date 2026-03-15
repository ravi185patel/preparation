package career.sixmonthssep.graph.practice;


import java.util.*;

class Pair{
    int node;
    int weight;

    public Pair(int node,int weight){
        this.node = node;
        this.weight = weight;
    }
}
public class ShortestPathDirectedGraph {
    public static void main(String[] args) {

          System.out.println(Arrays.toString(
                  shortestPathInDAG(new int[][]{
                          {0,4,2},{0,5,3},{5,4,1},{4,6,3},{4,2,1},{6,1,2},{2,3,3},{1,3,1}
                  },7,8)
          ));
        System.out.println(Arrays.toString(
                shortestPathInDAG(new int[][]{
                        {0, 1, 2},{1, 2, 3},{0, 2, 6}
                },3,3)
        ));

        System.out.println(Arrays.toString(
                shortestPathInDAG(new int[][]{
                        {2, 0, 4},{0, 1, 3},{2, 1, 2}
                },3,3)
        ));
    }
    public static int[] shortestPathInDAG(int [][]edges,int n,int m) {
        // Write your code here
        Map<Integer,List<Pair>> graphMap = createGraph(edges,n,m);
//        return bfs(graphMap,n,m);
        return topoSort(graphMap,n,m);
    }

    public static int[] topoSort(Map<Integer,List<Pair>> graphMap,int n,int m){

        int inDegree[]=new int[n+1];
        int distance[]=new int[n+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        for(int key:graphMap.keySet()){
            for(Pair nbPair:graphMap.get(key)){
                inDegree[nbPair.node]++;
            }
        }

        Queue<Pair>  queue = new LinkedList<>();
        for(int key:graphMap.keySet()) {
            for (Pair nbPair : graphMap.get(key)) {
                if (inDegree[nbPair.node] == 0) {
                    queue.add(nbPair);
                    distance[nbPair.node] = 0;
                }
            }
        }

        while(!queue.isEmpty()){
            Pair nbNode= queue.poll();
            for(Pair nbPair:graphMap.get(nbNode.node)){
                inDegree[nbPair.node]--;
                int pairDistance = nbPair.weight+nbNode.weight;
                if(pairDistance < distance[nbPair.node]){
                    distance[nbPair.node]=pairDistance;
                }
                if(inDegree[nbPair.node]==0){
                    queue.add(nbPair);
                }
            }
        }
        return distance;
    }

    public static int[] bfs(Map<Integer,List<Pair>> graphMap,int n,int m){
        int distance[]=new int[n];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0]=0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0,0));
        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            for(Pair nbPair:graphMap.getOrDefault(pair.node,new ArrayList<>())){
                int pairDistance = pair.weight + nbPair.weight;
                if(pairDistance < distance[nbPair.node]){
                    distance[nbPair.node]=pairDistance;
                    queue.add(new Pair(nbPair.node,pairDistance));
                }
            }
        }

        for(int i=0;i<n;i++){
            distance[i]= distance[i] == Integer.MAX_VALUE ? -1: distance[i];
        }
        return distance;
    }


    public static Map<Integer,List<Pair>> createGraph(int [][]edges, int n, int m){
        Map<Integer,List<Pair>> hmap = new HashMap<>();

        for(int edge[]:edges){
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];
            hmap.putIfAbsent(start,new ArrayList<>());
            hmap.get(start).add(new Pair(end,weight));
        }
        return hmap;
    }
}
