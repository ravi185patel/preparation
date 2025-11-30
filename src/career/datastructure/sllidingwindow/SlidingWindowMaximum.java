package career.datastructure.sllidingwindow;

import java.util.*;

/*
https://leetcode.com/problems/sliding-window-maximum/description/

 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)));
        System.out.println(Arrays.toString(maxSlidingWindowMS(new int[]{1,3,-1,-3,5,3,6,7},3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1},1)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((p1,p2) -> p2-p1);
//        Deque<Integer> queue = new ArrayDeque<>();
        int left=0;
        List<Integer> lst = new ArrayList<>();
        for(int right =0;right<nums.length;right++){
            pq.add(nums[right]);
            while((right-left+1) > k){
                pq.remove(nums[left]);
                left++;
            }
            if((right-left+1) == k){
                lst.add(pq.peek());
            }
        }

        int size= lst.size();
        int res[]=new int[size];
        for(int i=0;i<size;i++){
            res[i] = lst.get(i);
        }

        return res;
    }

    public static int[] maxSlidingWindowMS(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int left =0;
        int length = nums.length;
        int res[] =new int[length-k+1];
        int index=0;

        for(int i=0;i<length;i++){

            /*
            Imagine a queue where:
            Front = strongest / best candidate
            Back = weakest candidate
            If a new better candidate comes → push from back, but remove weaker ones
            If something becomes outdated → remove from front

            Left side  (Front)                   Right side (Rear)
            ↓                                   ↓
            ┌─────────────────────────────────────┐
            |  5  |  3  |  2  |  1  |  0  |  9  |
            └─────────────────────────────────────┘

             */
            while(!queue.isEmpty() && queue.getFirst() <= i-k){ // window shrink so remove first element of window
                queue.removeFirst();
            }

            while(!queue.isEmpty() && nums[i] >= nums[queue.getLast()]){ // Maintain monotonic decreasing order. Removes weaker max candidates because the current element is bigger and more recent.
                queue.removeLast();
            }

            queue.addLast(i);

            if(i >= k - 1){
                res[index]=nums[queue.getFirst()];
                index++;
            }
        }

        return res;
    }
}
