package career.datastructure.sllidingwindow;

/*
https://leetcode.com/problems/max-consecutive-ones-iii/description/
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
        System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1},3));
    }
    public static int longestOnes(int[] nums, int k) {
        int zeros=0,longest=0;
        int left=0,flip=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right] == 0){
                zeros++;
            }
            while(zeros > k ){
                if(nums[left] == 0){
                    zeros--;
                }
                left++;
            }
            longest = Math.max(longest,right-left + 1);
        }
        return longest;
    }

}
