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

            if(visited[node[0]]) continue; // why it is not work if i put check inside for loop

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
