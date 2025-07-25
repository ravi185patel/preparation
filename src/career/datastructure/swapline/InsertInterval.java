package career.datastructure.swapline;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/insert-interval/description/?envType=problem-list-v2&envId=ak9fi45v
 */
public class InsertInterval {
    public static void main(String[] args) {


    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // using binary search
        // If the intervals vector is empty, return a vector containing the newInterval
        if (intervals.length == 0) {
            return new int[][] { newInterval };
        }

        int n = intervals.length;
        int target = newInterval[0];
        int left = 0, right = n - 1;

        // Binary search to find the position to insert newInterval
        while (left <= right) {
            int mid = (left + right) / 2;
            if (intervals[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Insert newInterval at the found position
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < left; i++) {
            result.add(intervals[i]);
        }
        result.add(newInterval);
        for (int i = left; i < n; i++) {
            result.add(intervals[i]);
        }

        // Merge overlapping intervals
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : result) {
            // If res is empty or there is no overlap, add the interval to the result
            if (merged.isEmpty() ||
                    merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
                // If there is an overlap, merge the intervals by updating the end of the last
                // interval in res
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(
                        merged.get(merged.size() - 1)[1],
                        interval[1]);
            }
        }

        return merged.toArray(new int[0][]);
    }

    public int[][] insert1(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int n = intervals.length, i = 0;

        // Case 1: No overlapping before merging intervals
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Case 2: Overlapping and merging intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        res.add(newInterval);

        // Case 3: No overlapping after merging newInterval
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}
