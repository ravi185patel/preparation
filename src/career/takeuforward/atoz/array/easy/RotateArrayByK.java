package career.takeuforward.atoz.array.easy;

import java.util.Arrays;

public class RotateArrayByK {
    public static void main(String[] args) {
        int nums[] ={1,2,3,4,5,6},k=11;
        rotateLeft(nums,k);
        System.out.println(Arrays.toString(nums));

        int nums1[] ={1,2,3,4,5,6};
        rotateRight(nums1,k);
        System.out.println(Arrays.toString(nums1));
    }
    public static void rotateRight(int[] nums, int k) {
        int length = nums.length;
        int noRotate = length < k ? k%length : k;
//        System.out.println(noRotate);
//        helper(nums,noRotate);
        helperOpt(nums,noRotate);
    }

    public static void helper(int[] nums,int k){
        for(int i=0;i<k;i++){
            int temp = nums[nums.length-1];
            for(int j=nums.length-1;j>=1;j--){
                nums[j]=nums[j-1];
            }
            nums[0] = temp;
        }
    }

    public static void helperOpt(int[] nums,int k){
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public static void rotateLeft(int[] nums, int k) {
        int length = nums.length;
        int noRotate = length < k ? k%length : k;
//        System.out.println(noRotate);
//        helper(nums,noRotate);
        helperOptRight(nums,noRotate);
    }
    public static void helperOptRight(int[] nums,int k){
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
        reverse(nums,0,nums.length-1);
    }



    public static void reverse(int[] nums,int start,int end){
        while(start <= end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;end--;
        }
    }
}
