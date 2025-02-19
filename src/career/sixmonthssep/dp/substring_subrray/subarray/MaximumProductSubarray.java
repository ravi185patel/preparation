package career.sixmonthssep.dp.substring_subrray.subarray;

/*
https://leetcode.com/problems/maximum-product-subarray/description/


Given an integer array nums, find a
subarray
 that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

subarry -> continuous
-> continuous -> two pointer -> left to right && right to left
--> or map to store value + two pointer.

 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
        System.out.println(maxProduct(new int[]{-2,0,-1}));
    }
    public static int maxProduct(int[] nums) {
        int max=Integer.MIN_VALUE,prefixProduct=1,suffixProduct=1;
        for(int i=0;i<nums.length;i++){
            if(prefixProduct == 0){
                prefixProduct=1;
            }
            if(suffixProduct == 0){
                suffixProduct=1;
            }
            prefixProduct *= nums[i];
            suffixProduct *= nums[nums.length-i-1];
            max = Math.max(max,Math.max(prefixProduct,suffixProduct));
        }

        return max;
    }
}
