package career.datastructure.sllidingwindow;

/*
https://leetcode.com/problems/count-subarrays-with-score-less-than-k/description/

 */
public class CountSubarraysWithScoreLessThanK {
    public static void main(String[] args) {
        System.out.println(countSubarraysBf(new int[]{2,1,4,3,5},10));
        System.out.println(countSubarraysBf(new int[]{1,1,1},5));
        System.out.println(countSubarraysSw(new int[]{2,1,4,3,5},10));
        System.out.println(countSubarraysSw(new int[]{1,1,1},5));
    }
    public static long countSubarraysBf(int[] nums, long k) {
        long res =0;
        long sum=0;
        int n = nums.length,count=0;
        for(int i=0;i<n;i++){
            sum=0;
            for(int j=i;j<n;j++){
                sum += nums[j];
//                    System.out.println(sum+" "+(j-i+1));
                if(sum *(j-i+1) < k ){
                    count++;
                }
            }
        }
        return count;
    }

    public static long countSubarraysSw(int[] nums, long k) {
        long res =0;
        long sum=0;
        int n = nums.length,count=0;
        int left=0;
        for(int right=0;right<n;right++){
            sum+=nums[right];
            while(sum * (right-left+1) >= k){
                sum-=nums[left];
                left++;
            }
            if(sum * (right-left+1) < k){
                int len = right-left+1;
                count+=len;
            }
        }
        return count;
    }
}
