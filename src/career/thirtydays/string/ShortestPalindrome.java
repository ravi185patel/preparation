package career.thirtydays.string;

/*
https://leetcode.com/problems/shortest-palindrome/description/

 */
public class ShortestPalindrome {
    public static void main(String[] args) {
//        System.out.println(shortestPalindrome("aacecaaa"));
//        System.out.println(shortestPalindrome("abcd"));
        System.out.println(shortestPalindrome("aabba"));
    }

    public static String shortestPalindrome(String s) {
        return findShortestPalindrome(s);
    }

    private static String findShortestPalindrome(String s){
        String revS = new StringBuilder(s).reverse().toString();
        for(int i=0;i<s.length();i++){
//            System.out.println(revS.substring(i));
            if(s.startsWith(revS.substring(i))){
                return revS.substring(0,i)+s;
            }
        }

        return revS + s;
    }
}
