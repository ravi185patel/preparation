package career.datastructure.graph.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TarjanAlgo {
    public static void main(String[] args) {
        int  n = 4, connections[][] = {{0,1}, {1,2}, {2,0}, {1,3}};
        List<List<Integer>> lst = new ArrayList<>();
        for(int i=0;i<connections.length;i++){
            lst.add(new ArrayList<>());
            for(int node:connections[i]){
                lst.get(i).add(node);
            }
        }
        criticalConnections(n,lst);
    }
    private static int timer = 1;
    private static void dfs(int node, int parent, int[] vis,
                     ArrayList<ArrayList<Integer>> adj, int tin[], int low[],
                     List<List<Integer>> bridges) {
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;
        for (Integer it : adj.get(node)) {
            if (it == parent) continue;
            if (vis[it] == 0) {
                dfs(it, node, vis, adj, tin, low, bridges);
                low[node] = Math.min(low[node], low[it]); // compare parent with child to find lowest
                // why lowest because it is point which is bridge
                // node --- it
                if (low[it] > tin[node]) {
                    // bridge because
                    bridges.add(Arrays.asList(it, node));
                }
            } else {
                low[node] = Math.min(low[node], low[it]);
            }
        }
    }
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (List<Integer> it : connections) {
            int u = it.get(0); int v = it.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, vis, adj, tin, low, bridges);
        return bridges;
    }
}
