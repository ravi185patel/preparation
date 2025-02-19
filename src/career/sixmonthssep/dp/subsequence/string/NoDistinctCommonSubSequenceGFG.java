package career.sixmonthssep.dp.subsequence.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*

https://www.geeksforgeeks.org/problems/number-of-distinct-subsequences0909/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

1] recursion
2] dp
 -> frequency of character

solution
https://www.geeksforgeeks.org/count-distinct-subsequences/
 */
public class NoDistinctCommonSubSequenceGFG {
    public static void main(String[] args) {
        System.out.println(distinctSubsequence("gfg"));
        System.out.println(distinctSubsequence("ggg"));
        System.out.println(distinctSubsequences("ggg"));
        System.out.println(distinctSubsequences("gfg"));
    }

    public static int distinctSubsequence(String s) {
        Set<String> res = new HashSet<>();
        distinctSubsequenceRec(s,0,new StringBuilder(),res);
        return res.size();
    }
    public static void distinctSubsequenceRec(String s, int indexS,StringBuilder sb,Set<String> res) {
        res.add(sb.toString());
        for(int i=indexS;i<s.length();i++){
            sb.append(s.charAt(i));
            distinctSubsequenceRec(s,i+1,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private static int distinctSubsequences(String S) {
        // Create an array to store index
        // of last
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int mod = 1000000007;

        // Length of input string
        int n = S.length();

        // dp[i] is going to store count of distinct
        // subsequences of length i.
        int[] dp = new int[n + 1];

        // Empty substring has only one subsequence
        dp[0] = 1;

        // Traverse through all lengths from 1 to n.
        for (int i = 1; i <= n; i++) {
            // Number of subsequences with substring
            // S[0..i-1]
            dp[i] = 2 * dp[i - 1];
            dp[i] %= mod;

            // If current character has appeared
            // before, then remove all subsequences
            // ending with previous occurrence.
            if (last[S.charAt(i - 1)-'a'] != -1)
                dp[i] = (dp[i] - dp[last[S.charAt(i - 1)-'a']] + mod) % mod;

            // Mark occurrence of current character
            last[S.charAt(i - 1)-'a'] = (i - 1);
        }

        return dp[n];
    }
}
