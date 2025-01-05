package career.datastructure.recurssion.substring;

//https://leetcode.com/problems/longest-palindromic-substring/description/?envType=problem-list-v2&envId=dynamic-programming
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("a"));
    }

    public static String longestPalindrome(String s) {
//        return findPalindromicSubstringBf(s); // TC = O(N^3)
//        return findPalindromicSubstringOpDp(s); // TC = O(N^2) SC= O(N^2);
        return findPalindromicSubstringCenterExpand(s); // TC = O(N^2) SC= O(1);
    }

    static int leftMostInd=0,rightMostInd=0,maxLength=0;
    private static String findPalindromicSubstringCenterExpand(String s){
        leftMostInd= rightMostInd = maxLength=0;
        char ch[]=s.toCharArray();
        for(int i=0;i<s.length();i++){
            palin(i,i,ch);
            palin(i,i+1,ch);
        }
        return s.substring(leftMostInd,leftMostInd + rightMostInd);
    }

    private static void palin(int left,int right,char ch[]){

        while(left >= 0 && right < ch.length) {
            if(ch[left] != ch[right]){
                break;
            }
            left--;
            right++;
        }

        if(rightMostInd < right - left - 1 ) {
            leftMostInd = left+1;
            rightMostInd = right-left - 1;
        }
    }

    private static String findPalindromicSubstringOpDp(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] ans = new int[] { 0, 0 };

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                ans[0] = i;
                ans[1] = i + 1;
            }
        }

        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }

    private static String findPalindromicSubstringBf(String s){
        char ch[]=s.toCharArray();
        int max=0,start=0,end=0;
        int length = s.length();
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                if(isPalin(ch,i,j)){
                    if(max < j-i){
                        start = i;
                        end= j;
                        max = j-i;
                    }
                }
            }
        }
        System.out.println(start +" "+end);
        return s.substring(start,start + (end-start+1));
    }

    private static boolean isPalin(char ch[],int start,int end){
        while(start <= end){
            if(ch[start] != ch[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
