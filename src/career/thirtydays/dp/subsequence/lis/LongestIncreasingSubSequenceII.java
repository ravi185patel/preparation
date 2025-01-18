package career.thirtydays.dp.subsequence.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/longest-increasing-subsequence-ii/description/
You are given an integer array nums and an integer k.

Find the longest subsequence of nums that meets the following requirements:

The subsequence is strictly increasing and
The difference between adjacent elements in the subsequence is at most k.
Return the length of the longest subsequence that meets the requirements.

1] recursion TLE
2] DP TLE
3] segment tree -> why ? how ?

 */
public class LongestIncreasingSubSequenceII {
    public static void main(String[] args) {
//        System.out.println(lengthOfLIS(new int[]{4,2,1,4,3,4,5,8,15},3));
//        System.out.println(lengthOfLIS(new int[]{7,4,5,1,8,12,4,7},5));
        System.out.println(lengthOfLIS(new int[]{1,5},1));
    }
    public static int lengthOfLIS(int[] nums, int k) {
        return lis(nums,k);
//        return lisGenSeq(nums);
//        return lisGenSeqBs(nums,k);
    }

    private static int lis(int[]nums,int k){

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]!=nums[j] && nums[i]-nums[j] > 0 && nums[i]-nums[j] <= k ) {
                    System.out.println(nums[i]+" "+nums[j]);
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0;
        for (int c: dp) {
            longest = Math.max(longest, c);
        }

        return longest;
    }

    private static int lisGenSeq(int[]nums){
        List<Integer> lst = new ArrayList<>();
        lst.add(nums[0]);

        for(int i=1;i<nums.length;i++){
            int num = nums[i];
            if(num > lst.get(lst.size()-1)){
                lst.add(num);
            }else{
                int j=0;
                while(num > lst.get(j)){
                    j+=1;
                }
                lst.set(j,num);
            }
        }

        return lst.size();
    }


    private static int lisGenSeqBs(int[]nums,int k){
        List<Integer> lst = new ArrayList<>();
        lst.add(nums[0]);

        for(int i=1;i<nums.length;i++){
            int num = nums[i];
            if(num > lst.get(lst.size()-1)){
                lst.add(num);
            }else{
                int j = binarySearch(lst, num);
                lst.set(j,num);
            }
        }

        return lst.size();
    }

    private static int binarySearch(List<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            mid = (left + right) / 2;
            if (sub.get(mid) == num) {
                return mid;
            }

            if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
