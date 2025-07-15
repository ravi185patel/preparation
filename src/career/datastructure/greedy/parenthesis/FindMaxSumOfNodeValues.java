package career.datastructure.greedy.parenthesis;

import java.util.Arrays;

/*
https://leetcode.com/problems/find-the-maximum-sum-of-node-values/description/?envType=daily-question&envId=2025-05-23

 */
public class FindMaxSumOfNodeValues {
    public static void main(String[] args) {

    }

    public long maximumValueSumMemo(int[] nums, int k, int[][] edges) {
        long[][] memo = new long[nums.length][2];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        return maxSumOfNodesMemo(0, 1, nums, k, memo);
    }

    private long maxSumOfNodesMemo(int index, int isEven, int[] nums, int k,
                               long[][] memo) {
        if (index == nums.length) {
            return isEven == 1 ? 0 : Integer.MIN_VALUE;
        }
        if (memo[index][isEven] != -1) {
            return memo[index][isEven];
        }
        // No operation performed on the element
        long noXorDone = nums[index] + maxSumOfNodesMemo(index + 1, isEven, nums, k, memo);
        // XOR operation is performed on the element
        long xorDone = (nums[index] ^ k) +
                maxSumOfNodesMemo(index + 1, isEven ^ 1, nums, k, memo);

        // Memoize and return the result
        return memo[index][isEven] = Math.max(xorDone, noXorDone);
    }

    public long maximumValueSumDp(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        dp[n][1] = 0;
        dp[n][0] = Integer.MIN_VALUE;

        for (int index = n - 1; index >= 0; index--) {
            for (int isEven = 0; isEven <= 1; isEven++) {
                long performOperation = dp[index + 1][isEven ^ 1] + (nums[index] ^ k);
                long dontPerformOperation = dp[index + 1][isEven] + nums[index];

                dp[index][isEven] = Math.max(performOperation, dontPerformOperation);
            }
        }

        return dp[0][1];
    }


    public long maximumValueSumGdSorting(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        int[] netChange = new int[n];
        long nodeSum = 0;

        for (int i = 0; i < n; i++) {
            netChange[i] = (nums[i] ^ k) - nums[i];
            nodeSum += nums[i];
        }

        Arrays.sort(netChange);
        // Reverse the sorted array
        for (int i = 0; i < n / 2; i++) {
            int temp = netChange[i];
            netChange[i] = netChange[n - 1 - i];
            netChange[n - 1 - i] = temp;
        }

        for (int i = 0; i < n; i += 2) {
            // If netChange contains odd number of elements break the loop
            if (i + 1 == n) {
                break;
            }
            long pairSum = netChange[i] + netChange[i + 1];
            // Include in nodeSum if pairSum is positive
            if (pairSum > 0) {
                nodeSum += pairSum;
            }
        }
        return nodeSum;
    }



    public long maximumValueSumGreedy(int[] nums, int k, int[][] edges) {
        long sum=0;
        int count=0;
        int minWg  = Integer.MAX_VALUE;

        for(int wg:nums){

            if((wg ^ k) > wg){
                sum+=(wg^k);
                count++;
            }else{
                sum+=wg;
            }

            minWg = Math.min(minWg,Math.abs(wg - (wg^k)));
        }

        if(count%2==0){
            return sum;
        }

        return sum - minWg;

    }

}
