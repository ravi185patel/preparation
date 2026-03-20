package career.datastructure.graph.template;

import java.util.*;

public class MinimumSpanningTreePrimAlgo {
    static Map<Integer, List<int[]>> graph;
    public static void main(String[] args) {
        int mat[][]= {
                {0, 1, 5},
                {1, 2, 3},
                {0, 2, 1}
        };

        createGraph(mat);
        System.out.println(findMinSpanningTree());
    }

    private static int findMinSpanningTree(){
        boolean visited[]=new boolean[graph.size()];
        int distance[]=new int[graph.size()];
        Arrays.fill(distance,(int)(1e9));
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2)-> p1[1]-p2[1]);

        pq.add(new int[]{0,0});
        distance[0]=0;
        int minDis = 0;
//        visited[0]=true;  why not work
        while(!pq.isEmpty()){

            int node[]=pq.poll();

            if(visited[node[0]]) continue; 
            // why it is not worked inside loop
            // reason :- you need to process min edges of each node not path
            // so 0 -> 1(2), 3 -> 1(8),0-> 3(6) now 1 has 2 edges from 1 and 3
            // which is small 0 -> 1 if you put inside loop it won't process 3->1 as it is already visited
            // and even if you use distance array it will be exclude as it has lower distance
            /*
                Because the priority queue can contain duplicates:
                Same node may be pushed multiple times with different weights
                Only the smallest one should be used
                Others must be ignored → that’s what visited[] does

                🧠 Key insight
                Inner loop check → avoids pushing unnecessary edges
                Outer check (visited[node]) → avoids processing duplicates from PQ
                👉 Both are needed for correctness + efficiency

                
                The outer visited[node] check is about “Should I process this node at all?”
                The inner check is about “Should I consider this neighbor?”

                | Check                     | Purpose                   | Location         |
                | ------------------------- | ------------------------- | ---------------- |
                | `if (visited[node])`      | Skip duplicate PQ entries | **Outside loop** |
                | `if (!visited[neighbor])` | Avoid unnecessary pushes  | **Inside loop**  |
             */

            minDis += node[1];
            visited[node[0]]=true;

            for(int nbNode[]:graph.get(node[0])){

                if(visited[nbNode[0]]==false) {
                    pq.add(nbNode);
                }
            }
        }
        return minDis;
    }

    private static void createGraph(int mat[][]){
        graph = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;

        for(int i=0;i<m;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int edge[]:mat){
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }
        System.out.println(graph);
    }
}
