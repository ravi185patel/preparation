package career.datastructure.hasing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/binary-trees-with-factors/submissions/1860207635/
 */
public class BinaryTreesWithFactors {
    public static void main(String[] args) {

    }

    private static final int MOD = 1000000007;

    public int numFactoredBinaryTrees(int[] arr) {

        int n = arr.length;
        Arrays.sort(arr);

        Map<Integer, Long> mp = new HashMap<>();
        mp.put(arr[0], 1L);

        for (int i = 1; i < n; i++) {
            long count = 1;

            for (int j = 0; j < i; j++) {
                int v = arr[j];
                if (arr[i] % v == 0 && mp.containsKey(arr[i] / v)) {
                    count += (mp.get(v) * mp.get(arr[i] / v)) % MOD;
                    // 8-> 2,4 and 4 -> 2,2
                    // total 8->2,4 | 8 -> 4,2 | 8 -> 2,2,2 | 8 -> 2,2,2 | 8 -> 2,2,2
                    // multiply LC = currElement and RC = no/currElement.
                    count %= MOD;
                }
            }

            mp.put(arr[i], count);
        }

        int result = 0;
        for (long val : mp.values()) {
            result = (int) ((result + val) % MOD);
        }
        return result;
    }
}
