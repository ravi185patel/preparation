package career.datastructure.graph.toposort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TopoSortUsingDFSAndBFS_KahnAlgo {
    public static void main(String[] args) {
//        int [][]adj = {{}, {0}, {0}, {0}};
        int [][]adj =  {{}, {3}, {3}, {}, {0,1}, {0,2}};
        boolean visited[]=new boolean[adj.length];
        dfsTopoSort(adj,visited); // graph -> indegree - toposort -> flow -> dfs/bfs
        Arrays.fill(visited,false);
        bfsTopoSort(adj,visited);  //
    }

    private static void dfsTopoSort(int[][]adj,boolean visited[]){
        int path[] = new int[adj.length];
        Stack<Integer> pathStk = new Stack<>();
        for(int i=0;i<adj.length;i++) {
            if(visited[i] == false){
                dfs(i, adj, visited, pathStk);
            }
        }
        int index=0;
        while(!pathStk.isEmpty()){
            path[index++] = pathStk.pop();
        }
        System.out.println(Arrays.toString(path));
    }

    private static void dfs(int node, int[][]adj, boolean visited[], Stack<Integer> path){
        visited[node]=true;
        for(int nbNode:adj[node]){
            if(visited[nbNode] == false){
                dfs(nbNode,adj,visited,path);
            }
        }
        path.push(node);
    }

    private static void bfsTopoSort(int[][]adj,boolean visited[]) {
        int path[] = new int[adj.length];
        int inDegree[]=new int[adj.length];

        for(int i=0;i<adj.length;i++){ // parent
            for(int nbNode:adj[i]){// child
                inDegree[nbNode]++; // inDegree of child become = parents (nb <- parent)
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<adj.length;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        int visitedNode=0; // important when asked for valid sort/sequence with visit all points
        int index=0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            path[index++]=node;
            visitedNode++; //
            for(int nbNode:adj[node]){
                inDegree[nbNode]--;
                if(inDegree[nbNode]==0){
                    queue.add(nbNode);
                }
            }
        }

//        return index == adj.length ? path:new int[]{-1,-1}; // return array only when all point visited.

        System.out.println(Arrays.toString(path));
    }

}
