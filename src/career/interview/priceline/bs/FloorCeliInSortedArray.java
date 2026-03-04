package career.interview.priceline.bs;

import java.util.Arrays;

public class FloorCeliInSortedArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findFloorCeil(new int[]{5,7,7,8,8,10},8)));
        System.out.println(Arrays.toString(findFloorCeil(new int[]{5,7,7,8,8,10},6)));
        System.out.println(Arrays.toString(findFloorCeil(new int[]{},0)));
    }
    public int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);

        // if target not present
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        }

        int last = upperBound(nums, target) - 1;

        return new int[]{first, last};
    }

    // first index where value >= target
    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    // first index where value > target
    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
    public static int[] findFloorCeil(int nums[],int target){
        int start=0,end=nums.length-1;
        int mid = -1;
        while(start<=end){
            mid = start + (end-start)/2;
            if(nums[mid] == target){
                break;
            }else if(nums[mid] > target){
                end--;
            }else{
                start++;
            }
        }

        int endPoint=-1,startPoint=-1;
        if(mid != -1){
            for(int i=mid;i<nums.length;i++){
                if(target == nums[i]){
                    endPoint=i;
                }
            }
            for(int i=mid;i>=0;i--){
                if(target == nums[i]){
                    startPoint=i;
                }
            }
        }
        return new int[]{startPoint,endPoint};
    }
}
