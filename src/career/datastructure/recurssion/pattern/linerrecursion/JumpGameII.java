package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

/*
https://leetcode.com/problems/jump-game-ii/description/

 */
public class JumpGameII {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,3,1,1,4}));
        System.out.println(canJump(new int[]{2,3,0,1,4}));
        System.out.println(canJump(new int[]{1,2}));
        System.out.println(canJump(new int[]{2,0,0}));
        System.out.println(canJump(new int[]{0}));
    }
    public static int canJump(int[] nums) {
        int minJump=0;
//        minJump=canJumpIIRec(nums,0);


        int n = nums.length;
        int memo[]=new int[n+1];
        Arrays.fill(memo,-1);
//        minJump =  canJumpIIRecMemo(nums,0,memo);
//
//        minJump = canJumpIIRecDp(nums);
        minJump = canJumpIINormal(nums);

        return minJump == Integer.MAX_VALUE ? -1:minJump;
    }

    public static int canJumpIIRec(int[] nums,int index){
        if(index >= nums.length-1) return 0;
        int minJump=(int)1e9;
        for(int i=1;i <=nums.length-1 && i <= nums[index];i++){
            minJump = Math.min(minJump,1 + canJumpIIRec(nums,index+i));
        }
        return minJump;
    }

    public static int canJumpIIRecMemo(int[] nums,int index,int memo[]){
        if(index >= nums.length-1) return 0;
        if(memo[index] != -1){
            return memo[index];
        }
        int minJump=(int)1e9;
        for(int i=1;i <=nums.length-1 && i <= nums[index];i++){
            minJump = Math.min(minJump,1 + canJumpIIRecMemo(nums,index+i,memo));
        }
        return minJump;
    }

    public static int canJumpIIRecDp(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];

        // initialize dp with large value
        Arrays.fill(dp, (int)1e9);

        dp[0] = 0; // start index needs 0 jumps

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {

                if (j + nums[j] >= i) { // check from all previous position from i index -> 1,2,3 => from 3 check all previous 1,2 position
                    // inteview question why J + nums[J] >= I -> as if you wan to check that position reached from current position then currentPos + steps must be >= i if it small
//                    then it is not reached form current position.
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }

    public static int canJumpIINormal(int[] nums){
        /*
nums = [2, 3, 1, 1, 4]
index   0  1  2  3  4
        2, 4, 3, 4, 8

| Step | i | nums[i] | farthest (before → after) | i == currentEnd?  | jumps (after) | currentEnd (after) |
| ---- | - | ------- | ------------------------- | ----------------  | ------------- | ------------------ |
| Init | – | –       | 0 → 0                     | –                 | 0             | 0                  |
| 1    | 0 | 2       | 0 → **2**                 | ✅ Yes            | **1**         | **2**              |
| 2    | 1 | 3       | 2 → **4**                 | ❌ No             | 1             | 2                  |
| 3    | 2 | 1       | 4 → **4**                 | ✅ Yes            | **2**         | **4**              |


| Jump # | Jump Range (current window) | What we compute inside |
| ------ | --------------------------- | ---------------------- |
| Jump 1 | `[0]`                       | farthest = 2           |
| Jump 2 | `[1, 2]`                    | farthest = 4           |
| End    | reached index `4`           | stop                   |

         */
        int n = nums.length;
        int jumps =0,currentEnd = 0,farthest = 0;
        for (int i = 0; i < n-1; i++) { // explore each index
            farthest = Math.max(farthest,i + nums[i]); // prefix sum max ;
            System.out.println(i+" <> "+currentEnd+" <> "+farthest);
            if(i == currentEnd){ // reached end of current jump range;
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }
}
