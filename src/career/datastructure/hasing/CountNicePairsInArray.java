package career.datastructure.hasing;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/count-nice-pairs-in-an-array/description/

SUGGESTION:
    When you see something like this ...[i] + ...[j] == ...[j] + ...[i]
    always regroup so that the terms with the same i/j are on the same side of the equation
    e.g. ...[i] - ...[i] == [j] - ...[j]

    Another example : Leetcode-2926 - Where you should rearrange i and j on either side to make it easy to handle

 */
public class CountNicePairsInArray {
    public static void main(String[] args) {

    }

    public int countNicePairs(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i] - rev(nums[i]);
        }

        Map<Integer, Integer> dic = new HashMap<>();
        int ans = 0;
        int MOD = (int) 1e9 + 7;
        for (int num : arr) {
            ans = (ans + dic.getOrDefault(num, 0)) % MOD;
            dic.put(num, dic.getOrDefault(num, 0) + 1);
        }

        return ans;
    }

    public int rev(int num) {
        int result = 0;
        while (num > 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }

        return result;
    }
}
