package career.datastructure.sllidingwindow;

/*
https://leetcode.com/problems/max-consecutive-ones/description/
 */
public class MaxConsecutiveOnesI {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}));
        System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}));
    }
    public static int longestOnes(int[] nums) {
        int ones=0,longest=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right] == 1){
                ones++;
            }else{
                ones=0;
            }
            longest = Math.max(longest,ones);
        }
        return longest;
    }

}
