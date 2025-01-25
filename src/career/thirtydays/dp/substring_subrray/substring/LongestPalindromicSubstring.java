package career.thirtydays.dp.substring_subrray.substring;

/*
https://leetcode.com/problems/longest-palindromic-substring/description/

1] recursive-> tle
2] center expand -> O(n)

 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    public static String longestPalindrome(String s) {
        leftMostInd=0;
        rightMostInd=0;
        for(int i=0;i<s.length();i++){
            isPali(i,i,s);
            isPali(i,i+1,s);
        }

        return s.substring(leftMostInd,leftMostInd + rightMostInd);
    }
    static int leftMostInd=0,rightMostInd=0;
    private static void isPali(int left,int right,String s){

        while(left >= 0 && right < s.length()){
            if(s.charAt(left) != s.charAt(right)){
                break;
            }
            left--;
            right++;
        }

        if(right-left -1 > rightMostInd){
            rightMostInd = right-left-1;
            leftMostInd = left+1;
        }
    }
}
