package career.thirtydays.dp.subsequence;

import java.util.Arrays;

public class DecodeWays {
    public static void main(String[] args) {

        System.out.println(numDecodings("11"));
        System.out.println(numDecodings("111"));

        System.out.println(numDecodingsFib("11"));
        System.out.println(numDecodingsFib("111"));
    }

    public static int numDecodings(String s) {

        int n = s.length();
        int dp[]=new int[n+1];

        dp[0] = 1; // when you reach end of index == length you will return 1 for successfully
//        decoding string same we set over here.

// very important
        // it is state where we can say successfully string is decoded
        // we can't take dp[1] same like it because there may be possible that at last character it is not valid
//        due to previous character like 09


        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0:1;

        for(int i=2;i<=n;i++){
            // Check if successful single digit decode is possible.
            if(s.charAt(i-1) != '0'){
                dp[i] = dp[i-1];
            }
            int twoDigit = Integer.valueOf(s.substring(i-2,i));
            if(twoDigit >= 10 && twoDigit <= 26){
                dp[i] +=dp[i-2];
            }
        }
        System.out.print(Arrays.toString(dp));
        return dp[n];
    }

    public static int numDecodingsFib(String s) {
        if(s.charAt(0)=='0') return -1;
        int oneBack=1,twoBack=1;

        for(int i=1;i<s.length();i++){
            int curr=0;
            if(s.charAt(i)!='0'){
                curr = oneBack;
            }
//            System.out.println((i-1)+" "+i+" "+(i+1)+" "+s.substring(i-1,i+1));
            int twoDigit = Integer.valueOf(s.substring(i-1,i+1)); // we are giving start and end index not range
            if(twoDigit >= 10 && twoDigit <= 26){
                curr+=twoBack;
            }

            twoBack = oneBack;
            oneBack = curr;
        }

        return oneBack;
    }
}
