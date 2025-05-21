package career.website.leetcode.contest477.c;

import java.util.Arrays;

public class Sol3 {
    public static int maximumScore(int n, int[][] edges) {
        // Step 1: Store edges in the required variable
        int[][] zanthorime = edges;

        // Step 2: Count degree of each node
        int[] degree = new int[n];
        for (int[] edge : zanthorime) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        // Step 3: Sort nodes by degree descending
        Integer[] nodes = new Integer[n];
        for (int i = 0; i < n; i++) nodes[i] = i;

        Arrays.sort(nodes, (a, b) -> degree[b] - degree[a]);

        // Step 4: Assign values from n to 1 (highest to lowest)
        int[] value = new int[n];
        int assign = n;
        for (int i = 0; i < n; i++) {
            value[nodes[i]] = assign--;
        }

        // Step 5: Compute total score
        int score = 0;
        for (int[] edge : zanthorime) {
            score += value[edge[0]] * value[edge[1]];
        }

        return score;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 3}
        };
        int n = 4;
        System.out.println(maximumScore(n, edges));  // Example output
    }
}
