package career.sixmonthssep.graph;

import java.util.ArrayList;

public class RedundantConnection {
    public static void main(String[] args) {

    }
    public static int[] findRedundantConnection(int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = edges.length;
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        // Process each edge and check for cycles
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);

            // Check if adding this edge creates a cycle
            if (isCycle(V, adj)) {
                return edge;
            }
        }
        return new int[0];
    }
    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V + 1]; // V+1 to handle 1-based indexing
        for (int i = 1; i <= V; i++) {
            if (!vis[i]) {
                if (dfs(adj, vis, i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> adj, boolean[] vis, int node, int parent) {
        vis[node] = true;
        for (Integer it : adj.get(node)) {
            if (!vis[it]) {
                if (dfs(adj, vis, it, node)) {
                    return true;
                }
            } else if (it != parent) {
                return true;
            }
        }
        return false;
    }
}
