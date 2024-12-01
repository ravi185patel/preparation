package career.datastructure.sllidingwindow;

public class CountSubarrayWithFixedBounds {
    public static void main(String[] args) {
//        int  nums[] = {1,3,5,2,7,5}, minK = 1, maxK = 5;
        int nums[] = {1,1,1,1}, minK = 1, maxK = 1;
        System.out.println(countSubarrays(nums,minK,maxK));
    }

    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int maxPosition  = -1;
        int minPosition = -1;
        int issuePosition =-1;
        long ans=0l;
        for(int i=0;i<nums.length;i++){
            if(nums[i] < minK || nums[i] > maxK){
                issuePosition=i;
            }

            if(nums[i] == minK){
                minPosition=i;
            }

            if(nums[i] == maxK){
                maxPosition = i;
            }

            long smaller = Math.min(minPosition,maxPosition);
            long temp = smaller - issuePosition;
            ans += (temp <= 0) ? 0: temp;
        }
        return ans;
    }
}
