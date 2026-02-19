package career.datastructure.dp.dponstring;

import java.util.Arrays;

public class StickerToSpellWord {
    public static void main(String[] args) {
        System.out.println(minStickers(new String[]{"with","example","science"},"thehat"));
    }
    public static int minStickers(String[] stickers, String target) {
//        int ans = solve(stickers,target);
//        return ans == Integer.MAX_VALUE ? -1:ans;

/*        int n = stickers.length;
        int[][] stickerFreq = new int[n][26];

        // Precompute sticker frequency
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickerFreq[i][c - 'a']++;
            }
        }

        return solve(stickerFreq, target);*/

        int m = target.length();
        int n = stickers.length;

        // Precompute sticker frequency
        int[][] stickerFreq = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickerFreq[i][c - 'a']++;
            }
        }

        int fullMask = (1 << m) - 1;  // all letters need to be covered
        int[] dp = new int[1 << m];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        return dfs(dp, stickerFreq, target, fullMask);
    }

    private static int dfs(int[] dp, int[][] stickers, String target, int mask) {
        if (dp[mask] != -1) return dp[mask];

        int res = Integer.MAX_VALUE;
        int m = target.length();

        for (int[] sticker : stickers) {

            // Optimization: skip if sticker doesn't contain first uncovered char
            int first = -1;
            for (int i = 0; i < m; i++) {
                if (((mask >> i) & 1) == 1) {
                    first = i;
                    break;
                }
            }

            if (first == -1) break; // mask=0 handled by dp[0]

            if (sticker[target.charAt(first) - 'a'] == 0) continue;

            // Apply sticker
            int newMask = mask;
            int[] count = Arrays.copyOf(sticker, 26);
            for (int i = 0; i < m; i++) {
                if (((mask >> i) & 1) == 1) { // letter still needed
                    int c = target.charAt(i) - 'a';
                    if (count[c] > 0) {
                        count[c]--;
                        newMask &= ~(1 << i); // mark letter as covered
                    }
                }
            }

            int temp = dfs(dp, stickers, target, newMask);
            if (temp != Integer.MAX_VALUE) {
                res = Math.min(res, 1 + temp);
            }
        }

        dp[mask] = res;
        return res;
    }


    private static int solve(int[][] stickers, String target) {

        if (target.length() == 0)
            return 0;

        int[] targetFreq = new int[26];
        for (char c : target.toCharArray())
            targetFreq[c - 'a']++;

        int min = Integer.MAX_VALUE;

        for (int[] sticker : stickers) {

            // 🔥 Important comparison optimization
            if (sticker[target.charAt(0) - 'a'] == 0)
                continue;

            // Build remaining target
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 26; i++) {
                if (targetFreq[i] > 0) {
                    int remain = targetFreq[i] - sticker[i];
                    for (int k = 0; k < Math.max(0, remain); k++) {
                        sb.append((char)(i + 'a'));
                    }
                }
            }

            String newTarget = sb.toString();

            int temp = solve(stickers, newTarget);

            if (temp != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + temp);
            }
        }

        return min;
    }
        private static int solve(String[] stickers, String target) {

            // Base case
            if (target.length() == 0)
                return 0;

            int min = Integer.MAX_VALUE;

            for (String sticker : stickers) {

                // Optimization: skip if sticker doesn't contain first char
                if (sticker.indexOf(target.charAt(0)) == -1)
                    continue;

                String remaining = subtract(target, sticker);

                // If sticker made no change, skip
                if (remaining.length() == target.length())
                    continue;

                int temp = solve(stickers, remaining);

                if (temp != Integer.MAX_VALUE) {
                    min = Math.min(min, 1 + temp);
                }
            }

            return min;
        }

        // Removes sticker letters from target
        private static String subtract(String target, String sticker) {

            int[] count = new int[26];

            for (char c : target.toCharArray())
                count[c - 'a']++;

            for (char c : sticker.toCharArray()) {
                if (count[c - 'a'] > 0)
                    count[c - 'a']--;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                while (count[i]-- > 0) {
                    sb.append((char) (i + 'a'));
                }
            }

            return sb.toString();
        }

}
