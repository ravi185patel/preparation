package career.thirtydays.dp.subsequence.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }
    public static int lengthOfLIS(int[] nums) {
//        return lis(nums);
//        return lisGenSeq(nums);
        return lisGenSeqBs(nums);
    }

    private static int lis(int[]nums){

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
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


    private static int lisGenSeqBs(int[]nums){
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
