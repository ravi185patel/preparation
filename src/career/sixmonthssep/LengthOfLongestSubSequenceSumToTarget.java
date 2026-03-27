package career.sixmonthssep;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LengthOfLongestSubSequenceSumToTarget {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubsequence(Arrays.asList(1,2,3,4,5),9));
        System.out.println(lengthOfLongestSubsequence(Arrays.asList(4,1,3,2,1,5),7));
        System.out.println(lengthOfLongestSubsequence(Arrays.asList(1,1,5,4,5),3));
        System.out.println(lengthOfLongestSubsequence(Arrays.asList(1000),1000));
    }

    public static int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int dp[][]=new int[nums.size()][target+1];
        for(int row[]:dp){
            Arrays.fill(row,Integer.MIN_VALUE);
        }
//        Collections.sort(nums);
//        int ans = recursion(0,nums,target);
        int ans = recursion(0,nums,target,dp);
        ans = recursionDp(0,nums,target);
        ans = recursionDpOpt(0,nums,target);
        ans = recursionDpOpt1(0,nums,target);
        return ans == Integer.MIN_VALUE ? -1 :ans;

    }

    public static int recursion(int index,List<Integer> nums,int target){
//        if(target < 0) return Integer.MIN_VALUE;
        if(target == 0) return 0;
        if(index == nums.size()) return Integer.MIN_VALUE;

        int noTake = recursion(index+1,nums,target);
        int take = Integer.MIN_VALUE;
        if(target >= nums.get(index)) {
            take = recursion(index + 1, nums, target - nums.get(index));
            if(take != Integer.MIN_VALUE){
                take +=1;
            }
        }
        return Math.max(noTake,take);
    }

    public static int recursion(int index,List<Integer> nums,int target,int dp[][]){
//        if(target < 0) return Integer.MIN_VALUE;
        if(target == 0) return 0;
        if(index == nums.size()) return -1;
        if(dp[index][target] != -1){
            return dp[index][target];
        }

        int noTake = recursion(index+1,nums,target,dp);
        int take = -1;
        if(target >= nums.get(index)) {
            take =  1 + recursion(index + 1, nums, target - nums.get(index),dp);
        }
        return dp[index][target] =Math.max(noTake,take);
    }

    public static int recursionDp1(int index,List<Integer> nums,int target){
        int dp[][]=new int[nums.size()][target+1];
        for(int row[]:dp){
            Arrays.fill(row,Integer.MIN_VALUE);
        }
        for(int i=0;i<nums.size();i++){
            dp[i][0]=nums.get(i) <= target ? 0: Integer.MIN_VALUE;
        }
        for(int i=1;i<nums.size();i++){
            for(int t=0;t<=nums.get(i-1) && t <= target;t++){
                int noTake = dp[i-1][t];
                int take = 1 + dp[i-1][nums.get(i-1)-t];
                dp[i][t]= Math.max(noTake,take);
            }
        }
        return dp[nums.size()-1][target];
    }

    public static int recursionDp(int index,List<Integer> nums,int target){
        int dp[][]=new int[nums.size()][target+1];
        for(int row[]:dp){
            Arrays.fill(row,Integer.MIN_VALUE);
        }
        for(int i=0;i<nums.size();i++){
            dp[i][0]=nums.get(i) <= target ? 0: Integer.MIN_VALUE;
        }
        for(int i=1;i<nums.size();i++){
            for(int t=1;t<=target;t++){
                int noTake = dp[i-1][t];
                int take = Integer.MIN_VALUE;
                if(t >= nums.get(i-1)){
                    take = dp[i-1][t-nums.get(i-1)];
                    if(take != Integer.MIN_VALUE){
                        take +=1;
                    }
                }
                dp[i][t]= Math.max(noTake,take);
            }
        }
        return dp[nums.size()-1][target];
    }


    public static int recursionDpOpt(int index,List<Integer> nums,int target){
        int dp[] = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= nums.size(); i++) {
            int curr[] = new int[target + 1];
            Arrays.fill(curr, Integer.MIN_VALUE);  // FIX
            curr[0] = 0;

            for (int t = 1; t <= target; t++) {
                int noTake = dp[t];
                int take = Integer.MIN_VALUE;

                if (t >= nums.get(i - 1)) {
                    take = dp[t - nums.get(i - 1)];
                    if (take != Integer.MIN_VALUE) {
                        take += 1;
                    }
                }

                curr[t] = Math.max(noTake, take);
            }
            dp = curr;
        }

        return dp[target];
    }

    public static int recursionDpOpt1(int index,List<Integer> nums,int target){
        int dp[] = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= nums.size(); i++) {
            for (int t = target; t >=nums.get(i-1); t--) { // why it is worked because you are not picked again same element
                if (dp[t - nums.get(i - 1)] != Integer.MIN_VALUE) {
                    dp[t] = Math.max(dp[t], dp[t - nums.get(i - 1)] + 1);
                }
            }
        }

        return dp[target];
    }

}
