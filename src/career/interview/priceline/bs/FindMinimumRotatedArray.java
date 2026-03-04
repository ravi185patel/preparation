package career.interview.priceline.bs;

public class FindMinimumRotatedArray {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
    }
    public static int findMin(int[] nums) {
        int start=0, end = nums.length-1;
        while(start<end){
            int mid = start + (end - start)/2;
            if(nums[mid] > nums[end]){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return nums[end];
    }
}
