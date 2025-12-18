package career.datastructure.sllidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class FindThePowerOfKSizeSubarraysI {
    public static void main(String[] args) {
        int res[]=resultsArray(new int[]{1,2,3,4,3,2,5},3);
        System.out.println(Arrays.toString(res));
        res=resultsArrayMono(new int[]{1,2,3,4,3,2,5},3);
        System.out.println(Arrays.toString(res));
    }
    public static int[] resultsArray(int[] nums, int k) {
        if(k==1) return nums;

        int length = nums.length;
        int [] res = new int[length - k + 1];
        Arrays.fill(res,-1);
        int consecCount=1;
        for(int index=0;index<length-1;index++){
            if(nums[index]+1 == nums[index+1]){
                consecCount++;
            }else{
                consecCount=1;
            }

            if(consecCount >= k){
                res[index-k+2] =  nums[index+1];
            }
        }

        return res;
    }

    public static int[] resultsArrayMono(int[] nums, int k) {
        if(k==1) return nums;

        Deque<Integer> deq = new LinkedList<>(); // Monotonic deque
        int length = nums.length,index=0;
        int [] result = new int[length - k + 1];
        Arrays.fill(result,-1);
        for (int j = 0; j < length; j++) {
            // If deque size equals k, remove the front element
            if (deq.size() == k) {
                deq.pollFirst();
            }

            // If deque is not empty and current element is not consecutive to the last element
            if (!deq.isEmpty() && deq.peekLast() != nums[j] - 1) {
                deq.clear();
            }

            // Add the current element to the deque
            deq.offerLast(nums[j]);

            // Once we process the first k elements
            if (j >= k - 1) {
                if (deq.size() == k) {
                    result[index++] = deq.peekLast(); // Last element is the max due to monotonic property
                } else {
                    result[index++] = -1; // Otherwise, add -1
                }
            }
        }

        return result;
    }
}
