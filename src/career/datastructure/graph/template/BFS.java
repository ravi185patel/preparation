package career.datastructure.graph.template;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
//        int adj [][] = {{2,3,1}, {0}, {0,4}, {0}, {2}};
//        int adj [][] = {{2,1}, {2,0}, {1}};
        int adj[][]={{3}, {}, {7, 3}, {0, 2, 4}, {7, 6, 6, 3, 7}, {6, 7, 8, 6}, {4, 4, 5, 5}, {4, 2, 5, 4}, {5}};
        ArrayList<Integer> res = bfsOfGraph(adj.length,adj);
        System.out.println(res);
    }

    public static ArrayList<Integer> bfsOfGraph(int V, int[][] adj) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean visited[]=new boolean[V+1];
        visited[0]=true; // if added 0 as source added at start of list
        res.add(0); // if added 0 as source added at start of list
        // res = [0,2,1]
        // not added res = [2,1,0];
        while(!queue.isEmpty()){
            int node = queue.poll();
//            res.add(node);
//            if(visited[node])continue; // got same result [0,2,1]  but failed other cases
            // problem with this code as we marked down 3 as visited in for loop when found in
            //nb node we select same node for traverse nb node but found it is visited and we skip
            // nb visited part.
            for(int nbNode:adj[node]){
                if(visited[nbNode]== false){
                    queue.add(nbNode);
                    visited[nbNode] = true;
                    res.add(nbNode);
                }
            }
        }

        return res;
    }
}
