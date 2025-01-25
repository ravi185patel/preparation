package career.thirtydays.dp.subsequence.string;

import java.util.Arrays;

/*
https://leetcode.com/problems/longest-common-subsequence/description/
 */
public class CountLongestCommonSubSequence {
    public static void main(String[] args) {
        System.out.println(countLCS("aaabcde","ace"));
        System.out.println(countLCS("AGGTAB","GXTXAYB"));
        /*
        "GTAB"
        "GXAB"
         */
    }

    public static int countLCS(String A, String B) {
        int m = A.length(), n = B.length();

        // Initialize dp and count arrays
        int[][] dp = new int[m + 1][n + 1];
        int[][] count = new int[m + 1][n + 1];

        // Fill dp and count arrays
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    count[i][j] = 1;  // There's one way to have an LCS of length 0
                } else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    count[i][j] = count[i - 1][j - 1];  // Carry over count from previous diagonal
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    if (dp[i][j] == dp[i - 1][j]) {
                        count[i][j] += count[i - 1][j];
                    }
                    if (dp[i][j] == dp[i][j - 1]) {
                        count[i][j] += count[i][j - 1];
                    }
                    if (dp[i - 1][j] == dp[i][j - 1]) {
                        count[i][j] -= count[i - 1][j - 1]; // Avoid double counting
                    }
                }
            }
        }

        // The result is the number of LCS of length dp[m][n]
        for(int me[]:dp){
            System.out.println(Arrays.toString(me));
        }
        return count[m][n];
    }
}
