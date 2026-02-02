package career.datastructure.dp.dponstring;

import java.util.Arrays;

public class ShortestSupersequence {
    public static void main(String[] args) {
        System.out.println(shortestSupersequence("mno","nop"));
    }

    public static String shortestSupersequence(String s1,String s2){
        return solveDp(s1.toCharArray(),s2.toCharArray(),0,0);
    }

    public static String res="";

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

    public static String solveDp(char[] sCharArr,char [] tCharArr,int inds,int indt){
        int sLength = sCharArr.length;
        int tLength = tCharArr.length;
        int dp[][]=new int[sLength+1][tLength+1];

        for(inds=1;inds<=sLength;inds++){
            for(indt=1;indt<=tLength;indt++){
                if(sCharArr[inds-1] == tCharArr[indt-1]){
                    dp[inds][indt] = 1 + dp[inds-1][indt-1];
                }else {
                    dp[inds][indt] = Math.max(dp[inds][indt-1],dp[inds-1][indt]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = sLength,j=tLength;

        while(i > 0 && j > 0){
            if(sCharArr[i-1] == tCharArr[j-1]){
                sb.append(sCharArr[i-1]);
                i--;
                j--;
            }

            else if(dp[i-1][j] >dp[i][j-1]){
                sb.append(sCharArr[i-1]);
                i--;
            }
            else {
                sb.append(tCharArr[j-1]);
                j--;
            }

        }
        while(i > 0){
            sb.append(sCharArr[i-1]);
            i--;
        }
        while(j > 0){
            sb.append(tCharArr[j-1]);
            j--;
        }


        return sb.reverse().toString();
    }
}
