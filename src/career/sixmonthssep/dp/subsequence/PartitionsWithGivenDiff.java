package career.sixmonthssep.dp.subsequence;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1

 */
public class PartitionsWithGivenDiff {
    public static void main(String[] args) {
        int arr[] = {5, 2, 6, 4}, d = 3;
//        int arr[] = {1, 1, 1, 1}, d = 0;
//        int arr[] = {1, 2, 1, 0, 1, 3, 3}, d = 11; // 16
//        int arr[] = {0,1,2,2,2,3,0,3,0,1}, d = 12; // 16

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

        /*
        Now, the following cases can arise when we are at index 0,
        if the target sum is 0 and the first index is also 0,
        like in case [0,1], we can form the subset in two ways,
        either by considering the first element or leaving it, so we can return 2.

        Else at index 0, if target == 0, and the first element is not 0,
        it means we will not pick the first element so we just return 1 way.

        Or if at index 0, when the first element is not 0,
        and the target is equal to the first element ,
        then we will include it in the subset and we will return 1 way.

         */

        prev[0]= nums[0] == 0 ? 2:1;

        if(nums[0]!= 0 && nums[0] <= target){
            prev[nums[0]]=1;
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
