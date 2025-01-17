package career.thirtydays.dp.subsequence.string;

import java.util.HashSet;
import java.util.Set;

/*

https://www.geeksforgeeks.org/problems/number-of-distinct-subsequences0909/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

1] recursion
2] dp
 -> frequency of character

 */
public class NoDistinctCommonSubSequenceGFG {
    public static void main(String[] args) {
        System.out.println(distinctSubsequence("gfg"));
        System.out.println(distinctSubsequence("ggg"));
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

    private static int countCommonSubsequenceDp(String s,String t){
        int m = s.length();
        int n = t.length();
        int dp[][]=new int[m+1][n+1];


        /*

        if(s == 0 || t == 0){
           return t == 0 ? 1:0;
        }

        int taken =0;
        if(matched){
            taken = lcs(s+1,t+1);
        }
        untaken = lcs(s+1,t);
        return taken + untaken;

         */
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                int noTake = dp[i-1][j];
                int take = 0;
                if(s.charAt(i-1) == t.charAt(j-1)){
                    take = dp[i-1][j-1];
                }
                dp[i][j]= noTake + take;
            }
        }

        return dp[m][n];
    }
}
