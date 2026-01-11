package career.datastructure.recurssion.pattern.backtracking;

import java.util.*;

public class MColoringProblem {
    public static void main(String[] args) {
        int V = 4, edges[][] = {{0, 1},{1, 3},{2, 3},{3, 0},{0, 2}}, m = 3;
        System.out.println(graphColoring(V,edges,m));
    }

    public static boolean graphColoring(int v, int[][] edges, int m) {
        Map<Integer,List<Integer>> edgeList = edgeList(edges);
        int color[]=new int[v];
        Arrays.fill(color,-1);
        if(solve(0, edgeList, color, m, v)){
            return true;
        }
        return false;

    }

    public static boolean solve(int node, Map<Integer, List<Integer>> edges,int color[], int m,int v){
        if(node == v){
            return true;
        }
        for(int i=1;i<=m;i++){
            if(isSafe(node,color,edges)){
                color[node]=1;
                if(solve(node+1,edges,color,m,v)) return true;
                color[node]=-1;
            }
        }
        return false;
    }

    public static boolean isSafe(int node,int color[],Map<Integer,List<Integer>> edges){
        for(int nb:edges.get(node)){
            if(color[nb] != -1 && color[nb] == color[node]){
                return false;
            }
        }
        return true;
    }


    public static Map<Integer,List<Integer>> edgeList(int[][]edges){
        Map<Integer,List<Integer>> edgeList = new HashMap<>();
        for(int edge[]:edges){
            edgeList.putIfAbsent(edge[0], new ArrayList<>());
            edgeList.putIfAbsent(edge[1], new ArrayList<>());
            edgeList.get(edge[0]).add(edge[1]);
            edgeList.get(edge[1]).add(edge[0]);
        }
        return edgeList;
    }
}
