package career.thirtydays.dp.subsequence;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    public static void main(String[] args) {
        int nums[] = {1, 1, 1, 1, 1}, target = 3;
        System.out.println(findTargetSumWaysRec(nums,0,target));
        System.out.println(findTargetSumWays(nums,target));
//        System.out.println(findPathSumEqaulTargetDp(nums,target));
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


    private static int findPathSumEqaulTargetDp(int[] nums, int target) {
        int n = nums.length;
        int total=0;
        for(int i:nums){
            total+=i;
        }
        if(target > total || target < -total) return 0;
        int prev[] = new int[2*total+1];
        for (int index = 0; index < n; index++) {
            prev[index] = nums[index];
        }
        prev[0] = 1;
        for (int index = 1; index < n; index++) {
            int curr[] = new int[n];
            for (int sum = 0; sum < target; sum++) {
                int minusEle = nums[index - 1] + prev[sum - nums[index]];
                int posEle = nums[index - 1] + prev[sum + nums[index]];
                curr[sum] = minusEle + posEle;
            }
            prev = curr.clone();
        }
        return prev[n - 1];

    }
}
