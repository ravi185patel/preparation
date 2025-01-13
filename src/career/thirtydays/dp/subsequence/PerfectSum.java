package career.thirtydays.dp.subsequence;

import java.util.Arrays;

public class PerfectSum {
    public static void main(String[] args) {
//        int arr[] = {5, 2, 3, 10, 6, 8}, target = 10;
        int arr[] = {28,4,3,27,0,24,26}, target = 24;

        System.out.println(perfectSum(arr,target));
    }

    public static int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int prev[]=new int[target+1];
        prev[0]=1;
        if (nums[0] <= target) {
            prev[nums[0]] = 1;
        }
        System.out.println(Arrays.toString(prev));

        for(int j=1;j<nums.length;j++){
            int curr[]=new int[target+1];
            for(int sum=0;sum<=target;sum++){
                int noTake = prev[sum];
                int take = 0;
                if(sum >= nums[j]){
                    take = prev[sum-nums[j]];
                }
                curr[sum] = noTake + take;
            }
            System.out.println(Arrays.toString(curr));
            prev = curr.clone();
        }

        return prev[target];
    }
}
