package career.datastructure.arrays.easy;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithSumK {
    public static void main(String[] args) {
        int arr[] = {10, 5, 2, 7, 1, 9}, k = 15;
//        int arr[] = {-1, 2, 3}, k = -1;
        System.out.println(lenOfLongSubarr(arr,arr.length,k));
    }


    // Function for finding maximum and value pair
    public static int lenOfLongSubarr (int A[], int N, int K) { // only work for positive values
        //Complete the function

        int left =0,right =0,sum=A[0],maxLen=0;

        while(right < N){
            while(left <= right && sum > K){
                sum -= A[left];
                left++;
            }

            if(sum == K){
                maxLen = Math.max(maxLen,right-left+1);
            }

            right++;
            if(right < N){
                sum +=A[right];
            }
        }
        return maxLen;
    }
    public static int lenOfLongSubarrPosAndNeg (int nums[], int N, int k) { // work for both negative and positive values
        Map<Integer,Integer> prevSum = new HashMap<>();
        int sum=0;
        int window=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(sum == k){
                window = Math.max(window,i+1);
            }

            if(prevSum.containsKey(sum-k)){
                int length = i-prevSum.get(sum-k);
                window = Math.max(window,length);
            }

            if(!prevSum.containsKey(sum)){
                prevSum.put(sum,i);
            }
        }

        return window;
    }

}
