package career.interview.priceline.bs;

public class LoweUpperBound {
    public static void main(String[] args) {
        findBond(new int[]{1,2,4,4,4,6},4);
    }

    public static void findBond(int nums[],int target){
        System.out.println(lowerBound(nums,target));
        System.out.println(upperBound(nums,target));
    }

    public static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;  // right is exclusive

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;   // keep mid
            }
        }
        return left;
    }

    public static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
