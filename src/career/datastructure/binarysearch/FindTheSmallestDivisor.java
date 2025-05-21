package career.datastructure.binarysearch;

public class FindTheSmallestDivisor {
    public static void main(String[] args) {

    }

    public int smallestDivisor(int[] nums, int threshold) {
        int ans=-1;
        int low = 1;
        int high = nums[0];
        for(int num:nums){
            high = Math.max(high,num);
        }

        while(low <= high){
            int mid = low + (high - low)/2;
            int result = divArr(nums,mid);

            if(result <= threshold){
                ans = mid;
                high = mid-1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    public int divArr(int arr[],int div){
        int sum=0;
        for(int i:arr){
            sum += Math.ceil((1.0*i)/div);
        }
        return sum;
    }
}
