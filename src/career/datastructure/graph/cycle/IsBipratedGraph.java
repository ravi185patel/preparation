package career.datastructure.graph.cycle;

import java.util.*;

/*

odd length cycle graph != bipartite graph
even length cycle graph = bipartite graph

Hint:
Dividing a graph into 2 parts(grouping)
 */

public class IsBipratedGraph {
    public static void main(String[] args) {
//        int [][] graph = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
        int [][]graph =  {{1,3}, {0,2}, {1,3}, {0,2}};
        boolean visited[]=new boolean[graph.length];
        int colorDp[]=new int[graph.length];
        Arrays.fill(colorDp,-1);
        System.out.println(isBiprated(graph,visited,colorDp));
    }


    private static boolean isBiprated(int[][]graph,boolean visited[],int parent[]){

        for(int i=0;i<graph.length;i++){
//            if(visited[i]==false && dfs(i,0,graph,visited,parent)){
//                return false;
//            }
            if(visited[i]==false && bfs(i,0,graph,visited,parent)){
                return false;
            }
            System.out.println(Arrays.toString(parent));

        }
        return true;

    }

    private static boolean dfs(int node,int color,int [][] graph,boolean visited[],int colorDp[]){
        visited[node]=true;
        colorDp[node]=color;
        for(int nbNode:graph[node]){
            if(visited[nbNode]==false){
//                visited[nbNode]=true;
                if(dfs(nbNode,1-color,graph,visited,colorDp)){
                    // traverse child with 0 color if parent has 1 and vice versa
                    // only skip call when node is already visited and have different color then parent.
                    return true;
                }
            }else if(colorDp[nbNode] == color){ // parent and child color can't be same
                return true;
            }
        }
        return false;
    }


    private static boolean bfs(int node,int color,int [][] graph,boolean visited[],int colorDp[]){
        Queue<Integer> queue = new LinkedList<>();
        visited[node]=true;
        colorDp[node]=color;
        queue.add(node);
        while(!queue.isEmpty()){
            node = queue.poll();
            color = colorDp[node];
            for(int nbNode:graph[node]){
                if(visited[nbNode]==false){
                    visited[nbNode]=true;
                    colorDp[nbNode]=1-color;
                    queue.add(nbNode);
                }else if(colorDp[nbNode] == color){ // parent and child color can't be same
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBipartite(int[][] graph) {
        List<List<Integer>> adj = new ArrayList<>();
        int n = graph.length;
        int m = graph[0].length;

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<n;i++){
            for(int nb:graph[i]){
                adj.get(i).add(nb);
                adj.get(nb).add(i);
            }
        }

        int color[]=new int[n];
        Arrays.fill(color,-1);

        for(int i=0;i<n;i++){
            if(color[i] == -1){
                if(dfs(i,0,color,adj) == false){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int node,int col,int color[],List<List<Integer>> adj){
        color[node] = col;
        for(int i:adj.get(node)){
            if(color[i] == -1){
                if(dfs(i,1-col,color,adj)== false){
                    return false;
                }
            }else if(color[i] == col){
                return false;
            }
        }

        return true;
    }
}
