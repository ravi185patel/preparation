package career.datastructure.dp.mcmandpartitiondp;

public class CheckPartitionArrayIsValidOrNot {
    public static void main(String[] args) {
        System.out.println(validPartition(new int[]{2,2,2,4,4,4,5,6,7}));
    }
    public static boolean validPartition(int[] nums) {

//        return solveRec(nums,0,nums.length);
        return solveDP(nums,nums.length);
    }

    public static boolean solveRec(int nums[],int i,int n){
        if(i == n) return true;

        boolean ans = false;
        if(i+1 < n && nums[i] == nums[i+1]){
            ans = solveRec(nums,i+2,n);
        }
        if(!ans && i+2 < n ){
            if(nums[i] == nums[i+1] && nums[i+1]== nums[i+2]){
                ans = solveRec(nums,i+3,n);
            }

            if(!ans && i+2 < n ){
                if(nums[i]+1 == nums[i+1] && nums[i+1]+1== nums[i+2]){
                    ans = solveRec(nums,i+3,n);
                }
            }
        }
        return ans;
    }
    public static boolean solveDP(int nums[],int n){

        boolean[] dp = new boolean[n + 1];
        dp[n] = true; // empty array is valid

        for (int i = n - 1; i >= 0; i--) {

            // Case 1: two equal
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                if (dp[i + 2]) {
                    dp[i] = true;
                    continue; // no need to check further
                }
            }

            // Case 2 & 3: three elements
            if (i + 2 < n) {

                // three equal
                if (nums[i] == nums[i + 1] && nums[i] == nums[i + 2]) {
                    if (dp[i + 3]) {
                        dp[i] = true;
                        continue;
                    }
                }

                // three consecutive
                if (nums[i + 1] == nums[i] + 1 && nums[i + 2] == nums[i] + 2) {
                    if (dp[i + 3]) {
                        dp[i] = true;
                    }
                }
            }
        }

        return dp[0];
    }

}
