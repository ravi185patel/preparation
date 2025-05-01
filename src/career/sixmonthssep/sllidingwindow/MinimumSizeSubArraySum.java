package career.sixmonthssep.sllidingwindow;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-size-subarray-sum/description/
window size is dynamic as mention minimum whose sum >= target
sum >= target condition when we need to shrink
1] O(N) -> SW
2] O(NlogN) -> prefix and BS
 */
public class MinimumSizeSubArraySum {
    public static void main(String[] args) {
        int target = 7, nums[] = {2,3,1,2,4,3};
//        int target = 4, nums[] = {1,4,4};
//        int target = 10, nums[] = {1,4,4};
//        int target = -10, nums[] = {1,4,4};
//        int target = 11, nums[] = {1,2,3,4,5};
        System.out.println(minSubArrayLen(target,nums));
        System.out.println(minSubArrayLenPrefixSumAndBS(target,nums));
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int start=0,end=0,length=nums.length;
        int minLength = Integer.MAX_VALUE;
        int sum=0;
        while(end < length){
            sum+=nums[end];
            while(sum >= target && start <= end){
                minLength = Math.min(minLength,end-start+1);
                sum-=nums[start];
                start++;
            }
            end++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    public static int minSubArrayLenPrefixSumAndBS(int target, int[] nums) {
        int start=0,end=0,length=nums.length;
        if(length == 0) return 0;

        int prefixSum[]=new int[length+1];
        prefixSum[0] = 0;
        for(int i=1;i<=length;i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i-1];
        }
        int minLength = Integer.MAX_VALUE;
        System.out.println(Arrays.toString(prefixSum));
        for(int i=1;i<=length;i++){
            int left = 0;
            int right = i-1;
            while(left <= right){
                int mid = left + (right-left)/2;
                if(prefixSum[i] - prefixSum[mid] >= target){
                    minLength = Math.min(minLength,i-mid);
                    left = mid+1; // shrink window to get minimum window whose sum >= target
                }else{
                    right = mid-1;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? 0: minLength;
    }
}
