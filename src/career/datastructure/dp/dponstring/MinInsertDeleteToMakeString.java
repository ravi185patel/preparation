package career.datastructure.dp.dponstring;

public class MinInsertDeleteToMakeString {
    public static void main(String[] args) {
        System.out.println(lcs("kitten","sitting"));
        System.out.println(lcs("flaw","lawn"));
    }
    public static int lcs(String s,String t) {
//        return solve(s.toCharArray(),t.toCharArray(),s.length()-1,t.length()-1);
//        return solveDp(s.toCharArray(),t.toCharArray(),s.length()-1,t.length()-1);
        int longestPalindromeStr = solveDpOpt(s.toCharArray(),t.toCharArray(),s.length()-1,t.length()-1);
        return (t.length()-longestPalindromeStr) + (s.length() - longestPalindromeStr);
    }

    public static int solve(char[] sCharArr,char [] tCharArr,int inds,int indt){
        if(inds < 0 || indt < 0) return Integer.MIN_VALUE;

        if(inds == 0 && indt == 0) return 1;

        if(sCharArr[inds] == tCharArr[indt]){
            return 1 + solve(sCharArr,tCharArr,inds-1,indt-1);
        }else {
            return Math.max(solve(sCharArr,tCharArr,inds,indt-1),
                    solve(sCharArr,tCharArr,inds-1,indt));
        }
    }

    public static int solveDp(char[] sCharArr,char [] tCharArr,int inds,int indt){
        int sLength = sCharArr.length;
        int tLength = tCharArr.length;
        int dp[][]=new int[sLength+1][tLength+1];

        for(inds=1;inds<=sLength;inds++){
            for(indt=1;indt<=tLength;indt++){
                if(sCharArr[inds-1] == tCharArr[indt-1]){
                    dp[inds][indt] = 1 + dp[inds-1][indt-1];//solve(sCharArr,tCharArr,inds-1,indt-1);
                }else {
                    dp[inds][indt] = Math.max(dp[inds][indt-1],dp[inds-1][indt]);
//                    return Math.max(solve(sCharArr,tCharArr,inds,indt-1),
//                            solve(sCharArr,tCharArr,inds-1,indt));
                }
            }
        }

        return dp[sLength][tLength];
    }

    public static int solveDpOpt(char[] sCharArr,char [] tCharArr,int inds,int indt){
        int sLength = sCharArr.length;
        int tLength = tCharArr.length;
        int maxLength =0 ;
        int prev[]=new int[tLength+1];

        for(inds=1;inds<=sLength;inds++){
            int curr[]=new int[tLength+1];
            for(indt=1;indt<=tLength;indt++){
                if(sCharArr[inds-1] == tCharArr[indt-1]){
                    curr[indt] = 1 + prev[indt-1];//solve(sCharArr,tCharArr,inds-1,indt-1);
//                    maxLength = Math.max(maxLength,curr[indt]);
                }else {
                    curr[indt] = Math.max(curr[indt-1],prev[indt]);
//                    return Math.max(solve(sCharArr,tCharArr,inds,indt-1),
//                            solve(sCharArr,tCharArr,inds-1,indt));
                }
            }
            prev = curr.clone();
        }
        for(int no:prev){
            maxLength = Math.max(maxLength,no);
        }
        return maxLength;
    }
}
