package career.datastructure.dp.dponsequence;

public class CoinChange2 {
    public static void main(String[] args) {
        System.out.println(countWaysToMakeChange(new int[]{2, 4,10},10));
        System.out.println(countWaysToMakeChange(new int[]{5},5));
    }

    public static int countWaysToMakeChange(int nums[],int target){
//        return countWaysToMakeChangeRec(nums.length-1,target,nums);
        return countWaysToMakeChangeDp(nums.length-1,target,nums);
    }

    public static int countWaysToMakeChangeRec(int index,int target,int nums[]){
        if(target == 0){
            return 1;
        }

        if(index < 0){
            return 0;
        }

        int noTake = countWaysToMakeChangeRec(index-1,target,nums);
        int take =0;
        if(target >= nums[index]){
            take = countWaysToMakeChangeRec(index,target-nums[index],nums);
        }

        return noTake + take;
    }

    public static int countWaysToMakeChangeDp(int index,int target,int nums[]){
        int n = nums.length;
        int dp[]=new int[target+1];

        for(int sum=0;sum<=target;sum++){ // very important
            if(sum%nums[0] == 0) {
                dp[sum] = 1;
            }
        }
        for(int i=1;i<n;i++){
            int curr[]=new int[target+1];
            for(int sum=0;sum<=target;sum++){
                curr[sum] = dp[sum];
                if(sum >= nums[i]) {
                    curr[sum] += curr[sum-nums[i]]; // very important point when you need to consider
                    //same element at that movement you are taking element from curr row instead of prev.
                }
            }
            dp= curr.clone();
        }
        return dp[target];
    }
}
