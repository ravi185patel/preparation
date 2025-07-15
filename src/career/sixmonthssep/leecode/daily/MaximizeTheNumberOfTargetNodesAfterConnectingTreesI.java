package career.sixmonthssep.leecode.daily;


import java.util.*;

/*
https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/

 */
public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {
    public static void main(String[] args) {

    }

    public static int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int nodes = edges1.length;
        Map<Integer, List<Integer>> adjList1 = generateAdjList(edges1);
        Map<Integer, List<Integer>> adjList2 = generateAdjList(edges2);

        int count1[]=new int[nodes];
        for(int i=0;i<nodes;i++) {
            count1[i] = bfs(adjList1,i,k,nodes);
        }

        int count2[]=new int[nodes];
        for(int i=0;i<nodes;i++){
            count2[i] = bfs(adjList2,i,k,nodes);
        }

        int maxCount2 = 0;
        for (int c : count2) {
            if (c > maxCount2) {
                maxCount2 = c;
            }
        }
        int[] res = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            res[i] = count1[i] + maxCount2;
        }
        return res;


    }

    public static int bfs(Map<Integer, List<Integer>> adjList,int start,int limit,int n){
        boolean visited[]=new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int count=0;
        visited[start]=true;

        while(!queue.isEmpty()){
            int parent = queue.poll();
            count++;
            for(int child:adjList.getOrDefault(parent,new ArrayList<>())){
                if(visited[child]== false && count <= limit){
                    visited[child]=true;
                    queue.add(child);
                }
            }
        }
        return count;
    }

    public static Map<Integer, List<Integer>> generateAdjList(int [][]edges){
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int edge[]:edges){
            adjList.putIfAbsent(edge[0],new ArrayList<>());
            adjList.putIfAbsent(edge[1],new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        return adjList;
    }
}
