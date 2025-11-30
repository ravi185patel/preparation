package career.datastructure.sllidingwindow;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/description/
 */
public class MinimumSwapsGroupAll1TogetherII {
    public static void main(String[] args) {
        System.out.println(minSwaps(new int[]{0,1,0,1,1,0,0}));
    }

    public static int minSwaps(int[] nums) {
        int n = nums.length;

        int countOnes = Arrays.stream(nums).sum();

        int i = 0;
        int j = 0;
        int currCount = 0;
        int maxCount = 0;

        while (j < 2 * n) { // circular array = 2*n
            if (nums[j % n] == 1) {
                currCount++;
            }

            if (j - i + 1 > countOnes) { // window size = current ones in array
                currCount -= nums[i % n];
                i++;
            }

            maxCount = Math.max(maxCount, currCount);
            j++;
        }

        return countOnes - maxCount;
        /*// Calculate the minimum swaps needed to group all 1s or all 0s together
        int op1 = minSwapsHelper(nums, 0); // Grouping all 0s together
        int op2 = minSwapsHelper(nums, 1); // Grouping all 1s together
        return Math.min(op1, op2);*/
    }

    // Helper function to calculate the minimum swaps required
    // to group all `val` together
    public int minSwapsHelper(int[] data, int val) {
        int length = data.length;
        int totalValCount = 0;

        // Count the total number of `val` in the array
        for (int i = length - 1; i >= 0; i--) {
            if (data[i] == val) totalValCount++;
        }

        // If there is no `val` or the array is full of `val`,
        // no swaps are needed
        if (totalValCount == 0 || totalValCount == length) return 0;

        int start = 0, end = 0;
        int maxValInWindow = 0, currentValInWindow = 0;

        // Initial window setup: count the number of `val` in
        // the first window of size `totalValCount`
        while (end < totalValCount) {
            if (data[end++] == val) currentValInWindow++;
        }
        maxValInWindow = Math.max(maxValInWindow, currentValInWindow);

        // Slide the window across the array to find the
        // maximum number of `val` in any window
        while (end < length) {
            if (data[start++] == val) currentValInWindow--;
            if (data[end++] == val) currentValInWindow++;
            maxValInWindow = Math.max(maxValInWindow, currentValInWindow);
        }

        // Minimum swaps are the total `val` minus
        // the maximum found in any window
        return totalValCount - maxValInWindow;
    }

    public static int minSwapsUtil(int[] nums) {
        int n = nums.length;

        int countOnes = Arrays.stream(nums).sum();

        int i = 0;
        int j = 0;
        int currCount = 0;
        int maxCount = 0;

        while (j < 2 * n) { // circular array = 2*n
            if (nums[j % n] == 1) {
                currCount++;
            }

            if (j - i + 1 > countOnes) { // window size = current ones in array
                currCount -= nums[i % n];
                i++;
            }

            maxCount = Math.max(maxCount, currCount);
            j++;
        }

        return countOnes - maxCount;
    }
}
