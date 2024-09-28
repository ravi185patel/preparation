package career.datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleInDAG {
    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(5);
        adj.get(4).add(2);

        DetectCycleInDAG obj = new DetectCycleInDAG();
        boolean ans = obj.isCyclic(V, adj);
        if (ans)
            System.out.println("True");
        else
            System.out.println("False");
    }
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
//        return isCyclicUseTopoSort(V,adj);
        return isCyclicUseDfs(V,adj);
    }

    public boolean isCyclicUseDfs(int V, ArrayList<ArrayList<Integer>> adj){
        int visited[]=new int[V];
        int parent[]=new int[V];

        for(int i=0;i<V;i++){
            if(visited[i] == 0){
                if(dfs(i,adj,visited,parent)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int p, ArrayList<ArrayList<Integer>> adj,int visited[],int parent[]){
        visited[p] = 1;
        parent[p] = 1;

        for(int nb:adj.get(p)){
            if(visited[nb] == 0){
                if(dfs(nb,adj,visited,parent)){
                    return true;
                }
            }
            else if(parent[nb] == 1){
                return true;
            }
        }
        parent[p] = 0;
        return false;

    }

    public boolean isCyclicUseTopoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int indegree[]=new int[V];
        // int visited[] = new int[V];

        for(int i=0;i<V;i++){
            for(int it:adj.get(i)){
                indegree[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<V;i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        int count=0;
        while(!queue.isEmpty()){
            int node = queue.remove();
            count++;
            for(int it:adj.get(node)){
                indegree[it]--;
                if(indegree[it] ==0){
                    queue.add(it);
                }
            }
        }
        if(count == V) return false;
        return true;
    }
}
