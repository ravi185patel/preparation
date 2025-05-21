package career.datastructure.binarysearch;

/*
https://leetcode.com/problems/search-in-rotated-sorted-array/description/

https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates.
Would this affect the runtime complexity? How and why?

 */
public class SearchInRotatedSubArray {
    public static void main(String[] args) {
//        int  arr[] = {4,5,6,7,0,1,2,3}, k = 2;
//        int  arr[] = {38,53,53,53,54,-92,-92,-92,-75,-70,-59,-59,-59,-51,-44,-13,-13,22,22,22}, k = -13;
        int  arr[] = {3,1,2,3,3,3,3}, k = 1;
        System.out.println(findElement(arr,k)); // won't work with duplicate elements in array
        System.out.println(findElementII(arr,k));
    }

    public static int findElement(int nums[],int target){
        int start = 0, end = nums.length-1;
        while(start <= end){
            int mid = start + (end-start)/2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[start] <= nums[mid]){
                if(nums[start] <= target && target <= nums[mid]){
                    end = mid-1;
                }else{
                    start = mid + 1;
                }
            }else{
                if(nums[mid] <= target && target <= nums[end]){
                    start = mid + 1;
                }else{
                    end = mid-1;
                }
            }
        }
        return -1;
    }

    public static int findElementII(int arr[],int k){
        int start= 0,end = arr.length-1;

        while(start <= end){
            int mid = start + (end-start)/2;
            if(arr[mid] == k){
                return mid;
            }
            if(arr[start] == arr[mid] && arr[mid] == arr[end]){
                start++;
                end--;
                continue;
            }else if(arr[start]<= arr[mid]){
                if(arr[start] <= k && k<=arr[mid] ){
                    end = mid-1;
                }else{
                    start = mid+1;
                }
            }else{
                if(arr[mid] <= k && k <= arr[end]){
                    start = mid +1;
                }else{
                    end = mid-1;
                }
            }
        }
        return -1;
    }
}
