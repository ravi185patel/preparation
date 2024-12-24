package career.datastructure.graph.template;

import java.util.*;

/*
 In a directed graph, a Strongly Connected Component is a subset of vertices
 where every vertex in the subset is reachable from every other vertex
 in the same subset by traversing the directed edges.

 Finding the SCCs of a graph can provide important insights into the structure
 and connectivity of the graph, with applications in various fields such as social network analysis,
 web crawling, and network routing.

 1] Brute Force Approach for Finding Strongly Connected Components
  The simple method will be for each vertex i (which is not a part of any strongly component)
  find the vertices which will be the part of strongly connected component containing vertex i.
  Two vertex i and j will be in the same strongly connected component
  if they there is a directed path from vertex i to vertex j and vice-versa.

  Time complexity: O(n * (n + m)), because for each pair of vertices we are checking whether a path exists between them.
  Auxiliary Space: O(N)

 1.1] Floyd Warshall algorithm
 We can use the Floyd Warshall algorithm to compute the distance between every two vertices,
 then check if the distance between any two pairs is infinity. This means both are unreachable,
 except for the fact that it might be a self-loop. But, it has a time complexity of about O(V3),
 which we cannot afford in most of the scenarios.
 TC : O(V3)
 AC : O(N*M)

 2] Efficient Approach for Finding Strongly Connected Components (SCCs)
    To find SCCs in a graph, we can use algorithms.
    1. Kosaraju’s Algorithm
        i]   DFS on Original Graph: Record finish times.
        ii]  Transpose the Graph: Reverse all edges.
        iii] DFS on Transposed Graph: Process nodes in order of decreasing finish times to find SCCs.

    2. Tarjan’s Algorithm
        1] Initialize index to 0.
        2] For each unvisited node, perform DFS.
            i]   Set the node’s index and low-link value.
            ii]  Push the node onto the stack.
            iii] For each adjacent node, either perform DFS if it’s not visited or update the low-link value if it’s in the stack.
            iv]  If the node’s low-link value equals its index, pop nodes from the stack to form an SCC.
 */
public class KosarajuAlgo {
    static Map<Integer, List<Integer>> graph;

    static boolean visited[];

    public static void main(String[] args) {
        int adj[][] = {{2, 3}, {0}, {1}, {4}, {}};
        visited = new boolean[adj.length];
        createGraph(adj,true);
        Stack<Integer> stack = new Stack<>();
        for(int key:graph.keySet()){
            if(visited[key]==false){
                dfs(key,stack,true);
            }
        }

        createGraph(adj,false);
        int comp=0;
        Arrays.fill(visited,false);
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(visited[node]==false){
                dfs(node,stack,false);
                comp++;
            }
        }
        System.out.println(comp);
    }

    private static void dfsTrav(int node){
        visited[node]=true;
        for(int nbNode:graph.get(node)){
            if(visited[nbNode] == false){
                dfsTrav(nbNode);
            }
        }
    }
    private  static void dfs(int node, Stack<Integer> stack,boolean flag){
        visited[node]=true;
        for(int nbNode:graph.get(node)){
            if(visited[nbNode]== false){
                dfs(nbNode,stack,flag);
            }
        }
        if(flag) {
            stack.push(node);
        }
    }

    private static void createGraph(int adj[][],boolean flag){
        graph = new HashMap<>();
        for(int i=0;i<adj.length;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int i=0;i<adj.length;i++){
            for(int j=0;j<adj[i].length;j++){
                if(flag) {
                    graph.get(i).add(adj[i][j]);
                }else{
                    graph.get(adj[i][j]).add(i);
                }

            }
        }
    }
}
