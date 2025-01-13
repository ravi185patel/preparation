package career.thirtydays.dp.onedimensional_fibonaccistyle;
/*
https://leetcode.com/problems/house-robber/?envType=study-plan-v2&envId=dynamic-programming
https://www.naukri.com/code360/problems/maximum-sum-of-non-adjacent-elements_843261

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
the only constraint stopping you from robbing
each of them is that adjacent houses have security systems connected
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
op = 4

Here you can't robbed adjacent house which means you can jump from 1 to 3 but not 2
so your result depend on previous result
res[n] = res[n-2] and res[n-2] = res[n-4];
p1 p  c+p1 c+p
1  2  3    4 5 6
 */
public class HouseRobber {
    public static void main(String[] args) {
//        int nums[]={1,2,3,1};
        int nums[]={2,7,9,3,1};
        System.out.println(rob(nums));
    }
    public static int rob(int[] nums) {
        int prev1=0,prev=0;
        for(int i=0;i<nums.length;i++){
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
