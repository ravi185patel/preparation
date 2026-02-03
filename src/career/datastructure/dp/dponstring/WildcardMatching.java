package career.datastructure.dp.dponstring;

public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","*"));
        System.out.println(isMatch("cb","?a"));
        System.out.println(isMatch("ca","?a"));
        System.out.println(isMatch("adceb","*a*b"));
        System.out.println(isMatch("acdcb","a*c?b"));
    }
    public static boolean isMatch(String s, String p) {
//        return solve(s.toCharArray(),p.toCharArray(),s.length()-1,p.length()-1);
//        return solveDp(s.toCharArray(),p.toCharArray());
        return solveDp(s,p);
    }


    public static boolean solve(char s[],char p[],int ind1,int ind2){

        if (ind2 < 0 && ind1 < 0) return true;
        if(ind2 < 0) return false;
        if(ind1 < 0) return isAllStars(p,ind2);

        if(p[ind2] == '*'){
            return solve(s,p,ind1-1,ind2) || solve(s,p,ind1,ind2-1);
        }else if(p[ind2] == '?' || s[ind1] == p[ind2]){
            return solve(s,p,ind1-1,ind2-1);
        }else{
            return false;
        }
    }

    public static boolean solveDp(String s,String t){
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

    public static boolean isAllStars(char S1[], int i) {
        for (int j = 0; j <= i; j++) {
            if (S1[j]!= '*')
                return false;
        }
        return true;
    }
}
