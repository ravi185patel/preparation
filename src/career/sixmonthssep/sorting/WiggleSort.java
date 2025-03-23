package career.sixmonthssep.sorting;

import java.util.Arrays;

/*
https://leetcode.com/problems/wiggle-sort/description/
 */
public class WiggleSort {
    public static void main(String[] args) {
        int nums[]={3,5,2,1,6,4};
        for(int i=0;i<nums.length-1;i++){
            if((i%2==0 && nums[i] > nums[i+1])
                || (i%2==1 && nums[i] < nums[i+1])){
                swap(nums,i,i+1);
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int nums[],int i,int j){
        int temp = nums[i];
        nums[i]= nums[j];
        nums[j]= temp;
    }
}
