package career.sixmonthssep.leetcode76.sw;

/*
link : https://leetcode.com/problems/max-consecutive-ones-iii/description/?envType=study-plan-v2&envId=leetcode-75;
 */
public class MaxConsecuritveOnesIII {
    public static void main(String[] args) {
//        int nums[] = {1,1,1,0,0,0,1,1,1,1,0}, k = 2;
//        int nums[] = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, k = 3;
        int nums[] = {0,0,0,0}, k = 0;
        System.out.println(longestOnes(nums,k));
    }
    public static int longestOnes(int[] nums, int k) {
        int window=0,start=0,end=0,len=nums.length,flip=0;

        for(end =0;end<len;end++){
            if(nums[end] ==0){
                flip++;
            }
            while(start < end && flip > k){
                if(nums[start]==0){
                    flip--;
                }
                start++;
            }
            if(flip <= k) {
                window = Math.max(window, end - start + 1);
            }
        }
        return window;
    }
}
