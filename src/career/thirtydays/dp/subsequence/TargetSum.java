package career.thirtydays.dp.subsequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    public static void main(String[] args) {
//        int nums[] = {1, 1, 1, 1, 1}, target = 3;
//        int nums[] = {1}, target = 1;
//        int nums[] = {0}, target = 0;
        performTest(new int[]{1, 1, 1, 1, 1},3);
        performTest(new int[]{1},1);
        performTest(new int[]{0},0);
        performTest(new int[]{100},-200);
    }

    public static void performTest(int nums[],int target) {
//        System.out.println(findTargetSumWaysRec(nums,0,target));
//        System.out.println(findTargetSumWays(nums,target));
        System.out.println(findPathSumEqualTargetDp(nums,target));
        System.out.println(findPathSumEqualTargetDpOwn(nums,Math.abs(target)));
        /* why need to make target positive because to achieve negative you can do
        * only minus will make value equal target.
        * */

    }

    public static int findTargetSumWaysRec(int[] nums,int index, int target) {
        if(index == nums.length){
            return target == 0 ? 1:0;
        }

        /*
            We must put minus or plus between each index of nums array.
            if you put below condition there may be possibility that you can achieve target
            in mid of array which is wrong.
        */
//        if(target == 0){
//            return 1;
//        }

        int minusTaken = findTargetSumWaysRec(nums,index+1,target-nums[index]);
        int plusTaken = findTargetSumWaysRec(nums,index+1,target+nums[index]);

        return minusTaken + plusTaken;

    }

    public static int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);

        for (int num : nums) {
            Map<Integer, Integer> nextDp = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int total = entry.getKey();
                int count = entry.getValue();
                nextDp.put(total + num,
                        nextDp.getOrDefault(total + num, 0) + count);
                nextDp.put(total - num,
                        nextDp.getOrDefault(total - num, 0) + count);
            }
            dp = nextDp;
        }
        return dp.getOrDefault(target, 0);
    }


    /*
    Intuition :
    s1-s2 = d
    s1+s2 = totalSum
    2s1 = d+totalSum
    s1 = d+totalSum/2 => wht we need to find

    int nums[] = {1, 1, 1, 1, 1}, target = 3;
    totalSum = 5
    d = 3
    s1 = 4 => (1,1,1,1)-(1) = 3 ans right.

    OR
    totalSum - d = 5 - d = 5-3 = 2/2 = 1 which is and => (1,1,1,1) - (1) = 3 ans


     */

    private static int findPathSumEqualTargetDpOwn(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || (sum + target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (sum + target) / 2;

        int[] dp = new int[subsetSum + 1];

        dp[0] = 1; // base case when target becomes zero you need to set it 1.
        if(nums[0] <= subsetSum){
            dp[nums[0]]+=1; // why ? because target is achieve two ways. minus / plus.
            // to handle base case if given target 0 which is achieved two ways.
        }
        int curr[]=new int[subsetSum + 1];
        for(int i=1;i<nums.length;i++){
            for(sum=0;sum<=subsetSum;sum++){
                int noTake = dp[sum];
                int take =0;
                if(sum >= nums[i]) {
                    take= dp[sum - nums[i]];
                }
                curr[sum]=noTake + take;
            }
            dp = curr.clone();
        }
        return dp[subsetSum];
    }
    private static int findPathSumEqualTargetDp(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || (sum - target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (sum - target) / 2;

        int[] dp = new int[subsetSum + 1];

        dp[0] = 1;

        for (int num : nums) {
            for (int j = subsetSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[subsetSum];
    }
}
