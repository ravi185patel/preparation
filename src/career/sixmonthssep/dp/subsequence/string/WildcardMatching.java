package career.sixmonthssep.dp.subsequence.string;

/*
https://leetcode.com/problems/wildcard-matching/description/

String matching using wildcard where you need to compare substring
but here you can jump or skip few character based on ? and *
so it becomes lcs problem
if character matched or ? then s-1,t-1
else
    if * then either s-1,t or s,t-1
    else false ( as there is no character matched and not even no wild character)
 */
public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("cb","?a"));
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","*"));
        System.out.println(isMatch("adceb","*a*b"));
    }

    public static boolean isMatch(String s, String p) {
//        return isPatternMatchRec(s,p,s.length()-1,p.length()-1);
        return isPatternMatch(s,p);
    }

    private static boolean isPatternMatchRec(String s,String t,int indexS,int indexT) {

        if(indexS < 0 && indexT < 0) return true;
        if(indexS>=0 && indexT < 0) return false;

        if(indexS < 0 && indexT>=0){
            return allStar(t,indexT);
        }

        if(s.charAt(indexS) == t.charAt(indexT) || t.charAt(indexT) == '.'){
            return isPatternMatchRec(s,t,indexS-1,indexT-1);
        }else {

            if (t.charAt(indexT) == '*') {
                return isPatternMatchRec(s,t,indexS-1,indexT)
                      || isPatternMatchRec(s,t,indexS,indexT-1);
            }else{
                return false;
            }
        }
    }


    private static boolean allStar(String t,int indexT){
        for (int j = 0; j <= indexT; j++) {
            if (t.charAt(j) != '*')
                return false;
        }
        return true;
    }

    private static boolean allStarDp(String t,int indexT){
        for (int j = 1; j <= indexT; j++) {
            if (t.charAt(j-1) != '*')
                return false;
        }
        return true;
    }
    private static boolean isPatternMatch(String s,String t){
        int m = s.length();
        int n = t.length();
        boolean dp[][]=new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int j = 1; j <= n; ++j) {
            if (t.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }


        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1) || t.charAt(j-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    if(t.charAt(j-1)=='*'){
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[m][n];
    }

}
