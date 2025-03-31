package career.datastructure.graph.template;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TemplateToFindMaxMinSumCntOfChildNode {
    public static boolean visited[]=new boolean[10];
    static Map<Integer, List<Integer>> graph;
    public static void main(String[] args) {

    }

    private static long dfs(int node){
        long count=1;
        /*
        if(node == null) return Integer.MIN_VALUE or Integer.MAX_VALUE or 0;
        long sum = node.val; // max = node.val // min = node.val;
         */
        visited[node]=true;
        for(int nbNode:graph.get(node)){
            if(visited[nbNode]==false){
                count+=dfs(nbNode);
                /*
                    max = Math.max(max,dfs(nbNode));
                 or  min = Math.min(min,dfs(nbNode));
                 or  sum += dfs(nbNode);
                 */
            }
        }
        return count;  // return max or min or sum;
    }

    private static long bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        long count=0;
        visited[node]=true;
        while(!queue.isEmpty()) {
            node = queue.poll();
            count++;
            /*
            sum+= node.val;
            max = Math.max(max,node.val);
            min = Math.min(min,node.val);
             */
            for (int nbNode : graph.get(node)) {
                if (visited[nbNode] == false) {
                    queue.add(nbNode);
                    visited[nbNode]=true;
                }
            }
        }
        return count; // sum or max or min
    }
}
