package career.datastructure.recurssion.pattern.linerrecursion;

/*
https://leetcode.com/problems/valid-palindrome/description/

 */
public class ValidPalindrome {
    public static void main(String[] args) {

    }
    public boolean isPalindrome(String s) {
        int p = 0;
        int q = s.length() - 1;

        while (p <= q) {
            char c = s.charAt(p);
            char cb = s.charAt(q);

            if (!Character.isLetterOrDigit(c)) {
                p++;
                continue;
            }

            if (!Character.isLetterOrDigit(cb)) {
                q--;
                continue;
            }

            if (Character.toLowerCase(cb) != Character.toLowerCase(c))
                return false;
            p++;
            q--;
        }
        return true;
    }
}
