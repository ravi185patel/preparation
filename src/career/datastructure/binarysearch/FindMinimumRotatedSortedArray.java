package career.datastructure.binarysearch;

import java.util.ArrayList;

public class FindMinimumRotatedSortedArray {
    public static void main(String[] args) {

    }
    public int findMin(int[] nums) {
        int start=0,end = nums.length-1;
        while(start < end){
            int mid = start + (end-start)/2;
            if(nums[mid] > nums[end]){
                start = mid + 1;
            }else{
                end = mid;
            }
        }

        return nums[end];
    }
    public int findMin(ArrayList<Integer> arr) {
        int start=0,end=arr.size()-1;
        int min = Integer.MAX_VALUE;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(arr.get(start) <= arr.get(end)){
                min = Math.min(min,arr.get(start));
                break;
            }
            if(arr.get(start) <= arr.get(mid)){
                min = Math.min(min,arr.get(start));
                start=mid+1;
            }else{
                min = Math.min(min,arr.get(mid));
                end = mid-1;
            }
        }
        return min;
    }
}
