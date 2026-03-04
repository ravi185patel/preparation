package career.interview.priceline.bs;

public class SearchRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2},0));
        System.out.println(search(new int[]{4,5,6,7,0,1,2},3));
        System.out.println(search(new int[]{4,5,6,7,8,1,2,3},8));
        System.out.println(search(new int[]{1},0));
    }

    public static int search(int[] nums, int target) {
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
}
