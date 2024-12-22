package career.datastructure.graph.cycle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleInGraphDirectedGraph {
    public static void main(String[] args) {
//        int adj[][]={{1}, {2}, {3}, {3}};
        int adj[][]={{1}, {}, {1}}; // case where detect cycle in undirected graph won't work for directed
//        int adj[][]={{1}, {2}, {1}};
//        int adj[][]={{1}, {}, {1}};
//        int adj[][]={{1}, {}, {1}};
        int length = adj.length+1;
        boolean visited[]=new boolean[length];
        boolean pathVisited[]=new boolean[length];
        /*
         why need extra pathVisited array ?
         because First dfs will mark all node visited and
         when second dfs start, it will start marking visited node
         and encounter already visited node along with their parent are different.
         which notify as cycle.

         So when try to find cycle in directed graph we need pathVisited to track
         which nodes are visited and use this array as flag to identify cycle.

         Here backtracking used now question is why and where ?
         when you complete one path and no cycle detected. you will go and search cycle in other
         path of graph.
         There may be some node which are part of multiple paths. so we need to mark them unvisited path
         then only we can mark them for second path. And use them to find cycle in path.

         */

        System.out.println(detectCycleUsingDfs(adj,visited,pathVisited));
        Arrays.fill(visited,false);
        System.out.println(detectCycleUsingBfs(adj,visited,pathVisited));

    }

    private static boolean detectCycleUsingDfs(int[][] adj,boolean visited[],boolean pathVisited[]){
        for(int i=0;i<adj.length;i++){
            if(visited[i]==false && dfs(i,i,adj,visited,pathVisited)){
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(int node,int parent,int[][]adj,boolean visited[],boolean pathVisited[]){
        visited[node]=true;
        pathVisited[node]=true;
        for(int nbNode:adj[node]){
            if(visited[nbNode]==false){
                if(dfs(nbNode,node,adj,visited,pathVisited)){
                    return true;
                }
            }else if(pathVisited[nbNode]){
                return true;
            }
        }
        pathVisited[node]=false;
        return false;
    }


    private static boolean detectCycleUsingBfs(int[][] adj,boolean visited[],boolean pathVisited[]){
        for(int i=0;i<adj.length;i++){
            if(visited[i]==false && bfs(i,i,adj,visited,pathVisited)){
                return true;
            }
        }
        return false;
    }

    private static boolean bfs(int node,int parent,int[][]adj,boolean visited[],boolean pathVisited[]){
        int parentNode[]=new int[adj.length+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{node,node});
        parentNode[node]=node;
        visited[node]=true;
        pathVisited[node]=true;

        while(!queue.isEmpty()){
            int visit[] = queue.remove();
            node = visit[0];
            for(int nbNode:adj[node]){
                if(visited[nbNode]==false){
                    parentNode[nbNode] = node;
                    visited[nbNode] = true;
                    pathVisited[nbNode]=true;
                    queue.add(new int[]{nbNode,node});
                }else if(pathVisited[nbNode]){
                    return true;
                }
            }
        }
        pathVisited[node]=false;
        return false;
    }
}
