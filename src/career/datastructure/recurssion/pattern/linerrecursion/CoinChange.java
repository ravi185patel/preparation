package career.datastructure.recurssion.pattern.linerrecursion;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5},11));
    }

    public static int coinChange(int nums[],int target){
//        Arrays.sort(nums);
//        return coinChangeRec(nums,target,nums.length-1);
//        int n = nums.length;
//        int memo[][]=new int[n+1][target+1];
//        for(int i=0;i<=n;i++){
//            Arrays.fill(memo[i],-1);
//        }
//        return coinChangeMemo(nums,target,nums.length-1,memo);
        return coinChangeDp(nums,target,nums.length-1);
    }
    public static int coinChangeRec(int nums[],int target,int index){
        if(index == 0){
            if(target%nums[index] == 0){
                return target/nums[index];
            }
            return (int) 1e9;
        }
        int noTake = 0 + coinChangeRec(nums,target,index-1);
        int take = (int) 1e9;;
        if(nums[index] <= target) {
            take = 1 + coinChangeRec(nums, target - nums[index], index);
        }
        return Math.min(take,noTake);
    }

    public static int coinChangeMemo(int nums[],int target,int index,int memo[][]){
        if(index == 0){
            if(target%nums[index] == 0){
                return target/nums[index];
            }
            return (int) 1e9;
        }
        if(memo[index][target] != -1){
            return memo[index][target];
        }

        int noTake = 0 + coinChangeMemo(nums,target,index-1,memo);
        int take = (int) 1e9;;
        if(nums[index] <= target) {
            take = 1 + coinChangeMemo(nums, target - nums[index], index,memo);
        }

        return memo[index][target] = Math.min(take,noTake);
    }

    public static int coinChangeDp(int nums[],int target,int index){
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        int INF = (int)1e9;

        // initialize base case as you can see in recursion and why 0 to target because when index =0 target can be 0 to target
        // and check if possible to achieve we set it or infinity
        for (int t = 0; t <= target; t++) {
            if (t % nums[0] == 0) dp[0][t] = t / nums[0];
            else dp[0][t] = INF;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int noTake = dp[i - 1][t];
                int take = INF;
                if (nums[i] <= t) // here as in recursion you check nums[i] <= target ( here t is target)
                    take = 1 + dp[i][t - nums[i]];
                dp[i][t] = Math.min(take, noTake);
            }
        }

        return dp[n - 1][target] >= INF ? -1 : dp[n - 1][target];
    }
}
