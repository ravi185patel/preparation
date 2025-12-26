package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

/*
https://leetcode.com/problems/decode-ways/description/
 */
public class DecodeWaysII {

    static final int MOD = 1_000_000_007;
    static Long[] dp;
    public static void main(String[] args) {
        System.out.println(numDecodingsII("12"));
        System.out.println(numDecodingsII("226"));
        System.out.println(numDecodingsII("06"));
        System.out.println(numDecodingsII("2101"));
        System.out.println(numDecodingsII("*"));
        System.out.println(numDecodingsII("1*"));
    }

    public static int numDecodingsII(String s) {
        dp = new Long[s.length()];
        return (int) solve(0, s);
    }

    public static long solve(int i,String s) {

        if (i == s.length()) return 1;
        if (s.charAt(i) == '0') return 0;

        if (dp[i] != null) return dp[i];

        long ways = 0;
        char curr = s.charAt(i);

        // ONE character
        if (curr == '*') {
            ways += 9 * solve(i + 1, s);
        } else {
            ways += solve(i + 1, s);
        }

        // TWO characters
        if (i + 1 < s.length()) {
            char next = s.charAt(i + 1);

            if (curr == '*' && next == '*') {
                ways += 15 * solve(i + 2, s);
            } else if (curr == '*') {
                ways += (next <= '6' ? 2 : 1) * solve(i + 2, s);
            } else if (next == '*') {
                if (curr == '1') ways += 9 * solve(i + 2, s);
                else if (curr == '2') ways += 6 * solve(i + 2, s);
            } else {
                int num = (curr - '0') * 10 + (next - '0');
                if (num >= 10 && num <= 26) {
                    ways += solve(i + 2, s);
                }
            }
        }

        return dp[i] = ways % MOD;

    }

}
