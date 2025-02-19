package career.sixmonthssep.graph;


import java.util.*;

class Point{
    int node;
    int distance;
    public Point(int node,int distance){
        this.node = node;
        this.distance = distance;
    }
}
public class NetworkDelayTime {
    public static void main(String[] args) {
        int times[][] = {{2,1,1},{2,3,1},{3,4,1}}, n = 4, k = 2;
//        int  times[][] = {{1,2,1}}, n = 2, k = 1;
        System.out.println(networkDelayTime(times,4,2));
    }

    static Map<Integer, List<Point>> graph;
    public static int networkDelayTime(int[][] times, int n, int k) {
        createGraph(times);
        int distance[]=new int[n+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(k);
        distance[k] = 0;
        while(!pq.isEmpty()){
            int node = pq.poll();
            n--;
            for(Point nbNode:graph.getOrDefault(node,new ArrayList<>())){
                if(distance[nbNode.node] > distance[node]+1){
                    System.out.println(nbNode.node);
                    distance[nbNode.node] = distance[node]+1;
                    pq.add(nbNode.node);
                }
            }
        }

//        System.out.println(Arrays.toString(distance));
        int max = Integer.MIN_VALUE;
        for(int i:distance){
            if(i == Integer.MAX_VALUE) continue;
            max = Math.max(max,i);
        }
        return n > 0 ? -1:max;
    }

    private static void createGraph(int[][]times){
        graph = new HashMap<>();
        for(int time[]:times){
            if(!graph.containsKey(time[0])){
                graph.put(time[0],new ArrayList<>());
            }
            graph.get(time[0]).add(new Point(time[1],time[2]));
        }
    }
}
