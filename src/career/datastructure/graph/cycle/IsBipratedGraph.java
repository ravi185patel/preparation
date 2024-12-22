package career.datastructure.graph.cycle;

import java.util.Arrays;

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
            if(visited[i]==false && dfs(i,0,graph,visited,parent)){
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
                visited[nbNode]=true;
//                dfs(nbNode,1-color,graph,visited,colorDp);
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
    
}