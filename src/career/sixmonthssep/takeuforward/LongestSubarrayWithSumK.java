package career.sixmonthssep.takeuforward;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithSumK {
    public static void main(String[] args) {

    }

    public static int longestSubarray(int[] nums, int k) {
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
