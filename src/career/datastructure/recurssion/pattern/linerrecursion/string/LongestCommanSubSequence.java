package career.datastructure.recurssion.pattern.linerrecursion.string;

/*
https://leetcode.com/problems/longest-common-subsequence/description/

 */
public class LongestCommanSubSequence {
    static int start=0,end=0;
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde","ace"));
        System.out.println(longestCommonSubsequence("abc","abc"));
        System.out.println(longestCommonSubsequence("abc","def"));
    }
    public static int longestCommonSubsequence(String text1, String text2) {
//        return longestCommonSubsequenceRec(text1.toCharArray(),text2.toCharArray(),0,0);
        return longestCommonSubsequenceDp(text1.toCharArray(),text2.toCharArray());
    }

    public static int longestCommonSubsequenceRec(char sChar[],char revSChar[],int sIndex,int revSIndex){
        if(sIndex >= sChar.length || revSIndex >= revSChar.length) return 0;

        if(sChar[sIndex] == revSChar[revSIndex]){
            return 1 + longestCommonSubsequenceRec(sChar,revSChar,sIndex+1,revSIndex+1);
        }else{
            return Math.max(longestCommonSubsequenceRec(sChar,revSChar,sIndex,revSIndex+1),
                    longestCommonSubsequenceRec(sChar,revSChar,sIndex+1,revSIndex));
        }
    }


    public static int longestCommonSubsequenceDp(char sChar[],char revSChar[],int sIndex,int revSIndex){
        int sLength = sChar.length;
        int revsLength = revSChar.length;
        int dp[][]=new int[sLength+1][revsLength+1];

        for(int i=1;i<=sLength;i++){
            for(int j=1;j<=revsLength;j++){
                if(sChar[i-1] == revSChar[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[sLength][revsLength];
    }

    public static int longestCommonSubsequenceDp(char sChar[],char revSChar[]){
        int sLength = sChar.length;
        int revsLength = revSChar.length;
        int prev[]=new int[sLength+1];

        for(int i=1;i<=sLength;i++){
            int curr[]=new int[revsLength+1];
            for(int j=1;j<=revsLength;j++){
                if(sChar[i-1] == revSChar[j-1]){
                    curr[j] = 1 + prev[j-1];
                }else{
                    curr[j] = Math.max(prev[j],curr[j-1]);
                }
            }
            prev = curr.clone();
        }
        return prev[revsLength];
    }
}
