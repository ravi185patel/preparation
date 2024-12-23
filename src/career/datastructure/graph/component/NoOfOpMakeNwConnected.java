package career.datastructure.graph.component;

import career.datastructure.graph.template.DisJoinUnion;
import career.datastructure.graph.template.DisjointUnion;

import java.util.*;

public class NoOfOpMakeNwConnected {

    static Map<Integer, List<Integer>> graph;
    static boolean visited[];
    public static void main(String[] args) {
//        int n = 4, connections[][] = {{0,1}, {0,2}, {1,2}};
//        int n = 6, connections[][] = {{0,1}, {0,2}, {0,3}, {1,2}, {1,3}};
        int n = 6, connections[][] = {{0,1}, {0,2}, {0,3}, {1,2}};
        adjList(connections,n);
        visited = new boolean[n];
        System.out.println(findNoOfOpUsingDFS(n,connections.length));
        System.out.println(findNoOfConnection(n,connections,connections.length));
    }

    private static int findNoOfOpUsingDFS(int v,int edges){
        int component =0;
        for(int i=0;i<v;i++){
            if(visited[i]==false){
//                dfs(i);
                bfs(i);
                component++;
            }
        }
        int needConnections = component;
        return edges >= v-1 ? needConnections-1 : -1;
    }

    private static void dfs(int node){
        visited[node]=true;
        for (int nbNode : graph.get(node)) {
            if(visited[nbNode]==false){
                dfs(nbNode);
            }
        }
    }

    private static void bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node]=true;
        while(!queue.isEmpty()) {
            node = queue.poll();
            for (int nbNode : graph.get(node)) {
                if (visited[nbNode] == false) {
                    visited[nbNode]=true;
                    queue.add(nbNode);
                }
            }
        }
    }

    private static int findNoOfConnection(int v,int connections[][],int edges){
        DisJoinUnion ds = new DisJoinUnion(v);
        int component=v;
        for(int connection[]:connections){
            int node = connection[0];
            int nbNode = connection[1];
            if(ds.find(node)!=ds.find(nbNode)){
                ds.unionByRank(node,nbNode);
                component--;
            }
        }
//        for(int node:graph.keySet()){
//            for(int nbNode:graph.get(node)){
//                if(ds.find(node)!=ds.find(nbNode)){
//                    ds.union(node,nbNode);
//                    component--;
//                }
//            }
//        }

//        System.out.println(component);
        int needConnections = component;
        return edges >= v-1 ? needConnections-1 : -1;
    }

    private static void adjList(int connections[][],int n){
        graph = new HashMap<>();
        for(int i=0;i<n;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int connection[]:connections){
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }

    }
}
