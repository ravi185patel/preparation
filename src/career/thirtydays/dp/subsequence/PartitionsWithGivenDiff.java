package career.thirtydays.dp.subsequence;

import java.util.Arrays;

/*

 */
public class PartitionsWithGivenDiff {
    public static void main(String[] args) {
        int arr[] = {5, 2, 6, 4}, d = 3;
//        int arr[] = {1, 1, 1, 1}, d = 0;
//        int arr[] = {1, 2, 1, 0, 1, 3, 3}, d = 11;

        int target=0;
        for(int i:arr){
            target+=i;
        }

        int res = 0;

        if(!(target - d < 0 || (target-d)%2 != 0)) {
            res = countPartitions(arr,(target-d)/2);
        }
        System.out.println(res);
    }

    private static int countPartitions(int[] nums, int target) {
        int n = nums.length;
        int prev[]=new int[target+1];
        prev[0]= nums[0] == 0 ? 2:1;

        if(nums[0]!= 0 && nums[0] <= target){
            prev[nums[0]]=1;
        }

        System.out.println(Arrays.toString(prev));

        for(int j=1;j<nums.length;j++){
            int curr[]=new int[target+1];
//            curr[0]=1;
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
