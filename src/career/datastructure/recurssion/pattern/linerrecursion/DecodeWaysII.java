package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

/*
https://leetcode.com/problems/decode-ways/description/
 */
public class DecodeWaysII {
    public static void main(String[] args) {
        System.out.println(numDecodingsII("12"));
        System.out.println(numDecodingsII("226"));
        System.out.println(numDecodingsII("06"));
        System.out.println(numDecodingsII("2101"));
        System.out.println(numDecodingsII("*"));
        System.out.println(numDecodingsII("1*"));
    }

    public static int numDecodingsII(String s) {
        //base
//        char sCharArr[]=s.toCharArray();
        return numDecodingsRecII(s,0);

//        return numDecodingsDep(sCharArr,0);
//        return numDecodingsNormal(s,0);
    }
    public static int numDecodingsRecII(String str,int index) {
        //base
        return -1;
    }
    public static boolean isValid(String str){
        Integer no = Integer.parseInt(str);
        if(no >= 1 && no <= 26){
            return true;
        }
        return false;
    }

    public static int numDecodingsDep(char[] sCharArr,int index) {
        int length = sCharArr.length;
        int dp[]=new int[length+1];
        dp[length] = 1;

        // Last character
        dp[length - 1] = (sCharArr[length-1] != '0') ? 1 : 0;

        for(int i=length-2;i>=0;i--){
            if (sCharArr[i] == '0') { // main part because if 0 is in mid then full string is invalid
                dp[i] = 0;
                continue;
            }
            int noTake = dp[i+1];
            int take =0;
            int validNo = (sCharArr[i] - '0') * 10 + (sCharArr[i+1] - '0');
//            System.out.println("no >" +validNo);
            if (1 <= validNo && validNo <= 26) {
                take = dp[i+2];
            }
            dp[i] = noTake + take;
        }
        return dp[0];
    }


    public static int numDecodingsNormal(String s,int index) {
        int n = s.length();

        if (n == 0 || s.charAt(0) == '0') return 0;

        int next2 = 1; // dp[i+2]
        int next1 = 1; // dp[i+1] for last char (safe because s[0] != '0')
        int curr = 0;

        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                curr = 0;
            } else {
                int noTake = next1;
                int num = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                int take =0;
                if (num >= 1 && num <= 26) {
                    take = next2;
                }
                curr = noTake + take;
            }

            next2 = next1;
            next1 = curr;
        }

        return next1;
    }

    private int dfs(String s,int startIndex){
        if(s.length() == startIndex){
            return 1;
        }
        int ways = 0;
        if(s.charAt(startIndex) == '0'){
            return 0;
        }

        ways = dfs(s,startIndex+1);
        if(startIndex+2 <= s.length()
                && Integer.parseInt(s.substring(startIndex,startIndex+2)) <= 26)       {
            ways += dfs(s,startIndex+2);
        }
        return ways;
    }

    private int dfsMemo(String s,int startIndex,int memo[]){
        if(s.length() == startIndex){
            return 1;
        }

        if(memo[startIndex] != -1){
            return memo[startIndex];
        }
        int ways = 0;
        if(s.charAt(startIndex) == '0'){
            return 0;
        }

        ways = dfsMemo(s,startIndex+1,memo);
        if(startIndex+2 <= s.length()
                && Integer.parseInt(s.substring(startIndex,startIndex+2)) <= 26)       {
            ways += dfsMemo(s,startIndex+2,memo);
        }
        return memo[startIndex] = ways;
    }

}
