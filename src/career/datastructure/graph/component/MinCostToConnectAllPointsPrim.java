package career.datastructure.graph.component;

import java.util.*;

public class MinCostToConnectAllPointsPrim {
    static Map<Integer, List<int[]>> graph;
    public static void main(String[] args) {
        int points[][] = {{0,0}, {2,2}, {3,10}, {5,2}, {7,0}};
        createGraph(points);
        System.out.println(findMinCost());
    }

    private static int findMinCost(){
        boolean visited[]=new boolean[graph.size()];
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2)-> p1[1]-p2[1]);
        pq.add(new int[]{0,0});
        int minDist=0;
        while(!pq.isEmpty()){

            int node[]=pq.poll();

            if(visited[node[0]]) continue;

            minDist += node[1];
            visited[node[0]]=true;

            for(int[] nbNode :graph.get(node[0])){
                if(visited[nbNode[0]] == false){
//                    System.out.println(node[0]+" -- "+Arrays.toString(nbNode));
                    pq.add(nbNode);
                }
            }
        }

        return minDist;
    }

    private static void createGraph(int points[][]){
        graph = new HashMap<>();
        for(int i=0;i<points.length;i++){
            graph.put(i,new ArrayList<>());
        }
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int dis = Math.abs(points[i][0]-points[j][0]) +Math.abs(points[i][1]-points[j][1]);
                graph.get(i).add(new int[]{j,dis});
                graph.get(j).add(new int[]{i,dis});
            }
        }

//        for(int key:graph.keySet()){
//            System.out.println(key+" -- ");
//            for(int i[]:graph.get(key)){
//                System.out.println(Arrays.toString(i));
//            }
//        }
    }


}
