package career.interview.priceline;

public class MaximumSumOfDistinctSubarraysWithLengthK {
    public static void main(String[] args) {
        System.out.println(maximumSubarraySum(new int[]{1,5,4,2,9,9,9},3));
        System.out.println(maximumSubarraySum(new int[]{4,4,4},3));
    }
    public static long maximumSubarraySum(int[] nums, int k) {
        return solveSw(nums,k);
    }

    public static long solveSw(int nums[],int k){
        long sum =0,max=0;
        int left=0;
        int freq[]=new int[100001];

        for(int right=0;right<nums.length;right++){
            freq[nums[right]]++;
            sum += nums[right];
            while(freq[nums[right]] > 1 || right-left+1 > k ){
                sum-=nums[left];
                freq[nums[left]]--;
                left++;
            }
            if(right-left +1 == k){
                max = Math.max(max,sum);
            }
        }

        return max;
    }
}
