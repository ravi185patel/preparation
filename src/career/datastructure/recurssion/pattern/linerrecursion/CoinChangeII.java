package career.datastructure.recurssion.pattern.linerrecursion;

public class CoinChangeII {
    public static void main(String[] args) {
        System.out.println(coinChangeII(new int[]{1,2,5},5));
    }

    public static int coinChangeII(int nums[],int target){
//        Arrays.sort(nums);
//        return coinChangeRecII(nums,target,nums.length-1);
//        int n = nums.length;
//        int memo[][]=new int[n+1][target+1];
//        for(int i=0;i<=n;i++){
//            Arrays.fill(memo[i],-1);
//        }
//        return coinChangeMemoII(nums,target,nums.length-1,memo);
        return coinChangeDp(nums,target,nums.length-1);
    }
    public static int coinChangeRecII(int nums[],int target,int index){
        if(index == 0){
            return target%nums[index] == 0 ? 1:0;
        }
        if(index < 0){
            return 0;
        }
        int noTake = coinChangeRecII(nums,target,index-1);
        int take = 0;
        if(nums[index] <= target) {
             take = coinChangeRecII(nums, target - nums[index], index);
        }
        return take+noTake;
    }

    public static int coinChangeMemoII(int nums[],int target,int index,int memo[][]){
        if(index == 0){
            return target%nums[index] == 0 ? 1:0;
        }
        if(index < 0){
            return 0;
        }
        if(memo[index][target] != -1){
            return memo[index][target];
        }
        int noTake = coinChangeMemoII(nums,target,index-1,memo);
        int take = 0;
        if(nums[index] <= target) {
            take = coinChangeMemoII(nums, target - nums[index], index,memo);
        }
        return memo[index][target] = take+noTake;
    }

    public static int coinChangeDp(int nums[],int target,int index){
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        int INF = (int)1e9;

        // initialize base case as you can see in recursion and why 0 to target because when index =0 target can be 0 to target
        // and check if possible to achieve we set it or infinity
        for (int t = 0; t <= target; t++) {
            if (t % nums[0] == 0) dp[0][t] =1;
            else dp[0][t] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int noTake = dp[i - 1][t];
                int take = 0;
                if (nums[i] <= t) // here as in recursion you check nums[i] <= target ( here t is target)
                    take = dp[i][t - nums[i]];
                dp[i][t] = take+noTake;
            }
        }

        return dp[n - 1][target];
    }
}
