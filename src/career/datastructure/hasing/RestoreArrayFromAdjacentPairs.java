package career.datastructure.hasing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/description/

 */
public class RestoreArrayFromAdjacentPairs {
    public static void main(String[] args) {

    }

    Map<Integer, List<Integer>> graph = new HashMap<>();

    public int[] restoreArray(int[][] adjacentPairs) {
        for (int[] edge : adjacentPairs) {
            int x = edge[0];
            int y = edge[1];

            if (!graph.containsKey(x)) {
                graph.put(x, new ArrayList<>());
            }

            if (!graph.containsKey(y)) {
                graph.put(y, new ArrayList());
            }

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        int root = 0;
        for (int num : graph.keySet()) {
            if (graph.get(num).size() == 1) {
                root = num;
                break;
            }
        }

        // int[] ans = new int[graph.size()];
        // dfs(root, Integer.MAX_VALUE, ans, 0);
        // return ans;

        int curr = root;
        int[] ans = new int[graph.size()];
        ans[0] = root;
        int i = 1;
        int prev = Integer.MAX_VALUE;

        while (i < graph.size()) {
            for (int neighbor : graph.get(curr)) {
                if (neighbor != prev) {
                    ans[i] = neighbor;
                    i++;
                    prev = curr;
                    curr = neighbor;
                    break;
                }
            }
        }

        return ans;
    }

    private void dfs(int node, int prev, int[] ans, int i) {
        ans[i] = node;
        for (int neighbor : graph.get(node)) {
            if (neighbor != prev) {
                dfs(neighbor, node, ans, i + 1);
            }
        }
    }
}
