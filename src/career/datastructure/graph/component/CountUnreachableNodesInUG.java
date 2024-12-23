package career.datastructure.graph.component;

import career.datastructure.graph.template.DisJoinUnion;

import java.util.*;

public class CountUnreachableNodesInUG {
    static Map<Integer, List<Integer>> graph;
    static boolean visited[];
    public static void main(String[] args) {
//        int n = 3, edges[][] = {{0,1}, {0,2}, {1,2}};
        int n = 7, edges[][] = {{0,2}, {0,5}, {2,4}, {1,6}, {5,4}};
        initializeData(n,edges);
        System.out.println(countOfUnreachableNode(n));
        System.out.println(countOfUnreachableNodes(n,edges));
    }

    private static void initializeData(int n,int edges[][]){
        createAdjList(n,edges);
        visited=new boolean[n];
    }

    static int component=0;
    private static int countOfUnreachableNode(int n){
        int result=0;
        long N = n;
//        System.out.println(n);
        for(int i=0;i<n;i++){
            if(visited[i]==false){
//                component=1;
//                long comp = dfs(i);
                long comp = bfs(i);
                result += (N-comp)*comp;
                N -= comp;
//                result += (n-component)*component;
//                n = n-component;
//                System.out.println(result+" "+component+" = "+n);
            }
        }
        return result;
    }

    private static long dfs(int node){
        long count=1;
        visited[node]=true;
        for(int nbNode:graph.get(node)){
            if(visited[nbNode]==false){
                count+=dfs(nbNode);
            }
        }
        return count;
    }

    private static long bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        long count=0;
        visited[node]=true;
        while(!queue.isEmpty()) {
            node = queue.poll();
            count++;
            for (int nbNode : graph.get(node)) {
                if (visited[nbNode] == false) {
                    queue.add(nbNode);
                    visited[nbNode]=true;
                }
            }
        }
        return count;
    }

    private static long countOfUnreachableNodes(int n,int edges[][]){
        DisJoinUnion ds = new DisJoinUnion(n);
        for(int edge[]:edges){
            ds.unionBySize(edge[0],edge[1]);
        }
        System.out.println(Arrays.toString(ds.size));
        Map<Integer, Integer> componentSize = new HashMap<>();
        for(int i= 0; i<n; i++) {
            int parent = ds.find(i);
            if(componentSize.containsKey(parent)) {
                componentSize.put(parent, componentSize.get(parent) + 1);
            } else {
                componentSize.put(parent, 1);
            }
        }

        long numberOfPaths = 0;
        long remainingNodes = n;
        for (int size : componentSize.values()) {
            numberOfPaths += size * (remainingNodes - size);
            remainingNodes -= size;
        }
        return numberOfPaths;

    }

    private static void createAdjList(int n,int edges[][]){
        graph = new HashMap<>();
        for(int i=0;i<n;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int edge[]:edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    }
}
