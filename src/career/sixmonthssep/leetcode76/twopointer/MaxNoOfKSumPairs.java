package career.sixmonthssep.leetcode76.twopointer;

import java.util.Arrays;

/*
https://leetcode.com/problems/max-number-of-k-sum-pairs/description/?envType=study-plan-v2&envId=leetcode-75

 */
public class MaxNoOfKSumPairs {
    public static void main(String[] args) {
//        int nums[] = {1,2,3,4}, k = 5;
        int nums[] ={3,1,3,4,3}, k = 6;
        System.out.println(maxOperations(nums,k));
    }
    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int left=0,right=nums.length-1;
        int op=0;
        while(left < right){
            int sum=nums[left] + nums[right];
            if(sum == k){
                op++;
                left++;right--;
            }else if(sum > k){
                right--;
            }else{
                left++;
            }
        }
        return op;
    }
}
