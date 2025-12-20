package career.datastructure.hasing;

import java.util.Arrays;

/*
https://leetcode.com/problems/number-of-good-pairs/
 */
public class NumberGoodPairs {
    public static void main(String[] args) {

    }
    public int numIdenticalPairs(int[] nums) {
        int freq[]=new int[101]   ;
        Arrays.fill(freq,0);
        int count=0;
        for(int i=0;i<nums.length;i++){
            count+=freq[nums[i]];
            freq[nums[i]]++;
        }
        return count;
    }
}
