package career.sixmonthssep.dp.onedimensional_fibonaccistyle;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/delete-and-earn/description/?envType=study-plan-v2&envId=dynamic-programming
You are given an integer array nums.
You want to maximize the number of points you get by performing the following operation
any number of times:

Pick any nums[i] and delete it to earn nums[i] points.
Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.


Example 1:

Input: nums = [3,4,2]
Output: 6

Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.


Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.
 */
public class DeleteAndEarn {
    public static void main(String[] args) {
//            int nums[]={2,2,3,3,3,4};
            int nums[]={3,4,2};
            System.out.println(deleteAndEarn(nums));
    }

    static Map<Integer,Integer> points,cache;
    private static int deleteAndEarn(int[] nums) {
        points = new HashMap<>();
        cache = new HashMap<>();

        int max=0;
        for(int num:nums){
            points.put(num,points.getOrDefault(num,0)+num);
            max = Math.max(max,num);
        }

//        return maxEarn(max);
        return maxEarnDp(max);
    }

    private static int maxEarn(int num){
        // Check for base cases
        if (num == 0) {
            return 0;
        }

        if (num == 1) {
            return points.getOrDefault(1, 0);
        }

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        // Apply recurrence relation
        int gain = points.getOrDefault(num, 0);
        cache.put(num, Math.max(maxEarn(num - 1), maxEarn(num - 2) + gain));
        return cache.get(num);

    }

    private static int maxEarnDp(int maxNumber){
        // Declare our array along with base cases
        int[] maxPoints = new int[maxNumber + 1];
        maxPoints[1] = points.getOrDefault(1, 0);

        for (int num = 2; num < maxPoints.length; num++) {
            // Apply recurrence relation
            int gain = points.getOrDefault(num, 0);
            maxPoints[num] = Math.max(maxPoints[num - 1], maxPoints[num - 2] + gain);
        }

        return maxPoints[maxNumber];
    }
}
