package career.datastructure.swapline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/remove-interval/description/?envType=problem-list-v2&envId=ak9fi45v

1] full overlapped. -> no need to add in list
2] left potion overlapped -> [removed end, left end]
3] right portion overlapped.-> [left start, removed start]

 */
public class RemoveInterval {
    public static void main(String[] args) {

    }

    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] interval : intervals) {
            // If there are no overlaps, add the interval to the list as is.
            if (interval[0] > toBeRemoved[1] || interval[1] < toBeRemoved[0]) {
                result.add(Arrays.asList(interval[0], interval[1]));
            } else {
                // Is there a left interval we need to keep?
                if (interval[0] < toBeRemoved[0]) {
                    result.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                // Is there a right interval we need to keep?
                if (interval[1] > toBeRemoved[1]) {
                    result.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        return result;
    }
}
