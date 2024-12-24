package career.datastructure.graph.path;

import java.util.*;

public class NetworkDelayTime {
    static Map<Integer, List<Pair>> graph;
    static int distance[];
    static int parent[];
    public static void main(String[] args) {
        int  times[][] = {{2,1,1}, {2,3,1}, {3,4,1}}, n = 4, k = 2;
        distance = new int[n+1];
        parent = new int[n+1];

        Arrays.fill(distance,(int)1e9);

        createGraph(times,n);
        System.out.println(findNetworkDelayTime(k));
    }

    private static int findNetworkDelayTime(int node){
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1,p2) -> p1.second - p2.second);

        pq.add(new Pair(node,0));
        distance[node]=0;
        parent[node]=node;
        int nodeReached = distance.length-1;
        while(!pq.isEmpty()){
            Pair pair = pq.poll();
            node = pair.first;
            nodeReached--;
            for(Pair nbPair:graph.get(node)){

                int nbNode = nbPair.first;
                int nbWt = nbPair.second;

                if(distance[node]+ nbWt < distance[nbNode]){
                    distance[nbNode] =distance[node]+ nbWt;
                    pq.add(new Pair(nbNode,distance[nbNode]));
                }

            }
        }

        if(nodeReached > 0) return -1;
        int maxTime = 0;
        for(int time:distance){
            if(time == (int)(1e9)) continue;
            maxTime = Math.max(maxTime,time);
        }

        return maxTime;
    }

    public static void createGraph(int times[][],int n){
        graph = new HashMap<>();
        for(int i=1;i<=n;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int time[]:times){
            graph.get(time[0]).add(new Pair(time[1],time[2]));
        }
    }


}
