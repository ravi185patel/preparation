package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

/*
https://leetcode.com/problems/jump-game/submissions/1489008057/

 */
public class JumpGame {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,3,1,1,4}));
        System.out.println(canJump(new int[]{3,2,1,0,4}));
        System.out.println(canJump(new int[]{1,2}));
        System.out.println(canJump(new int[]{2,0,0}));
        System.out.println(canJump(new int[]{0}));
    }
    public static boolean canJump(int[] nums) {
//        return canJumpRec(nums,0);

        int n = nums.length;
        boolean memo[]=new boolean[n+1];
        /*return canJumpRecMemo(nums,0,memo);*/

        return canJumpRecDp(nums,memo);
//        return canJumpRecNormal(nums,memo);
    }

    public static boolean canJumpRec(int[] nums,int index){
        if(index >= nums.length-1) return true;
        for(int i=1;i <=nums.length-1 && i <= nums[index];i++){
            if(canJumpRec(nums,index+i)){
                return true;
            }
        }
        return false;
    }

    public static boolean canJumpRecMemo(int[] nums,int index,boolean memo[]){
        if(index >= nums.length-1) return true;
        if(memo[index]){
            return memo[index];
        }
        for(int i=1;i <=nums.length-1 && i <= nums[index];i++){
            if(canJumpRecMemo(nums,index+i,memo)){
                return memo[index] =true;
            }
        }
        return memo[index] =false;
    }

    public static boolean canJumpRecDp(int[] nums,boolean memo[]){
        int n = nums.length;
        boolean[] dp = new boolean[n]; // it is 1d not 2d. because current step depends on previous steps.

        dp[0] = true; // start position

        /*for (int i = 0; i < n; i++) {

            // if current index is not reachable, skip it
            if (!dp[i]) continue;

            // jump forward: 1 → nums[i]
            for (int jump = 1; jump <= nums[i] && i + jump < n; jump++) {
                dp[i + jump] = true;
            }
        }*/

        for (int i = 1; i < n; i++) {

            for(int jump=0;jump < i;jump++){
                if(dp[jump] && jump + nums[jump] >= i){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }

    public static boolean canJumpRecNormal(int[] nums,boolean memo[]){
        int n = nums.length;
        int maxReachable=0;
        for (int i = 0; i < n; i++) {
            if(maxReachable < i) return false; // 3,2,1,0,4 -> 0 at position 4 => now if you check all previous one (index+value) => 3,3,3,4,9 -> no previous has same value.
            maxReachable = Math.max(maxReachable,nums[i]+i);
        }

        return true;
    }
}
