package career.interview.priceline;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MaxMinSumsOfMostSizeKSubarrays {
    public static void main(String[] args) {
        System.out.println(minMaxSubarraySum(new int[]{1,2,3},2));
    }
    public static long minMaxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            // maintain decreasing deque for max
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[i])
                maxDeque.pollLast();
            maxDeque.offerLast(i);

            // maintain increasing deque for min
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[i])
                minDeque.pollLast();
            minDeque.offerLast(i);

            // remove elements outside window of size k
            if (i - maxDeque.peekFirst() >= k)
                maxDeque.pollFirst();

            if (i - minDeque.peekFirst() >= k)
                minDeque.pollFirst();

            // count contributions for subarrays ending at i
            int start = Math.max(0, i - k + 1);

            sum += nums[maxDeque.peekFirst()];
            sum += nums[minDeque.peekFirst()];
        }

        return sum;
    }

    public static long minMaxSubarraySumPq(int[] nums, int k) {

        int n = nums.length;
        long sum = 0;

        // max heap → larger value first
        PriorityQueue<int[]> maxPQ =
                new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // min heap → smaller value first
        PriorityQueue<int[]> minPQ =
                new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int left = 0;

        for (int right = 0; right < n; right++) {

            maxPQ.offer(new int[]{nums[right], right});
            minPQ.offer(new int[]{nums[right], right});

            // keep window size ≤ k
            if (right - left + 1 > k) {
                left++;
            }

            // remove outdated elements (lazy removal)
            while (!maxPQ.isEmpty() && maxPQ.peek()[1] < left)
                maxPQ.poll();

            while (!minPQ.isEmpty() && minPQ.peek()[1] < left)
                minPQ.poll();

            // add max + min for window ending at right
            sum += maxPQ.peek()[0];
            sum += minPQ.peek()[0];
        }

        return sum;
    }
}
