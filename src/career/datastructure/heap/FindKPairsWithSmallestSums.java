package career.datastructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.

Define a pair (u, v) which consists of one element from the first array and one element from the second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.



Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]


Constraints:

1 <= nums1.length, nums2.length <= 105
-109 <= nums1[i], nums2[i] <= 109
nums1 and nums2 both are sorted in non-decreasing order.
1 <= k <= 104
k <= nums1.length * nums2.length
 */
public class FindKPairsWithSmallestSums {
    public static void main(String[] args) {

    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[0]-b[0]);

        for(int i = 0; i < Math.min(nums1.length, k); i++)
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});

        while(!pq.isEmpty() && res.size() < k) {
            int [] cur = pq.poll();
            int i = cur[1], j = cur[2];

            res.add(Arrays.asList(nums1[i], nums2[j]));

            if(j + 1 < nums2.length)
                pq.offer(new int[]{nums1[i] + nums2[j+1], i, j+1});
        }

        return res;
    }
    public List<List<Integer>> kSmallestPairsPQ(int[] nums1, int[] nums2, int k) { // TLE
        PriorityQueue<int[]> pq = new PriorityQueue<>((i,j) -> j[2]-i[2]);
        for(int i:nums1){
            for(int j:nums2){
                pq.add(new int[]{i,j,i+j});
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while(!pq.isEmpty()){
            int[] point=pq.poll();
            res.add(0,Arrays.asList(point[0],point[1]));
        }

        return res;
    }
}
