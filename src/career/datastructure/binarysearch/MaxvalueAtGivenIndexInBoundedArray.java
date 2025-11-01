package career.datastructure.binarysearch;

/*
https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/description/
You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.



Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3


Constraints:

1 <= n <= maxSum <= 109
0 <= index < n
 */
public class MaxvalueAtGivenIndexInBoundedArray {
    public static void main(String[] args) {
        System.out.println(maxValue(4,0,4));
        System.out.println(maxValue(4,2,6));
        System.out.println(maxValue(6,1,10));
    }
    public static int maxValue(int n, int index, int maxSum) {
        int ans =-1;
        /*for(int i=1;i<=maxSum;i++){
            if(isPossibleToAchive(n,index,i,maxSum)){
                ans = i;
            }
        }*/
        int left =1,right = maxSum;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(isPossibleToAchiveOp(n,index,mid,maxSum)){
                ans = mid;
                left = mid + 1;
            }else{
                right = mid -1;
            }
        }
        return ans;
    }

    public static boolean isPossibleToAchiveOp(int n,int index,int value,int maxSum){
        long count = 0;

        // On index's left:
        // If value > index, there are index + 1 numbers in the arithmetic sequence:
        // [value - index, ..., value - 1, value].
        // Otherwise, there are value numbers in the arithmetic sequence:
        // [1, 2, ..., value - 1, value], plus a sequence of length (index - value + 1) of 1s.
        if (value > index) {
            count += (long)(value + value - index) * (index + 1) / 2;
        } else {
            count += (long)(value + 1) * value / 2 + index - value + 1;
        };
        // On index's right:
        // If value >= n - index, there are n - index numbers in the arithmetic sequence:
        // [value, value - 1, ..., value - n + 1 + index].
        // Otherwise, there are value numbers in the arithmetic sequence:
        // [value, value - 1, ..., 1], plus a sequence of length (n - index - value) of 1s.
        if (value >= n - index) {
            count += (long)(value + value - n + 1 + index) * (n - index) / 2;
        } else {
            count += (long)(value + 1) * value / 2 + n - index - value;
        }

        return count - value <= maxSum;
    }
    public static boolean isPossibleToAchive(int n,int index,int mid,int maxSum){
        int element = mid-1;
        int totalSum=mid;
        for(int i=index-1;i>=0;i--){
            totalSum+=element >= 0 ? element : 1;
            element--;
        }
        element= mid-1;
        for(int i=index+1;i<=n;i++){
            totalSum+=element >= 0 ? element:1;
            element--;
        }
        return totalSum <= maxSum;
    }
}
