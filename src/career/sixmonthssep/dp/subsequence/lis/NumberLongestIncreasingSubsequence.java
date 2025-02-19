package career.sixmonthssep.dp.subsequence.lis;

import java.util.Arrays;

/*
https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/

1] Using subset of LIS and return size of subset.
2] Using recursion
    -> find maxLength
    -> return 1 when reach to end of array and maxLength == length of LIS
3] DP
  -> maintain two dp
  -> one dp -> to find longest
  -> cnt dp -> to store no of sequence at each index

  nums -> 1, 3, 2, 4
  dp   -> 1, 2, 2, 3
  cnt  -> 1, 1, 1, 2

 */
public class NumberLongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(findNumberOfLIS(new int[]{2,2,2,2,2}));
    }

    public static int findNumberOfLIS(int[] nums) {
        return findNoOfLIS(nums);
    }

    private static int findNoOfLIS(int [] nums){
        int n = nums.length;
        int dp[]=new int[n+1];
        int cnt[]=new int[n+1];

        Arrays.fill(dp,1);
        Arrays.fill(cnt,1);
        int maxi=0;
        for(int index=1;index<nums.length;index++){
            for(int prevInd =0;prevInd < index;prevInd++){
                if(nums[index] > nums[prevInd] && dp[prevInd]+1 > dp[index]){
                    dp[index] = dp[prevInd] + 1;
                    cnt[index] = cnt[prevInd];
                }else if(nums[index] > nums[prevInd] && dp[prevInd]+1 == dp[index]){
                    cnt[index] = cnt[prevInd]+cnt[index];
                }
            }
            maxi = Math.max(maxi,dp[index]);
        }

        int totalCnt=0;
        for(int i=0;i<nums.length;i++){
            if(maxi == dp[i]){
                totalCnt +=cnt[i];
            }
        }
        return totalCnt;
    }
}
