package career.sixmonthssep.leetcode76.twopointer;

public class IsSubsequence {
    public static void main(String[] args) {

    }

    public static boolean isSubsequence(String s, String t) {
        int sInd =0,tInd=0;
        while(sInd < s.length() && tInd < t.length()){
            if(s.charAt(sInd) == t.charAt(tInd)){
                sInd++;
            }
            tInd++;
        }

        return sInd == s.length();
    }
}
