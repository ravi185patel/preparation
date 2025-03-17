package career.sixmonthssep.dp.onedimensional_fibonaccistyle;
/*
https://leetcode.com/problems/house-robber-iv/description/?envType=daily-question&envId=2025-03-15

There are several consecutive houses along a street, each of which has some money inside.
There is also a robber, who wants to steal money from the homes,
but he refuses to steal from adjacent homes.

The capability of the robber is the maximum amount of money he steals from one house of all
the houses he robbed.

You are given an integer array nums representing how much money is stashed in each house.
More formally, the ith house from the left has nums[i] dollars.

You are also given an integer k, representing the minimum number of houses the robber will steal from.
 It is always possible to steal at least k houses.

Return the minimum capability of the robber out of all the possible ways to steal at least k houses.

Input: nums = [2,3,5,9], k = 2
Output: 5
Explanation:
There are three ways to rob at least 2 houses:
- Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
- Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
- Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
Therefore, we return min(5, 9, 9) = 5.
Example 2:

Input: nums = [2,7,9,3,1], k = 2
Output: 2
Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is
to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.

 */

import java.util.Arrays;
import java.util.OptionalInt;

public class HouseRobberIV {
    public static void main(String[] args) {
//        int  nums[] = {2,3,5,9}, k = 2;
//        int  nums[] = {2,7,9,3,1}, k = 2;
        int  nums[] = {7,3,9,5}, k = 2;
        System.out.println(minCapability(nums,k));
    }
    public static int minCapability(int[] nums, int K) {
        // return rec(0,nums,K);
        int n = nums.length;
//        int memo[][]=new int[n][K+1];
//        for(int i[]:memo){
//            Arrays.fill(i,-1);
//        }
//        return rec(0,nums,K,memo);


        return rec(nums,K);
    }

    public static int rec(int index,int[]nums,int K,int memo[][]){

        if(K==0){
            return 0;
        }

        if(index >= nums.length){
            return Integer.MAX_VALUE;
        }

        if(memo[index][K] != -1){
            return memo[index][K];
        }

        int noTaken = rec(index+1,nums,K,memo);
        int taken = 0;
        if(index < nums.length){
            taken = Math.max(nums[index],rec(index+2,nums,K-1,memo));
        }
        return memo[index][K]=Math.min(noTaken,taken);
    }

    public static int rec(int index,int[]nums,int K){

        if(K==0){
            return 0;
        }

        if(index >= nums.length){
            return Integer.MAX_VALUE;
        }

        int noTaken = rec(index+1,nums,K);
        int taken = 0;
        if(index < nums.length){
            taken = Math.max(nums[index],rec(index+2,nums,K-1));
        }
        return Math.min(noTaken,taken);
    }

    static boolean isPossible(int[]nums,int mid,int k){
        int house=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] <= mid){
                house++;
                i++;
            }
        }

        return house >=k;
    }
    public static int rec(int[]nums,int k){
        int n = nums.length;
        int left = Arrays.stream(nums).min().getAsInt();
        int right = Arrays.stream(nums).max().getAsInt();

        int result = right;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(isPossible(nums, mid, k)){
                result = mid;
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }

        return result;
    }
}
