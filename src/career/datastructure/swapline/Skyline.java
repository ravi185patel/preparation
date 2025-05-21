package career.datastructure.swapline;

import java.util.*;

/*
https://leetcode.com/problems/the-skyline-problem/description/?envType=problem-list-v2&envId=a4szt2rh

 */
public class Skyline {

    public static void main(String[] args) {

    }

    public List<List<Integer>> getSkylineBf(int[][] buildings){
        // Sort the unique positions of all the edges.
        SortedSet<Integer> edgeSet = new TreeSet<Integer>();
        for (int[] building : buildings) {
            int left = building[0], right = building[1];
            edgeSet.add(left);
            edgeSet.add(right);
        }
        List<Integer> edges = new ArrayList<Integer>(edgeSet);

        // Hast table 'edgeIndexMap' record every {position : index} pairs in edges.
        Map<Integer, Integer> edgeIndexMap = new HashMap<>();
        for (int i = 0; i < edges.size(); ++i) {
            edgeIndexMap.put(edges.get(i), i);
        }

        // Initialize 'heights' to record maximum height at each index.
        int[] heights = new int[edges.size()];

        // Iterate over all the buildings.
        for (int[] building : buildings) {
            // For each building, find the indexes of its left
            // and right edges.
            int left = building[0], right = building[1], height = building[2];
            int leftIndex = edgeIndexMap.get(left), rightIndex = edgeIndexMap.get(right);

            // Update the maximum height within the range [left_idx, right_idx)
            for (int idx = leftIndex; idx < rightIndex; ++idx) {
                heights[idx] = Math.max(heights[idx], height);
            }
        }

        List<List<Integer>> answer = new ArrayList<>();

        // Iterate over 'heights'.
        for (int i = 0; i < heights.length; ++i) {
            int currHeight = heights[i], currPos = edges.get(i);

            // Add all the positions where the height changes to 'answer'.
            if (answer.isEmpty() || answer.get(answer.size() - 1).get(1) != currHeight) {
                answer.add(Arrays.asList(currPos, currHeight));
            }
        }
        return answer;
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> result = new ArrayList<>();
            List<int[]> events = new ArrayList<>();

            for (int[] building : buildings) {
                events.add(new int[]{building[0], building[2], 0}); // Start event (0=start)
                events.add(new int[]{building[1], building[2], 1}); // End event (1=end)
            }

            // Sort events:
            // 1. By x (ascending)
            // 2. Start events before end events if same x
            // 3. For start events: higher height first
            // 4. For end events: lower height first
            Collections.sort(events, (a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0];
                if (a[2] != b[2]) return a[2] - b[2];
                return a[2] == 0 ? b[1] - a[1] : a[1] - b[1];
            });

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            maxHeap.add(0); // Ground level
            Map<Integer, Integer> toRemove = new HashMap<>(); // For lazy deletion

            int prevMax = 0;
            for (int[] event : events) {
                int x = event[0], height = event[1], isEnd = event[2];

                if (isEnd == 0) { // Start event
                    maxHeap.add(height);
                } else { // End event
                    toRemove.put(height, toRemove.getOrDefault(height, 0) + 1);
                }

                // Lazy removal of heights marked for deletion
                while (!maxHeap.isEmpty() && toRemove.containsKey(maxHeap.peek())) {
                    int top = maxHeap.poll();
                    toRemove.put(top, toRemove.get(top) - 1);
                    if (toRemove.get(top) == 0) toRemove.remove(top);
                }

                int currMax = maxHeap.peek();
                if (currMax != prevMax) { // Skyline height changed
                    result.add(Arrays.asList(x, currMax));
                    prevMax = currMax;
                }
            }

            return result;
        }
}
