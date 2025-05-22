package career.datastructure.swapline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/zero-array-transformation-iv/description/
 */
public class ZeroArrayTransformationIV {
    public static void main(String[] args) {
    }

    public int minZeroArray1(int[] nums, int[][] queries) {
        if(Arrays.stream(nums).allMatch(x -> x == 0)) return 0 ;
        int n = nums.length ;
        int q = queries.length ;
        int l = 0 ;
        int r = q-1 ;
        int interRes = -1 ;
        while(l <= r){
            int mid = l + (r-l)/2 ;
            if(isPossible(nums , queries , mid)){
                interRes = mid+1 ;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return interRes ;
    }
    public boolean isPossible(int[] nums ,int[][] queries , int k){
        for(int j = 0 ; j < nums.length ; j++){
            if(nums[j] == 0) continue ;
            List<Integer> arr = new ArrayList<>();
            for(int i = 0 ; i <= k ; i++){
                int L = queries[i][0];
                int R = queries[i][1];
                int val = queries[i][2];
                if(j >= L && j <= R){
                    arr.add(val);
                }
            }
            if(!targetSum(arr , nums[j])){
                return false ;
            }
        }
        return true ;
    }
    public boolean targetSum(List<Integer> nums , int target){
        //We'll use tabulation for target SUm
        int n = nums.size();
        boolean[][] dp = new boolean[n+1][target+1];
        for(int i = 0 ; i <= n ; i++){
            dp[i][0] = true ;//bae case as sum of 0 is always possible
        }
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j <= target ; j++){
                if(j < nums.get(i-1)){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums.get(i-1)];
                }
            }
        }
        return dp[n][target];
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int p = queries.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 0) {
                continue;
            }
            int[] dp = new int[num+1];
            dp[num] = 1;
            boolean found = false;
            for (int j = 0; j < p; j++) {
                int l = queries[j][0];
                int r = queries[j][1];
                int v = queries[j][2];
                if (l <= i && i <= r) {
                    for (int k = v; k <= num; k++) {
                        if (dp[k] == 1) {
                            dp[k-v] = 1;
                        }
                    }
                    if (dp[0] == 1) {
                        max = Math.max(max, j + 1);
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                return -1;
            }
        }
        return max;
    }
}
