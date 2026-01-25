package career.datastructure.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution4 {
    public static void main(String[] args) {
                System.out.println(Arrays.toString(rotateElements(new int[]{5,4,3,2,1},3)));
        System.out.println(Arrays.toString(rotateElements(new int[]{1,-2,3,-4},3)));
        System.out.println(Arrays.toString(rotateElements(new int[]{-3,-2,7},1)));
        System.out.println(Arrays.toString(rotateElements(new int[]{5,4,-9,6},2)));
        System.out.println(Arrays.toString(rotateElements(new int[]{3,11},27812)));
        System.out.println(Arrays.toString(rotateElements(new int[]{5,4,-9,6},0)));
        System.out.println(Arrays.toString(rotateElements(new int[]{-1,-1,-1,-1},1)));
        System.out.println(Arrays.toString(rotateElements(new int[]{1,2,3,4,5},1)));
        long no = (long)Math.pow(2,50);
        long i=0;
        int k = 2;
        int n=4;
        while(i < no){
            if(Long.bitCount(i) == k){
                n--;
                if(n==0){
                    System.out.println(i);
                }
            }
            i++;
        }


    }
    public static int[] rotateElements(int[] nums, int k) {
        if(k == 0) return nums;
        int n = nums.length;
        int res[]=new int[n];
        int count=0;
        for(int i:nums){
            if(i >= 0){
                count++;
            }
        }
        if(count == 0){
            return nums;
        }

        int temp[]=new int[count];
        int index=0;
        for(int i:nums){
            if(i < 0) continue;
            temp[index++]=i;
        }

        k = k % count;

        reverse(temp, 0, k - 1);
        reverse(temp, k, count - 1);
        reverse(temp, 0, count - 1);

        index=0;
        for(int i=0;i<nums.length;i++){
            res[i]=nums[i] >= 0 ? temp[index++]:nums[i];
        }
        return res;
    }
    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
        }
    }
}