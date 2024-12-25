package career.datastructure.graph.path;

import java.util.*;

public class AlienDictionary {

    public static void main(String[] args) {
        String words[] = {"wrt","wrf","er","ett","rftt"};
        System.out.println(alienOrder(words));
    }
    static Map<Character, List<Character>> map;
    static boolean isValid = false;

    public static String alienOrder(String[] words) {
        // Write your code here
        if (words == null || words.length == 0) {
            return "";
        }

        // step 1: construct the graph
        //
        Map<Character, List<Character>> adjMap = new HashMap<>();
        constructGraph(words, adjMap);
        if(isValid) return "";
        int numNodes = adjMap.size();

        StringBuilder result = new StringBuilder();

        // toplogical sorting
        //
        Map<Character, Integer> indegreeMap = new HashMap<>();
        for (Character node : adjMap.keySet()) {
            indegreeMap.put(node, 0);
        }

        for (Character node : adjMap.keySet()) {
            for (Character neighbor : adjMap.get(node)) {
                int indegree = indegreeMap.get(neighbor);
                indegree += 1;
                indegreeMap.put(neighbor, indegree);
            }
        }

        // start from indegree=0
        Queue<Character> queue = new PriorityQueue<>();
        for (Character node : indegreeMap.keySet()) {
            if (indegreeMap.get(node) == 0) {
                // starting node, can only be one, cannot be 2 starting with 0 indegree
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            char curNode = queue.poll();
            result.append(curNode);

            for (char neighbor : adjMap.get(curNode)) {
                int indegree = indegreeMap.get(neighbor);
                indegree -= 1;
                indegreeMap.put(neighbor, indegree);

                // @note: key is here.
                // for A->B, B->C, A-C: C will not be counted until its indgree is 0

                if (indegree == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (result.length() < numNodes) {
            return "";
        }

        return result.toString();
    }

    private static void constructGraph(String[] words, Map<Character, List<Character>> adjMap) {
        // construct nodes
        for (String word : words) {
            for (Character c : word.toCharArray()) {
                adjMap.put(c, new ArrayList<>()); // c to all its next
            }
        }

        // construct edges
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String curr = words[i];
            if (prev.length() > curr.length() && prev.startsWith(curr)) {
                isValid = true;
                return;
            }

            for (int j = 0; j < prev.length() && j < curr.length(); j++) {
                if (prev.charAt(j) != curr.charAt(j)) {
                    adjMap.get(prev.charAt(j)).add(curr.charAt(j));
                    break;
                }
            }
        }
    }
}
