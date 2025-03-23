package career.sixmonthssep.leetcode76.sw;

public class MaxAvgSubArrI {
    public static void main(String[] args) {
        int nums []= {1,12,-5,-6,50,3}, k = 4;
        System.out.println(findMaxAverage(nums,k));
    }
    public static double findMaxAverage(int[] nums, int k) {

        double sum=0,maxAvg=0;
        int start=0,end=0,len=nums.length;
        while(end < len){
            sum+=nums[end];
            if(end-start+1==k){
                maxAvg=Math.max(maxAvg,(double)(sum/(double)k));
                sum-=nums[start];
                start++;
            }
            end++;
        }

        return maxAvg;
    }
}
