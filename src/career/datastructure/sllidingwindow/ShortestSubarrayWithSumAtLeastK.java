package career.datastructure.sllidingwindow;

import java.util.*;

/*
https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
 */

class Pair<T,R>{

    T key;
    R value;
    public Pair(T x,R y) {
        this.key=key;
        this.value=value;
    }

    public T getKey(){
        return this.key;
    }

    public R getValue(){
        return this.value;
    }
}
public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        System.out.println(shortestSubarray1(new int[]{2,-1,2},3));
        System.out.println(shortestSubarray1(new int[]{48,99,37,4,-31},140));
        System.out.println(shortestSubarray1(new int[]{84,-37,32,40,95},167));
    }
    public static int shortestSubarray1(int[] nums, int k) {
        int n = nums.length;

        // Size is n+1 to handle subarrays starting from index 0
        long[] prefixSums = new long[n + 1];

        // Calculate prefix sums
        prefixSums[0]=nums[0];
        for (int i = 1; i <= n; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
        }

        Deque<Integer> candidateIndices = new ArrayDeque<>();

        int shortestSubarrayLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // Remove candidates from front of deque where subarray sum meets target
            while (
                    !candidateIndices.isEmpty() &&
                            prefixSums[i] - prefixSums[candidateIndices.peekFirst()] >=
                                    k
            ) {
                // Update shortest subarray length
                shortestSubarrayLength = Math.min(
                        shortestSubarrayLength,
                        i - candidateIndices.pollFirst()
                );
            }

            // Maintain monotonicity by removing indices with larger prefix sums
            while (
                    !candidateIndices.isEmpty() &&
                            prefixSums[i] <= prefixSums[candidateIndices.peekLast()]
            ) {
                candidateIndices.pollLast();
            }

            // Add current index to candidates
            candidateIndices.offerLast(i);
        }

        // Return -1 if no valid subarray found
        return shortestSubarrayLength == Integer.MAX_VALUE
                ? -1
                : shortestSubarrayLength;
    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;

        // Stack-like list to store cumulative sums and their indices
        List<Pair<Long, Integer>> cumulativeSumStack = new ArrayList<>();
        cumulativeSumStack.add(new Pair<>(0L, -1));

        long runningCumulativeSum = 0;
        int shortestSubarrayLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // Update cumulative sum
            runningCumulativeSum += nums[i];

            // Remove entries from stack that are larger than current cumulative sum
            while (
                    !cumulativeSumStack.isEmpty() &&
                            runningCumulativeSum <=
                                    cumulativeSumStack.get(cumulativeSumStack.size() - 1).getKey()
            ) {
                cumulativeSumStack.remove(cumulativeSumStack.size() - 1);
            }

            // Add current cumulative sum and index to stack
            cumulativeSumStack.add(new Pair<>(runningCumulativeSum, i));

            int candidateIndex = findCandidateIndex(
                    cumulativeSumStack,
                    runningCumulativeSum - k
            );

            // If a valid candidate is found, update the shortest subarray length
            if (candidateIndex != -1) {
                shortestSubarrayLength = Math.min(
                        shortestSubarrayLength,
                        i - cumulativeSumStack.get(candidateIndex).getValue()
                );
            }
        }

        // Return -1 if no valid subarray found
        return shortestSubarrayLength == Integer.MAX_VALUE
                ? -1
                : shortestSubarrayLength;
    }

    // Binary search to find the largest index where cumulative sum is <= target
    private int findCandidateIndex(
            List<Pair<Long, Integer>> nums,
            long target
    ) {
        int left = 0, right = nums.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums.get(mid).getKey() <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public int shortestSubarrayPq(int[] nums, int k) {
        int n = nums.length;

        // Initialize result to the maximum possible integer value
        int shortestSubarrayLength = Integer.MAX_VALUE;

        long cumulativeSum = 0;

        // Min-heap to store cumulative sum and its corresponding index
        PriorityQueue<Pair<Long, Integer>> prefixSumHeap = new PriorityQueue<>(
                (a, b) -> Long.compare(a.getKey(), b.getKey())
        );

        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Update cumulative sum
            cumulativeSum += nums[i];

            // If cumulative sum is already >= k, update shortest length
            if (cumulativeSum >= k) {
                shortestSubarrayLength = Math.min(
                        shortestSubarrayLength,
                        i + 1
                );
            }

            // Remove subarrays from heap that can form a valid subarray
            while (
                    !prefixSumHeap.isEmpty() &&
                            cumulativeSum - prefixSumHeap.peek().getKey() >= k
            ) {
                // Update shortest subarray length
                shortestSubarrayLength = Math.min(
                        shortestSubarrayLength,
                        i - prefixSumHeap.poll().getValue()
                );
            }

            // Add current cumulative sum and index to heap
            prefixSumHeap.offer(new Pair<>(cumulativeSum, i));
        }

        // Return -1 if no valid subarray found
        return shortestSubarrayLength == Integer.MAX_VALUE
                ? -1
                : shortestSubarrayLength;
    }
}
