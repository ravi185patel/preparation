package career.datastructure.arrays.medium;

import java.util.Arrays;

public class SortZeroOneAndTwoInArray {
    public static void main(String[] args) {
//        int nums[] = {2,0,2,1,1,0};
        int nums[] = {1,2,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        int start=0,end=nums.length-1,mid=start;
        while(mid <= end){
            if(nums[mid] == 0){
                int temp = nums[start];
                nums[start] = nums[mid];
                nums[mid] = temp;
                start++;
                mid++;
            }else if(nums[mid] == 2){
                int temp = nums[end];
                nums[end] = nums[mid];
                nums[mid] = temp;
                end--;
            }else{
                mid++;
            }
        }
    }
    public static void sortColorsCnt(int[] nums) {
        int z=0,o=0,s=0;
        for(int i:nums){
            if(i==0) z++;
            if(i==1) o++;
            if(i==2) s++;
        }

        for(int i=0;i<nums.length;i++){
            if(z!=0){
                nums[i]=0;
                z--;
            }else if(o != 0){
                nums[i]=1;
                o--;
            }else{
                nums[i]=2;
                s--;
            }
        }
    }
}
