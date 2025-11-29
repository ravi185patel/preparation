package career.datastructure.sllidingwindow;

/*
https://leetcode.com/problems/maximum-average-subarray-i/

 */
public class MaximumAverageSubarrayI {
    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3},4));
    }
    public static double findMaxAverage(int[] nums, int k) {
        double sum =0 ,ans= Integer.MIN_VALUE;
        int left=0;
        for(int right=0;right<=nums.length-1;right++){
            sum+=nums[right];
            if(right-left+1 ==k){
                ans = Math.max(ans,sum/(long)k);
                sum-=nums[left];
                left++;
            }
        }
        return ans;
    }
}
