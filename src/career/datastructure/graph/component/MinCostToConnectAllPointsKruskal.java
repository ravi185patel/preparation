package career.datastructure.graph.component;

import career.datastructure.graph.template.DisJoinUnion;

import java.util.*;

public class MinCostToConnectAllPointsKruskal {
    static List<int[]> edgeList;
    public static void main(String[] args) {
        int points[][] = {{0,0}, {2,2}, {3,10}, {5,2}, {7,0}};
        int V=5;
        createList(points);
        System.out.println(findMinCost(V));
    }

    private static int findMinCost(int V){
        DisJoinUnion ds = new DisJoinUnion(V);
        int minDist=0;
        for(int edge[]:edgeList){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if(ds.find(u) != ds.find(v)){
                ds.unionByRank(u,v);
                minDist +=w;
            }
        }
        return minDist;
    }

    private static void createList(int points[][]){
        edgeList = new ArrayList<>();

        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int dis = Math.abs(points[i][0]-points[j][0]) +Math.abs(points[i][1]-points[j][1]);
                edgeList.add(new int[]{i,j,dis});
            }
        }

        Collections.sort(edgeList,(edge1,edge2) -> edge1[2]-edge2[2]);

//        for(int key:graph.keySet()){
//            System.out.println(key+" -- ");
//            for(int i[]:graph.get(key)){
//                System.out.println(Arrays.toString(i));
//            }
//        }
    }


}
