package career.datastructure.graph.component;

import java.util.*;

public class NoOfProvinces {
    static Map<Integer, List<Integer>> adjList;
    public static void main(String[] args) {
//        int  [][] isConnected = {{1,1,0}, {1,1,0}, {0,0,1}};
        int  [][] isConnected = {{1,0,1}, {0,1,0}, {1,0,1}};
        createAdjList(isConnected);
        System.out.println(noOfProvDfs(new boolean[isConnected.length]));
    }

    private static void createAdjList(int[][]isConnected){
        adjList = new HashMap<>();
        for(int i=0;i<isConnected.length;i++){
            adjList.put(i,new ArrayList<>());
        }
        for(int i=0;i<isConnected.length;i++){
            for(int j=0;j<isConnected[0].length;j++){
                if(isConnected[i][j] == 1){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }
    }
    private static int noOfProvDfs(boolean visited[]){
        int component=0;
        for(int i=0;i<adjList.size();i++){
            if(visited[i] == false){
//                dfs(i,visited);
                bfs(i,visited);
                component++;
            }
        }
        return component;
    }

    private static void dfs(int node,boolean visited[]){
        visited[node]= true;
        for(int nbNode:adjList.get(node)){
            if(visited[nbNode] == false){
                dfs(nbNode,visited);
            }
        }
        // why method has only if not other conditions
        // because over here you are performing dfs where you are looking for continue path
        // and mark all node which are visited in path
        // if you add else with return it will end execution of dfs after first leaf node.
    }
    private static void bfs(int node,boolean visited[]){
        visited[node]= true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            node =queue.poll();
            for(int nbNode:adjList.get(node)){
                if(visited[nbNode] == false){
                    dfs(nbNode,visited);
                }
            }
        }
    }

}
