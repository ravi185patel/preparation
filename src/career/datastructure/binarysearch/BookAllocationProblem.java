package career.datastructure.binarysearch;

/*
https://takeuforward.org/plus/dsa-concept-revision/day-6/book-allocation-problem
https://leetcode.com/problems/split-array-largest-sum/description/


Allocate the books to m students in such a way
that the maximum number of pages assigned to a student is minimized.


no partial allotment
either full or single


 */
public class BookAllocationProblem {
    public static void main(String[] args) {

    }

    public int findPages(int[] nums, int m) {
        int low =Integer.MIN_VALUE,high =0;
        for(int num: nums){
            low = Math.min(low,num);
            high += num;
        }

        while(low < high){
            int mid = low + (high - low)/2;
            if(canMake(nums,m,mid)){
                high = mid;
            }else{
                low = mid -1;
            }
        }
        return low;
    }

    public static boolean canMake(int nums[],int m,int allocation){
        int count=0;
        for(int num:nums){
            if(num <= allocation){
                count++;
            }
            if(count == m) return true;
        }
        return false;
    }
}
