package career.datastructure.sllidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/description/

 */
public class LengthLongestSubarrayWithMostKFrequency {
    public static void main(String[] args) {
        System.out.println(maxSubarrayLength(new int[]{1,2,3,1,2,3,1,2},2));
        System.out.println(maxSubarrayLength(new int[]{1,2,1,2,1,2,1,2},1));
        System.out.println(maxSubarrayLength(new int[]{5,5,5,5,5,5,5},4));
        System.out.println(maxSubarrayLength(new int[]{1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000,1000000000},5));
    }
    public static int maxSubarrayLength(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        int j=0,longest=0;
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            while(map.getOrDefault(nums[i],0) > k){
                map.put(nums[j],map.getOrDefault(nums[j],0)-1);
                j++;
            }
//            if(isValid(map,k)){
                longest = Math.max(longest,i-j+1);
//            }
        }
        return longest;
    }

//    public static boolean isValid(Map<Integer,Integer> map,int k){
//        for(Map.Entry<Integer,Integer> entry:map){
//            if(entry.getValue() > k){
//
//            }
//        }
//    }
}
