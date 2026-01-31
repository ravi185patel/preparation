package career.datastructure.dp.dponstring;

import java.util.Arrays;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        System.out.println(lcs("abcde","abfce"));
        System.out.println(lcs("adebc","dcadb"));
    }
    public static int lcs(String s, String t) {
        //Your code goes here

        return solve(s.toCharArray(),t.toCharArray(),s.length()-1,t.length()-1,0);
//        return solveDp(s.toCharArray(),t.toCharArray(),s.length()-1,t.length()-1);
//        return solveDpOpt(s.toCharArray(),t.toCharArray(),s.length()-1,t.length()-1);
    }

    public static int solve(char[] sCharArr,char [] tCharArr,int i,int j,int count){
        if(i < 0 || j < 0){
            return 0;
        }
        int center=count;
        if(sCharArr[i]==tCharArr[j]){
            center= 1 + solve(sCharArr,tCharArr,i-1,j-1,0);
        }
        int left=  solve(sCharArr,tCharArr,i-1,j,0);
        int right=  solve(sCharArr,tCharArr,i,j-1,0);
        return Math.max(center,Math.max(left,right));
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
                    maxLength = Math.max(maxLength,curr[indt]);
                }else {
                    curr[indt] = 0;
//                    return Math.max(solve(sCharArr,tCharArr,inds,indt-1),
//                            solve(sCharArr,tCharArr,inds-1,indt));
                }
            }
            prev = curr.clone();
        }
        return maxLength;
    }
}
