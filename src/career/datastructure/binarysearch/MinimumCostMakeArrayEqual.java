package career.datastructure.binarysearch;

import java.util.Arrays;

/*
https://www.youtube.com/watch?v=lGo6E8jiDB8&list=PLpIkg8OmuX-LkgtrEF7eyyYWJM3m5tVQY&index=16
You are given two 0-indexed arrays nums and cost consisting each of n positive integers.

You can do the following operation any number of times:

Increase or decrease any element of the array nums by 1.
The cost of doing one operation on the ith element is cost[i].

Return the minimum total cost such that all the elements of the array nums become equal.



Example 1:

Input: nums = [1,3,5,2], cost = [2,3,1,14]
Output: 8
Explanation: We can make all the elements equal to 2 in the following way:
- Increase the 0th element one time. The cost is 2.
- Decrease the 1st element one time. The cost is 3.
- Decrease the 2nd element three times. The cost is 1 + 1 + 1 = 3.
The total cost is 2 + 3 + 3 = 8.
It can be shown that we cannot make the array equal with a smaller cost.
Example 2:

Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
Output: 0
Explanation: All the elements are already equal, so no operations are needed.


Constraints:

n == nums.length == cost.length
1 <= n <= 105
1 <= nums[i], cost[i] <= 106
Test cases are generated in a way that the output doesn't exceed 253-1


 */
public class MinimumCostMakeArrayEqual {
    public static void main(String[] args) {
//        System.out.println(minCost(new int[]{1,3,5,2},new int[]{2,3,1,14}));
//        System.out.println(minCost(new int[]{2,2,2,2,2},new int[]{4,2,8,1,3}));
        System.out.println(minCost(new int[]{735103,366367,132236,133334,808160,113001,49051,735598,686615,665317,999793,426087,587000,649989,509946,743518},new int[]{724182,447415,723725,902336,600863,287644,13836,665183,448859,917248,397790,898215,790754,320604,468575,825614}));
    }
    public static long minCost(int[] nums, int[] cost) {
       /* int length = nums.length;

        long minOp=Long.MAX_VALUE;
        for(int i=0;i<length;i++){
            int element = nums[i];
            long totalCost = 0;
            for(int left=i-1;left>=0;left--){
                totalCost += ((long)Math.abs(nums[left]-element)*(long)cost[left]);
            }
            for(int right = i + 1;right<length;right++){
                totalCost += ((long)(Math.abs(nums[right]-element))*(long)cost[right]);
            }
            minOp = Math.min(minOp,totalCost);
        }
        return minOp;*/
        return minCostBs(nums,cost);
    }

    public static long minCostBs(int[] nums, int[] cost) {
        long minOp=Long.MAX_VALUE;

        int left = Integer.MAX_VALUE,right = Integer.MIN_VALUE;
        for(int i:nums){
            right = Math.max(right,i);
            left = Math.min(left,i);
        }
        while(left <= right){
            int mid = left + (right-left)/2;
            long totalCost1 =totalCostToConvertIntoElem(nums,cost,mid);
            long totalCost2 =totalCostToConvertIntoElem(nums,cost,mid+1);
            minOp = Math.min(totalCost1,totalCost2);
            if(totalCost2 > totalCost1){
                right = mid - 1;
            }else{
                left  = mid +1;
            }
        }
        return minOp == Long.MAX_VALUE ? 0:minOp;
    }
    public static long totalCostToConvertIntoElem(int[]nums,int []cost,int target){

        long totalCost = 0;
        for(int i=0;i<nums.length;i++){

            totalCost += ((long)(Math.abs(nums[i]-target))*(long)cost[i]);
        }
        return totalCost;
    }
}
