package career.datastructure.leetcode;

import java.util.Arrays;

public class LongestIncreasingSubSequenceII {
    public static void main(String[] args) {
//        int nums[] = {4,2,1,4,3,4,5,8,15}, k = 3; // ans 5
        int nums[] = {7,4,5,1,8,12,4,7}, k = 5; // ans 4
        int res = lengthOfLIS(nums,k);
        System.out.println(res);
    }

    public static int lengthOfLIS(int[] nums, int k) {
//        return dfs(nums,nums.length,0,-1,k);
        int n = nums.length;
        int memo[][]=new int[n][n];
        for(int i[]:memo){
            Arrays.fill(i,-1);
        }
        return dfs(nums,nums.length,0,-1,k,memo);
    }


    public static int dfs(int nums[],int n,int index,int prevIndex,int k){
         if(index == n){
             return 0;
         }

        int noTake = dfs(nums,n,index+1,index,k);
        int take = 0;
        if(prevIndex == -1 || ( nums[index] - nums[prevIndex] > 0 && nums[index] - nums[prevIndex] <= k)){
            take = 1 + dfs(nums,n,index+1,index,k);
        }

        return Math.max(noTake,take);

    }

    public static int dfs(int nums[],int n,int index,int prevIndex,int k,int memo[][]){
        if(index == n){
            return 0;
        }

        if(memo[index][prevIndex+1] != -1){
            return memo[index][prevIndex+1];
        }
        int noTake = dfs(nums,n,index+1,index,k,memo);
        int take = 0;
        if(prevIndex == -1 || ( nums[index] - nums[prevIndex] > 0 && nums[index] - nums[prevIndex] <= k)){
            take = 1 + dfs(nums,n,index+1,index,k,memo);
        }

        return memo[index][prevIndex+1] = Math.max(noTake,take);

    }
}
