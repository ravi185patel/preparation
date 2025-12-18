package career.datastructure.sllidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/count-the-number-of-good-subarrays/description/

 */
public class CountNumberGoodSubarrays {
    public static void main(String[] args) {

    }
    public long countGood(int[] nums, int k) {
        long result=0,pair=0;;
        int n = nums.length;
        int left=0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int right=0;right<n;right++){
            pair += map.getOrDefault(nums[right],0);
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            while(pair >= k){
                result += n-right;
                map.put(nums[left],map.getOrDefault(nums[left],0)-1);
                pair -= map.getOrDefault(nums[left],0);
                if(map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }
                left++;
            }
        }

        return result;
    }
}
