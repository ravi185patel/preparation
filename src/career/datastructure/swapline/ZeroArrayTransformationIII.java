package career.datastructure.swapline;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/zero-array-transformation-iii/description/?envType=daily-question&envId=2025-05-22

1]brute force approach
2] recursive approach
3] dp
4] Priority Queue + line sweep algorithm.

 */
public class ZeroArrayTransformationIII {
    public static void main(String[] args) {
        int nums[] = {1,1,1,1}, queries[][] = {{1,3},{0,2},{1,3},{1,2}};
        System.out.println(maxRemoval(nums,queries));
    }

    public static int maxRemoval(int[] nums, int[][] queries) {
//        return helper(nums, queries, 0, 0);
        return helperOp(nums, queries);
    }

    private static int helper(int[] nums, int[][] queries, int index, int removed) {
         return -1;
    }

    private static int helperOp(int[] nums, int[][] queries){
        int[]line = new int[nums.length + 1];
        Arrays.sort(queries,(x,y) -> x[0]-y[0]);
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[1]-x[1]);

        int preSum = 0;
        int ans =0;
        int index =0 ;
        for(int i=0;i<nums.length;i++){
            while(index < queries.length && i >= queries[index][0]){
                maxHeap.add(queries[index++]);
            }

            while(!maxHeap.isEmpty() && nums[i] > preSum + line[i]){
                int[]range = maxHeap.poll();

                if(range[1] < i){
                    return -1;
                }
                line[i]++;
                line[range[1]+1]--;
            }

            if(maxHeap.isEmpty() && nums[i] > preSum + line[i]){
                return -1;
            }

            preSum += line[i];
        }

        return maxHeap.size();
    }
}
