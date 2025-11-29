package career.datastructure.sllidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/
 */
public class CountSubarraysWithFixedBounds {
    public static void main(String[] args) {
        System.out.println(countSubarrays(new int[]{1,1,1,1},1,1));
    }
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        // subarray which has min == minK and max = maxK
        // if value < min || max > maxK shrink window

        long left=0,count=0;
        int minIndex=-1,maxIndex=-1,culIndex=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i] < minK || nums[i]> maxK){
                culIndex = i;
            }
            if(nums[i] == minK){
                minIndex = i;
            }

            if(nums[i] == maxK){
                maxIndex = i;
            }

            int smaller = Math.min(maxIndex,minIndex);
            long temp = smaller - culIndex;
            count += temp <= 0 ? 0 : temp;

        }
        return count;
    }

    public long countSubarraysDQ(int[] nums, int minK, int maxK) {
        long count = 0;
        int left = 0;
        Deque<Integer> dq_min = new ArrayDeque<>();
        Deque<Integer> dq_max = new ArrayDeque<>();

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                dq_min.clear();
                dq_max.clear();
                left = i + 1;
                continue;
            }
            while (!dq_min.isEmpty() && nums[dq_min.peekLast()] >= nums[i]) dq_min.pollLast();
            dq_min.offerLast(i);
            while (!dq_max.isEmpty() && nums[dq_max.peekLast()] <= nums[i]) dq_max.pollLast();
            dq_max.offerLast(i);
            if (nums[dq_min.peekFirst()] == minK && nums[dq_max.peekFirst()] == maxK) {
                int start = Math.min(dq_min.peekFirst(), dq_max.peekFirst());
                count += (start - left + 1);
            }
        }
        return count;
    }

    public long countSubarraysLinerWay(int[] nums, int minK, int maxK) {
        long count = 0;
        int start = -1, mini = -1, maxi = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) start = i;
            if (nums[i] == maxK) maxi = i;
            if (nums[i] == minK) mini = i;
            int valid = Math.max(0, Math.min(mini, maxi) - start);
            count += valid;
        }
        return count;
    }

}
