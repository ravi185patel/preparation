package career.datastructure.sllidingwindow;

/*
https://leetcode.com/problems/longest-nice-subarray/description/

 */
public class LongestNiceSubarray {
    public static void main(String[] args) {

    }
    public int longestNiceSubarray(int[] nums) {
        int result=0;
        int n =nums.length;
        int mask = 0,left=0;
        for(int right=0;right<n;right++){
            while((mask & nums[right]) != 0){ // shrink window
                mask = mask ^ nums[left];
                left++;
            }

            mask = mask | nums[right];
            result = Math.max(result,right-left+1);
        }
        return result;
    }

}
