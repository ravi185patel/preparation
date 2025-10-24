package career.datastructure.swaplineordifferencearray;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-i/

You are given an integer array nums and two integers k and numOperations.

You must perform an operation numOperations times on nums, where in each operation you:

Select an index i that was not selected in any previous operations.
Add an integer in the range [-k, k] to nums[i].
Return the maximum possible frequency of any element in nums after performing the operations.



Example 1:

Input: nums = [1,4,5], k = 1, numOperations = 2

Output: 2

Explanation:

We can achieve a maximum frequency of two by:

Adding 0 to nums[1]. nums becomes [1, 4, 5].
Adding -1 to nums[2]. nums becomes [1, 4, 4].
Example 2:

Input: nums = [5,11,20,20], k = 5, numOperations = 1

Output: 2

Explanation:

We can achieve a maximum frequency of two by:

Adding 0 to nums[1].


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
0 <= k <= 105
0 <= numOperations <= nums.length

 */
public class MaxFreqElementAfterPerformOpII {
    public static void main(String[] args) {

    }

    public static int maxFrequency(int[] nums, int k, int numOperations) {
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);

//        int[] freq = new int[maxVal + 1];
        Map<Integer,Integer> freq = new HashMap<>();
        for (int x : nums) {
//            freq[x]++;
            freq.put(x,freq.getOrDefault(x,0)+1);
        }

//        int[] diff = new int[maxVal + 2]; // extra for safety
        Map<Integer,Integer> diff = new TreeMap<>();
        for (int x:nums) {
            int left = Math.max(0, x - k);
            int right = Math.min(maxVal, x + k);
            diff.put(left,diff.getOrDefault(left,0)+1);
            diff.put(right+1,diff.getOrDefault(left+right,0)-1);
        }

        int res = 1,prevTarget=-1;
        for (int target : diff.keySet()) {
            if(prevTarget == -1){
                prevTarget = target;
                continue;
            }
            int current = (prevTarget != -1 ? diff.getOrDefault(prevTarget,0):0);
            diff.put(target,diff.getOrDefault(target,0)+current);

            int already = freq.getOrDefault(target,0);
            int toChange = diff.getOrDefault(target,0);;
            int maxPerFreq = Math.min(toChange,numOperations);
            res = Math.max(res,already+maxPerFreq);
        }
        return res;
    }

}
