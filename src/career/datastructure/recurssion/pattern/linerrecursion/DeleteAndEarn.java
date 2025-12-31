package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

/*
https://leetcode.com/problems/delete-and-earn/description/

🧠 Key principle (MEMORIZE THIS)
If a problem’s constraints are defined on VALUES,
your DP / recursion state must also be on VALUES.

🔑 Golden Rule (MEMORIZE THIS)
If adjacency is defined by VALUE,
DP must run on VALUE SPACE — never on array indices.

Adding 0 to the original array:
❌ does NOT fix adjacency
❌ still produces wrong recursion
❌ not interview-acceptable


❌ Why NOT i-1 and i+1?
Let’s be very explicit.
Problem with i-1
If you write:
solve(i) depends on solve(i-1)

Then:
You go backward
You will revisit states
You lose a clear base case
Memoization becomes messy
Bottom-up DP becomes unclear
DP works best when state moves in one direction.

Example
values:   0   1   2   3   4
points:   0   0   2   3   4

solve(2) → best from [2,3,4]
solve(3) → best from [3,4]
solve(4) → best from [4]

 */
public class DeleteAndEarn {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2,2,3,3,3,4}));
        System.out.println(deleteAndEarn(new int[]{3,4,2}));
    }

    public static int deleteAndEarn(int[] nums) {
        if (nums.length == 0) return 0;

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        // normalize input into value-space
        int[] points = new int[max + 1];
        for (int x : nums) {
            points[x] += x;
        }
        System.out.println(Arrays.toString(points));
        return solve(points, 0);
    }

    // solve(i) = max points from value i onward
    private static int solve(int[] points, int i) {
        if (i >= points.length) return 0;

        int skip = solve(points, i + 1);
        int take = points[i] + solve(points, i + 2);

        return Math.max(take, skip);
    }


    public static int deleteAndEarnDp(int[] nums) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] points = new int[max + 1];
        for (int x : nums) points[x] += x;

        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int i = 0; i <= max; i++) {
            int curr = Math.max(prev1, prev2 + points[i]);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
