package career.thirtydays.dp.onedimensional_fibonaccistyle;
/*
https://leetcode.com/problems/house-robber-ii/description/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [1,2,3]
Output: 3


when given there is cycle
run code from 0 to n-2 and 1 to n-1

 */
public class HouseRobberII {
    public static void main(String[] args) {
//        int nums[]={1,2,3,1};
        int nums[]={1};
//        int nums[]={2,3,2};
        int zeroPos = rob(nums,0,nums.length-2);
        int onePos = rob(nums,1,nums.length-1);
        System.out.println(Math.max(zeroPos,onePos));
    }
    public static int rob(int[] nums,int start,int end) {
        if(nums.length==1) return nums[0];
        int prev1=0,prev=0;
        for(int i=start;i<=end;i++){
            int robbed = nums[i];
            if(i > 0){
                robbed += prev1;
            }

            int notRobbed = prev;
            int curr = Math.max(robbed,notRobbed);
            prev1 = prev;
            prev = curr;
        }
        return prev;
    }
}
