package career.sixmonthssep.leecode.daily;

import java.util.*;

/*
https://leetcode.com/problems/largest-color-value-in-a-directed-graph/?envType=daily-question&envId=2025-05-26

1] bfs
2] dfs
3] inDegree
4] union set
 */
public class LargestColorValueInDirectedGraph {
    public static void main(String[] args) {
//        String colors = "abaca"; int edges[][] = {{0,1},{0,2},{2,3},{3,4}};
//        String colors = "aa"; int edges[][] = {{0,0}};
//        String colors = "afdcd"; int edges[][] = {{0,1},{0,2},{2,3},{3,4}};
        String colors = "hhqhuqhqff"; int edges[][] =
                {{0,1},{0,2},{2,3},{3,4},{3,5},{5,6},{2,7},{6,7},{7,8},{3,8},{5,8},{8,9},{3,9},{6,9}};
        System.out.println(largestPathValue(colors,edges));
    }

    public static int largestPathValue(String colors, int[][] edges) {
        Map<Integer,List<Integer>> map = genGraph(edges);
        int inDegree[]=new int[colors.length()];
        for(int parent:map.keySet()){
            for(int child:map.get(parent)){
                inDegree[child]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int parent:map.keySet()){
            if(inDegree[parent] == 0){
                queue.add(parent);
            }
        }

        int nodeColor[][]=new int[colors.length()][26];
        for(int color=0;color<colors.length();color++){
            nodeColor[color][colors.charAt(color)-'a']++;
        }

        if(queue.isEmpty()){
            return -1;
        }
        int maxColorCnt=0;
        int visited=0;
        while(!queue.isEmpty()){
            int parent=queue.poll();
            visited++;
            for(int child:map.getOrDefault(parent,new ArrayList<>())){
                inDegree[child]--;
                for(int color=0;color<26;color++){
                    nodeColor[child][color]=
                            Math.max(nodeColor[child][color],
                                    nodeColor[parent][color] +
                                            (colors.charAt(child)-'a' == color ? 1:0));
                }


                if(inDegree[child] == 0) {
                    queue.add(child);
                }
            }

            maxColorCnt = Math.max(maxColorCnt,Arrays.stream(nodeColor[parent]).max().getAsInt());
        }


        return visited == colors.length() ? maxColorCnt :-1;
    }

    public static Map<Integer, List<Integer>> genGraph(int [][]edges){
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int edge[]:edges){
            map.putIfAbsent(edge[0],new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
        }
        return map;
    }

}
