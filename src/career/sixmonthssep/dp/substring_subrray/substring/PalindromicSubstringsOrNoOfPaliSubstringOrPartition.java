package career.sixmonthssep.dp.substring_subrray.substring;

/*
https://leetcode.com/problems/palindromic-substrings/description/

 */
public class PalindromicSubstringsOrNoOfPaliSubstringOrPartition {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    public static int longestPalindrome(String s) {
        int total=0;
        for(int i=0;i<s.length();i++){
            int even = isPali(i,i,s);
            int odd  = isPali(i,i+1,s);
            total += even+odd;
        }

        return total;
    }
    private static int isPali(int left,int right,String s){
        int count=0;
        while(left >= 0 && right < s.length()){
            if(s.charAt(left) != s.charAt(right)){
                break;
            }
            left--;
            right++;
            count++;
        }

        return count;
    }
}
