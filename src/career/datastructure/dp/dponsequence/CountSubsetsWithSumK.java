package career.datastructure.dp.dponsequence;

public class CountSubsetsWithSumK {
    public static void main(String[] args) {
        System.out.println(countSubsets(3,new int[]{1,2,2,3}));
    }

    public static int countSubsets(int target,int nums[]){
//        return countSubsetsRec(nums.length-1,target,nums);
        return countSubsetsDp(nums.length-1,target,nums);
    }

    public static int countSubsetsRec(int index,int target,int nums[]){
        if(target == 0){
            return 1;
        }

        if(index < 0){
            return 0;
        }

        int noTake = countSubsetsRec(index-1,target,nums);
        int take = 0;
        if(target >= nums[index]){
            take = countSubsetsRec(index-1,target-nums[index],nums);
        }
        return noTake + take;
    }

    public static int countSubsetsDp(int index,int target,int nums[]){

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
