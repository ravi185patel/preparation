package career.datastructure.recurssion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Count how many strobogrammatic numbers are in a range [low, high].
 */
public class StrobogrammaticIII {

    public static void main(String[] args) {
        System.out.println(strobogrammaticInRange("00","100"));
    }
    static int count = 0;

    private static final char[][] pairs = {
            {'0','0'}, {'1','1'}, {'6','9'}, {'8','8'}, {'9','6'}
    };

    public static int strobogrammaticInRange(String low, String high) {
        count = 0;
        for (int len = low.length(); len <= high.length(); len++) {
            char[] arr = new char[len];
            dfs(arr, 0, len - 1, low, high);
        }
        return count;
    }

    private static void dfs(char[] arr, int left, int right,
                     String low, String high) {

        if (left > right) {
            String num = new String(arr);

            // range checks
            if ((num.length() == low.length() && num.compareTo(low) < 0)) return;
            if ((num.length() == high.length() && num.compareTo(high) > 0)) return;

            count++;
            return;
        }

        for (char[] p : pairs) {

            // no leading zero
            if (left == 0 && right != 0 && p[0] == '0') continue;

            // middle digit must be symmetric
            if (left == right && p[0] != p[1]) continue;

            arr[left] = p[0];
            arr[right] = p[1];

            dfs(arr, left + 1, right - 1, low, high);
        }
    }
}
