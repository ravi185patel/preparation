package career.datastructure.graph.template;

import java.util.ArrayList;

public class DFS {
    public static void main(String[] args) {
        int adj[][] = {{2,3,1}, {0}, {0,4}, {0}, {2}};
        ArrayList<Integer> res = dfsOfGraph(adj);
        System.out.println(res);
    }

    public static ArrayList<Integer> dfsOfGraph(int adj[][]) {
        ArrayList<Integer> lst = new ArrayList<>();
        boolean visited[]=new boolean[adj.length+1];
        dfs(0,adj,lst,visited);
        return lst;
    }

    private static void dfs(int node,int [][]adj,
                     ArrayList<Integer> lst,boolean [] visited){
        if(visited[node]) return; // already visited return true,count,nothing
        visited[node] = true; // mark node visited so again same node won't visited.
        lst.add(node); // add in list based on problem
        for(int nbNode:adj[node]){
            if(visited[nbNode] == false){
                dfs(nbNode,adj,lst,visited);  //visited nb point of node.

                /*
                    if(dfs(nbNode,adj,lst,visited)){
                    // terminate execution for other child of parent after find path or condition
                    // satisfied
                        return true;
                    }
                */
            }
        }
    }

}
