package career.datastructure.binarysearch;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/description/?envType=problem-list-v2&envId=binary-search
Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.


Example 1:

Input: nums1 = [2,5], nums2 = [3,4], k = 2
Output: 8
Explanation: The 2 smallest products are:
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
The 2nd smallest product is 8.
Example 2:

Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
Output: 0
Explanation: The 6 smallest products are:
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
The 6th smallest product is 0.
Example 3:

Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
Output: -6
Explanation: The 3 smallest products are:
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
The 3rd smallest product is -6.


Constraints:

1 <= nums1.length, nums2.length <= 5 * 104
-105 <= nums1[i], nums2[j] <= 105
1 <= k <= nums1.length * nums2.length
nums1 and nums2 are sorted.

 */
public class KthSmallestProductTwoSortedArrays {
    public static void main(String[] args) {

    }

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        Arrays.sort(nums2); // Critical!
        long l = -10_000_000_000L;
        long r =  10_000_000_000L;
        long result = r;

        while (l <= r) {
            long midProduct = l + (r - l) / 2;
            long count = findCountSmallest(nums1, nums2, midProduct);
            if (count >= k) {
                result = midProduct;
                r = midProduct - 1;
            } else {
                l = midProduct + 1;
            }
        }
        return result;
    }

    public long findCountSmallest(int[] nums1, int[] nums2, long midProduct) {
        long productCount = 0;
        for (int x : nums1) {
            if (x == 0) {
                if (0 <= midProduct) {
                    productCount += nums2.length;
                }
            } else if (x > 0) {
                // nums2 sorted ascending → product ascending
                int lo = 0, hi = nums2.length - 1;
                int idx = -1;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    long prod = 1L * x * nums2[mid];
                    if (prod <= midProduct) {
                        idx = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                productCount += (idx + 1);
            } else { // x < 0
                // nums2 sorted ascending → product descending
                int lo = 0, hi = nums2.length - 1;
                int idx = nums2.length; // first index where product <= midProduct
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    long prod = 1L * x * nums2[mid];
                    if (prod <= midProduct) {
                        idx = mid;
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
                productCount += (nums2.length - idx);
            }
        }
        return productCount;
    }

    public long kthSmallestProductPq(int[] nums1, int[] nums2, long k) {
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> Long.compare(b, a)); // TLE
        for(int i:nums1){
            for(int j:nums2){
                pq.add((long)i*j);
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
        return pq.isEmpty() ? 0l: pq.peek();
    }
}
