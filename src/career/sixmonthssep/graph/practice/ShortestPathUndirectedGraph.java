package career.sixmonthssep.graph.practice;


import java.util.*;

public class ShortestPathUndirectedGraph {
    public static void main(String[] args) {
//        n = 9, m = 10 edges = [[0,1},{0,3},{3,4},{4 ,5},{5,6},{1,2},{2,6},{6,7},{7,8},{6,8]] src = 0
        System.out.println(shortestPath(new int[][]{{1,2},{2,3},{3,4},{1,3}},4,4,1,4));
        System.out.println(shortestPath(new int[][]{
                {1,2},{1,3},{1,4},{2,5},{5,8},{3,8},{4,6},{6,7},{7,8}
        },8,9,1,8));
    }
    public static List<Integer> shortestPath(int[][] edges, int n, int m, int s, int t) {
        // Write your code here.
        Map<Integer,List<Integer>> graph = createGraph(edges,n,m);
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[]=new boolean[n+1];
        int parent[]=new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }
        queue.add(s);
        visited[s]=true;
        parent[s]=0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int nbNode:graph.get(node)){
                if(visited[nbNode]==false){
                    visited[nbNode]=true;
                    parent[nbNode]=node;
                    queue.add(nbNode);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while(parent[t]!=t){
            res.add(t);
            t=parent[t];
        }
        Collections.reverse(res);
        return res;
    }

    public static Map<Integer,List<Integer>> createGraph(int [][]edges, int n, int m){
        Map<Integer,List<Integer>> hmap = new HashMap<>();

        for(int edge[]:edges){
            int start = edge[0];
            int end = edge[1];
            hmap.putIfAbsent(start,new ArrayList<>());
            hmap.putIfAbsent(end,new ArrayList<>());
            hmap.get(start).add(end);
            hmap.get(end).add(start);
        }
        return hmap;
    }
}
