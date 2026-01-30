package career.datastructure.dp.dponsequence;

import java.util.Arrays;

public class CountPartitionsWithGivenDifference {
    public static void main(String[] args) {
        System.out.println(countPartitions(new int[]{1, 1, 2, 3},1));
        System.out.println(countPartitions(new int[]{1, 2, 3, 4},2));
    }

    public static int countPartitions(int nums[],int diff){
        int sum = Arrays.stream(nums).sum()+diff;
        if(sum%2 != 0){
            return 0;
        }
//        return countPartitionsRec(nums.length-1,sum/2,nums);
        return countPartitionsDp(nums.length-1,sum/2,nums);
    }

    public static int countPartitionsRec(int index,int target,int nums[]){
        if(target == 0){
            return 1;
        }

        if(index < 0){
            return 0;
        }


        int noTake = countPartitionsRec(index-1,target,nums);
        int take = 0;
        if(target >= nums[index]){
            take = countPartitionsRec(index-1,target-nums[index],nums);
        }
        return noTake + take;
    }


    public static int countPartitionsDp(int index,int target,int nums[]){

        int n = nums.length;
        int dp[]=new int[target+1];
        dp[0]=1;
        if(nums[0] <= target){
            dp[nums[0]]+=1;
        }

        for(int i=1;i<n;i++){
            int curr[]=new int[target+1];
            for(int sum=0;sum<=target;sum++){
                curr[sum] = dp[sum];
                if(sum >= nums[i]) {
                    curr[sum] += dp[sum-nums[i]];
                }
            }
            dp= curr.clone();
        }
        return dp[target];
    }
}
