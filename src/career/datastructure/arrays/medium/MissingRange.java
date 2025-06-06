package career.datastructure.arrays.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingRange {
    public static void main(String[] args) {
        int nums[] = {0, 1, 3, 50, 75}, lower = 0 ,upper = 99;
    }
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 0) {
            return List.of(List.of(lower, upper));
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (nums[0] > lower) {
            ans.add(List.of(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > 1) { // to get missing range
                ans.add(List.of(nums[i - 1] + 1, nums[i] - 1)); //very important
                // previous +1 to next-1
            }
        }
        if (nums[n - 1] < upper) {
            ans.add(List.of(nums[n - 1] + 1, upper));
        }
        return ans;
    }

}
