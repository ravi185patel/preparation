package career.sixmonthssep.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MColoring {
    public static void main(String[] args) {
//        int V=4,edges[][]={{0,1},{1,3},{2,3},{3,0},{0,2}}, m= 3;
        int V=3,edges[][]={{0,1},{1,2},{0,2}}, m= 2;
        colorM(V,edges.length,edges,m);
    }

    private static void colorM(int V,int E,int edges[][],int colors){
        Map<Integer, List<Integer>> graph = generateGraph(V,E,edges);
        int color[]=new int[V];
        boolean res = dfs(0,color,V,colors,graph);
        System.out.println(res);
    }

    private static boolean dfs(int node,int colors[],int V,int m,Map<Integer, List<Integer>> graph){
        if(node == V){
            return true;
        }

        for(int i=1;i<=m;i++){
            if(isSafe(i,node,colors,graph)){
                colors[node] = i;
                if(dfs(node+1,colors,V,m,graph)){
                    return true;
                }
                colors[node] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int col, int node,int[] colors,Map<Integer, List<Integer>> graph){
        for(int nbNode:graph.get(node)){
            if(colors[nbNode] == col){
                return false;
            }
        }
        return true;
    }

    private static Map<Integer, List<Integer>> generateGraph(int V,int E,int edges[][]){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0;i<V;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int edge[]:edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }
}
