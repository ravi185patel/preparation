package career.datastructure.swapline;

/*
https://leetcode.com/problems/zero-array-transformation-i/description/
 */
public class ZeroArrayTransformation {
    public static void main(String[] args) {

    }
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int length = nums.length;
        int freq[] = new int[length + 1];
        for (int query[] : queries) {
            freq[query[0]]++;
            freq[query[1] + 1]--;
        }
        int op = 0;
        for (int i = 0; i < nums.length; i++) {
            op += freq[i];
            if (op < nums[i]) return false;
        }
        return true;
    }
}
