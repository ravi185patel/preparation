package career.sixmonthssep.leecode.leetcodecomp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class S13 {
    public static void main(String[] args) {
        System.out.println(findCoins(new int[]{0,1,0,2,0,3,0,4,0,5}));
//        System.out.println(findCoins(new int[]{1,2,2,3,4}));
    }
    public static List<Integer> findCoins(int[] numWays) {
        long[] dp = new long[numWays.length + 1];
        dp[0] = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= numWays.length; i++) {
            if (dp[i] != numWays[i - 1]) {
                list.add(i);
                for (int j = i; j <= numWays.length; j++) {
                    dp[j] += dp[j - i];
                }
            }
        }
        for (int i = 1; i <= numWays.length; i++) {
            if (dp[i] != numWays[i - 1]) {
                return new ArrayList<>();
            }
        }
        return list;
    }
}
