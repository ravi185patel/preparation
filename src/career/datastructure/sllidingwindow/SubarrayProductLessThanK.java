package career.datastructure.sllidingwindow;

/*
https://leetcode.com/problems/subarray-product-less-than-k/description/

 */
public class SubarrayProductLessThanK {
    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10,5,2,6},100));
        System.out.println(numSubarrayProductLessThanK(new int[]{1,2,3},0));
    }
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int left=0;
        int product =1,count=0;
        for(int right=0;right<nums.length;right++){
            product*=nums[right];

            while(left <= right && product >= k){
                product/=nums[left];
                left++;
            }

            if(product < k){
                count+=(right - left + 1);
            }

        }
        return count;
    }
}
