package career.sixmonthssep.takeuforward;

/*
https://leetcode.com/problems/maximum-product-subarray/description/

1] O(N) approach

maintain maxProduct and minProduct at each index
when negative value encounter inter change maxProduct and minProduct.

ex (-2) * maxProduct(4) = -8
   (-2) * minProduct(-3) = 6

 */
public class MaximumProductSubarrayArray {
    public static void main(String[] args) {

    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            if (num < 0) {
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }

            maxEndingHere = Math.max(num, maxEndingHere * num);
            minEndingHere = Math.min(num, minEndingHere * num);

            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
    public int maxProduct1(int[] nums) {

        int length = nums.length;
        int maxProduct=Integer.MIN_VALUE;
        for(int i=0;i<length;i++){
            int product=nums[i];
            maxProduct = Math.max(maxProduct,product);
            for(int j=i+1;j<length;j++){
                product*= nums[j];
                maxProduct = Math.max(maxProduct,product);
            }

        }
        return maxProduct;
    }
}
