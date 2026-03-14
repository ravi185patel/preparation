package career.sixmonthssep.graph.practice;

import java.util.*;

public class EventualSafeState {
    public static void main(String[] args) {
        System.out.println(eventualSafeNodes(new int[][]{
                {1,2},{2,3},{5},{0},{5},{},{}
        }));
    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int m = graph.length;
        int n = graph[0].length;
        Map<Integer,List<Integer>> graphMap = createGraph(m,n,graph,false);
        boolean visited[] = new boolean[m];
        boolean pathVisited[] = new boolean[m];
        int parent[] = new int[m];
        for(int i=0;i<m;i++){
            parent[i]=i;
        }
        List<Integer> res = new ArrayList<>();
//        for(int i=0;i<m;i++){
//            if(visited[i] == false){
////                dfs(i,visited,pathVisited,graphMap);
//                bfs(i,visited,pathVisited,graphMap);
//
//            }
//        }
//        for(int key:graphMap.keySet()){
//            if(pathVisited[key]==false){
//                res.add(key);
//            }
//        }

        res = bfsTopoSort(m,n,graphMap);

        Collections.sort(res);
        return res;
    }

    public static boolean dfs(int node,boolean visited[],boolean pathVisited[],Map<Integer,List<Integer>> graph){
        visited[node]=true;
        pathVisited[node]=true;
        for(int nbNode:graph.get(node)){
            if(visited[nbNode] == false){
                if(dfs(nbNode,visited,pathVisited,graph)){
                    return true;
                }
            }else if(pathVisited[nbNode] == true){
                return true;
            }
        }
        pathVisited[node]=false;
        return false;
    }

    public static boolean bfs(int node,boolean visited[],boolean pathVisited[],Map<Integer,List<Integer>> graph){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node]=true;
        pathVisited[node]=true;
        while(!queue.isEmpty()) {
            node = queue.poll();
            for (int nbNode : graph.get(node)) {
                if (visited[nbNode] == false) {
                    visited[nbNode]=true;
                    pathVisited[nbNode]=true;
                    queue.add(nbNode);
                } else if (pathVisited[nbNode] == true) {
                    return true;
                }
            }
        }
        pathVisited[node]=false;
        return false;
    }

    public static List<Integer> bfsTopoSort(int m,int n,Map<Integer,List<Integer>> graph){
        List<Integer> lst = new ArrayList<>();
        int inDegree[]=new int[m];
        for(int key:graph.keySet()){
            for(int nbNode:graph.get(key)){
                inDegree[nbNode]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int key:graph.keySet()){
            if(inDegree[key]==0){
                queue.add(key);
            }
        }
        if(queue.size() == 0){
            return lst;
        }
        while(!queue.isEmpty()) {
            int node = queue.poll();
            lst.add(node);
            for (int nbNode : graph.get(node)) {
                inDegree[nbNode]--;
                if (inDegree[nbNode] == 0) {
                    queue.add(nbNode);
                }
            }
        }
        return lst;
    }

    public static Map<Integer,List<Integer>> createGraph(int m ,int n ,int graph[][],boolean flag){
        Map<Integer,List<Integer>> graphMap = new HashMap<>();
        for(int i=0;i<m;i++){
            graphMap.put(i,new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<graph[i].length;j++){
                if(flag) {
                    graphMap.get(i).add(graph[i][j]);
                }else{
                    graphMap.get(graph[i][j]).add(i);
                }
            }
        }
        return graphMap;
    }
}
