package career.datastructure.leetcode;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        System.out.println(maxCapacity(new int[]{4,8,5,3},new int[]{1,5,2,7},8));
        System.out.println(maxCapacity(new int[]{3,5,7,4},new int[]{2,4,3,6},7));
        System.out.println(maxCapacity(new int[]{2,2,2},new int[]{3,5,4},5));
        System.out.println(maxCapacity(new int[]{1,7,3},new int[]{7,3,5},13));
        System.out.println(maxCapacity(new int[]{2,2},new int[]{4,5},10));
        System.out.println(maxCapacity(new int[]{7,5,2,5},new int[]{7,9,9,1},13));
    }
    public static int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        if (n == 0) return 0;

        int[][] temp = new int[n][2];
        for (int i = 0; i < n; i++) {
            temp[i][0] = costs[i];
            temp[i][1] = capacity[i];
        }
        Arrays.sort(temp, (i,j)->i[0]-j[0]);

        int maxCap = 0;

        int[] prefixMax = new int[n];
        prefixMax[0] = temp[0][1];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], temp[i][1]);
        }

        int left = 0;
        int right = n - 1;

        while (right >= 0) {

            if (temp[right][0] < budget) {
                maxCap = Math.max(maxCap, temp[right][1]);
            }

            while (left < right && temp[left][0] + temp[right][0] < budget) {
                left++;
            }

            if (left > 0) {
                int validLeftIdx = Math.min(left - 1, right - 1);
                if (validLeftIdx >= 0 && temp[validLeftIdx][0] + temp[right][0] < budget) {
                    maxCap = Math.max(maxCap, temp[right][1] + prefixMax[validLeftIdx]);
                }
            }
            right--;
        }

        return maxCap;
    }

    // First index where cost > target
    private static int upperBound(int[][] items, int target) {
        int low = 0, high = items.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (items[mid][0] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
