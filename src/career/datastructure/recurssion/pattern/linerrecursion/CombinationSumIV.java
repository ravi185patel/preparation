package career.datastructure.recurssion.pattern.linerrecursion;

public class CombinationSumIV {
    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1,2,3},4));
        System.out.println(combinationSum4(new int[]{9},3));
    }
    public static int combinationSum4(int[] nums,int target) {
//        return combinationSum4Rec(nums,target);
        return combinationSum4RecDp(nums,target);
    }
    public static int combinationSum4Rec(int[] nums, int target) {

        if(target < 0) return 0;
        if(target == 0) return 1;

        int count=0;
        for(int i=0;i<nums.length;i++){
            count+=combinationSum4Rec(nums,target-nums[i]);
        }
        return count;
    }

    public static int combinationSum4RecDp(int[] nums, int target) {
        int dp[]=new int[target+1];

        dp[0]=1;
        for(int i=0;i<=target;i++){
            for(int num:nums){
                if(i-num < 0 ) continue;
                dp[i]+=dp[i-num];
            }
        }
        return dp[target];
    }
}
