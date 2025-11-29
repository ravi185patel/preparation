package career.datastructure.sllidingwindow;

import java.util.Arrays;

/*
https://leetcode.com/problems/k-radius-subarray-averages/description/

 */
public class KRadiusSubarrayAvg {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getAverages(new int[]{7,4,3,9,1,8,5,2,6},3)));
    }
    public static int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int windowSize = 2 * k + 1;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);

        if (n < windowSize) {
            return ans;
        }

        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for (int i = k; i + k < n; ++i) {
            ans[i] = (int) ((prefixSum[i + k + 1] - prefixSum[i - k]) / windowSize);
        }

        return ans;
    }

    public static int[] getAverages1(int[] nums, int k) {
        int n = nums.length;
        int res[]= new int[n];
        Arrays.fill(res,-1);
        int left = 0,right=2*k,j=k;
        if(right+1 > n) return res;
        long sum=0;
        for(int i=left;i<=right;i++){
            sum+=nums[i];
        }

        int count = 2*k + 1;
        res[j] =(int) (sum/count);
        j++;
        right++;

        while(right < nums.length){
            int leftNum = nums[left] ;
            int rightNum = nums[right];

            sum = sum + rightNum - leftNum;

            res[j] = (int)(sum/count);
            right++;
            left++;
            j++;
        }
        return res;
    }
}
