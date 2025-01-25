package career.thirtydays.dp.matrix.mcm;

/*
https://leetcode.com/problems/burst-balloons/description/


Main  point
when you are at index 0 you need to calculate i-1 which is -1
so we will append 1 in array in beginning.

same way at index n-1 => calculate n+1-1 = n which is out of array
so append n at the end; why n instead of one
because you are calculating so at n there is no balloons so you need to multiple with 1

1] recursive
-> you have to cover i-1,i,i+1
-> call recursive by taking i to ind-1 and ind+1 to j
-> ex 1,2,3,4 -> 1 to 2 and 3 to 4
tc = exp

2] memo tc = O(N3)
3] dp tc = O(n3)


 */
public class BrustBalloons {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3,1,5,8}));
        System.out.println(maxCoins(new int[]{1,5}));
    }

    public static int maxCoins(int[] nums) {
//        return find(0,nums.length-1,nums);
        return findDp(nums);
    }

    private static  int find(int i,int j,int[]nums){
        if(i > j) return 0;
        int max = Integer.MIN_VALUE;
        for(int ind = i;ind <=j;ind++){
            int prev = i-1 < 0 ? 1:nums[i-1];
            int next = j+1 >= nums.length ? 1:nums[j+1];
            int cost =
                    nums[ind]*prev*next
                            + find(i,ind-1,nums)
                            + find(ind+1,j,nums);
            max = Math.max(max,cost);
        }
        return max;
    }

    private static  int findDp(int[]nums){
        // add 1 before and after nums
        int n = nums.length + 2;
        int[] newNums = new int[n];
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        newNums[0] = 1;
        newNums[n - 1] = 1;
        // dp[i][j] represents
        // maximum if we burst all nums[left]...nums[right], inclusive
        int[][] dp = new int[n][n];
        // do not include the first one and the last one
        // since they are both fake balloons added by ourselves and we can not burst
        // them
        for (int left = n - 2; left >= 1; left--) {
            for (int right = left; right <= n - 2; right++) {
                // find the last burst one in newNums[left]...newNums[right]
                for (int i = left; i <= right; i++) {
                    // newNums[i] is the last burst one
                    int gain = newNums[left - 1] * newNums[i] * newNums[right + 1];
                    // recursively call left side and right side
                    int remaining = dp[left][i - 1] + dp[i + 1][right];
                    // update
                    dp[left][right] = Math.max(remaining + gain, dp[left][right]);
                }
            }
        }
        // burst newNums[1]...newNums[n-2], excluding the first one and the last one
        return dp[1][n - 2];
    }
}
