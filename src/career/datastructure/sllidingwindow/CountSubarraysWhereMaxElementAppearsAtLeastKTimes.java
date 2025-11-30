package career.datastructure.sllidingwindow;

import java.util.ArrayList;

/*
https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/

 */
public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {
    public static void main(String[] args) {

    }

    public long countSubarrays(int[] nums, int k) {
        int max=0;
        for(int i:nums){
            max = Math.max(max,i);
        }

        int left =0;
        int maxCount=0,count=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right] == max){
                maxCount++;
            }
            while(maxCount >= k){  // why because count can be more than k And Question: asked for at least K
                count += nums.length-right; // left contains element so sequence can be 1,3,3 -> 1,3,3 | 3,3
                if(nums[left] == max){
                    maxCount--;
                }
                left++;
            }
        }

        return count;
    }

    public static long countSubarraysMap(int[] nums, int k) {
        // Finding the maximum element in the array

        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxElement) {
                maxElement = nums[i];
            }
        }

        ArrayList<Integer> indexesOfMaxElements = new ArrayList<>();
        long ans = 0;

        // Iterating through the array

        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == maxElement) {
                indexesOfMaxElements.add(index);
            }

            int freq = indexesOfMaxElements.size();
            if (freq >= k) {
                ans += indexesOfMaxElements.get(freq - k) + 1;
            }
        }
        return ans;
    }

}
