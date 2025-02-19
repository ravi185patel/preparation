package career.sixmonthssep.dp.subsequence.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/largest-divisible-subset/description/
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{1,2,3}));
        System.out.println(largestDivisibleSubset(new int[]{1,2,4,8}));
        System.out.println(largestDivisibleSubset(new int[]{3,4,16,8})); // bot x%y == 0 || y%x == 0 // 4,8,16
        System.out.println(largestDivisibleSubset(new int[]{5,9,18,54,108,540,90,180,360,720})); // need sorting //9,18,90,180,360,720
    }
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        return lis(nums);
//        return lisGenSeq(nums);
//        return lisGenSeqBs(nums);
    }

    private static List<Integer> lis(int[]nums){
        Arrays.sort(nums)    ; // why
        int parent[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            parent[i]=i;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int index = 1; index < nums.length; index++) {
            for (int prevInd = 0; prevInd < index; prevInd++) {
                if ((nums[index] % nums[prevInd] == 0
                        || nums[prevInd] % nums[index] == 0) && dp[prevInd] + 1 > dp[index]) {
                    dp[index] = dp[prevInd] + 1;
                    parent[index] = prevInd;
                }
            }
        }

        int ans = -1;
        int lastIndex =-1;

        for(int i=0; i<nums.length; i++){
            if(dp[i] > ans){
                ans = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(nums[lastIndex]);

        while(parent[lastIndex] != lastIndex){ // till not reach the initialization value
            lastIndex = parent[lastIndex];
            temp.add(0,nums[lastIndex]);
        }
        return temp;
    }
}
