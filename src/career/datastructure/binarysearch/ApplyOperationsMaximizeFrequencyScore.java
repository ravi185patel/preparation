package career.datastructure.binarysearch;

import java.util.Arrays;

/*
https://leetcode.com/problems/apply-operations-to-maximize-frequency-score/description/
 https://www.youtube.com/watch?v=Iaz-8ZQYUvs
 */
public class ApplyOperationsMaximizeFrequencyScore {
    public static void main(String[] args) {

        System.out.println(maxFrequencyScore(new int[]{1,2,6,4},3));
        System.out.println(maxFrequencyScore(new int[]{1,4,4,2,4},0));
    }
    public static int maxFrequencyScore(int[] nums, long k) {
        int length = nums.length;
        if(length == 0) return 0;
        Arrays.sort(nums);
        long prefix[]=new long[length];
        prefix[0] = nums[0];
        for(int i=1;i<length;i++){
            prefix[i] = nums[i] + prefix[i-1];
        }

        int maxFreq = length,minFreq=1;
        int ans=1;
        while(minFreq <= maxFreq){
            int mid = minFreq + (maxFreq-minFreq)/2;
            if(possible(prefix,nums,mid,length,k)){
                ans = mid;
                minFreq = mid + 1;
            }else{
                maxFreq = mid -1;
            }
        }
        return ans;
    }

    private static boolean possible(long prefix[],int nums[],int len,int n,long k){
        int i=0;
        int j= len-1;
        while(j < n){
            int targetIndex = (i+j)/2;
            long target = nums[targetIndex];

            long opLeft = 0;
            long opRight = 0;
            if(targetIndex == 0){
                opLeft = 0;
            }else {
                opLeft = Math.abs(((targetIndex - i) * target) - (prefix[targetIndex - 1]
                        - (i > 0 ?prefix[i - 1]:0)));
            }

            opRight = Math.abs(((j-targetIndex)*target)-(prefix[j]-prefix[targetIndex]));

            if(opLeft + opRight <= k){
                return true;
            }
            i++;
            j++;
        }
        return false;
    }
}
