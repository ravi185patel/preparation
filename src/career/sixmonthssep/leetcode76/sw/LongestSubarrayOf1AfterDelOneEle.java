package career.sixmonthssep.leetcode76.sw;

/*
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class LongestSubarrayOf1AfterDelOneEle {
    public static void main(String[] args) {
//        int nums[] = {0,1,1,1,0,1,1,0,1};
//        int nums[] = {1,1,1,0,1};
//        int nums[] = {0,0,0,0,1};
//        int nums[] = {1,0,0,0,0};
//        int nums[] = {1,0,0,1,0};
        int nums[] = {1,1,1,1,1};
        System.out.println(longestSubarray(nums));
    }
    public static int longestSubarray(int[] nums) {
        int window=0,start=0,end=0,len=nums.length,flip=0;

        for(end =0;end<len;end++){
            if(nums[end] ==0){
                flip++;
            }
            while(start < end && flip > 1){
                if(nums[start]==0){
                    flip--;
                }
                start++;
            }
            window = Math.max(window, end - start + 1);
        }
        return window==0 ? 0:window-1;
    }
}
