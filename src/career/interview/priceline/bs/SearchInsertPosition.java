package career.interview.priceline.bs;

public class SearchInsertPosition {
    public static void main(String[] args) {
        System.out.println(insertPosition(new int[]{1,2,3,4},3));
    }
    public static int insertPosition(int nums[],int elem){
        int left =0,right = nums.length-1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] <= elem){
                // < insert element in first place when target > nums[mid] // not duplicates
                // < insert at last when target > nums[mid] // used for duplicate elements
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
