package career.thirtydays.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphValidTree {
    public static void main(String[] args) {
//        int n = 5, edges[][] = {{0,1},{1,2},{2,3},{1,3},{1,4}};
        int n = 5, edges[][] = {{0,1},{0,2},{0,3},{1,4}};
        System.out.println(validTree(n,edges));
    }

    public static boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjList = createGraph(n, edges);
        boolean visited[] = new boolean[n];

        if(isCycle(0,adjList,visited,-1)){
            return false;
        }

        for(boolean source:visited){
            if(source == false){
                return false;
            }
        }
        return true;
    }

    private static boolean isCycle(int source,List<List<Integer>> adjList,boolean visited[],int parent){
        visited[source] = true;
        for(int nbNode:adjList.get(source)){
            if(visited[nbNode]==false){
                if(isCycle(nbNode,adjList,visited,source)){
                    return true;
                }
            }else if(parent != nbNode ){
                return false;
            }
        }
        return false;
    }

        private static List<List<Integer>> createGraph(int n,int[][]edges){
        List<List<Integer>> adjList = new ArrayList<>(n);

        // initialize vertices
        for (int i = 0; i < n; i++)
            adjList.add(i, new ArrayList<Integer>());

        // add edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }
}
