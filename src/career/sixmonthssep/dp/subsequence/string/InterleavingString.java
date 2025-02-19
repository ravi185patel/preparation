package career.sixmonthssep.dp.subsequence.string;

/*
https://leetcode.com/problems/interleaving-string/description/
 */

public class InterleavingString {
    public static void main(String[] args) {
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        System.out.println(isInterleave(s1,s2,s3));

    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if(m+n != s3.length()) return false;
        boolean memo[][] = new boolean[m+1][n+1];
//          for(boolean row[]:memo){
//              Arrays.fill(row,false);
//          }
//        return isInterleaveDfs(s1.toCharArray(),s2.toCharArray(),s3.toCharArray(),0,0,0);
        return isInterleaveDfsDp(s1.toCharArray(),s2.toCharArray(),s3.toCharArray(),0,0,0,memo);
        // return dfsDp(s1.toCharArray(),s2.toCharArray(),s3.toCharArray(),0,0,0,memo);
    }

    private static boolean isInterleaveDfs(char s1[],char s2[],char[]s3,int ind1,int ind2,int ind3) {
        if (ind3 == s3.length) {
            return true;
        }

        /*
        why if condition not work
        because if first condition satisfied it won't run second condition for the same point
        example 1,2,3,4
        1,2
        but will not execute 1 -> 3
         */
        boolean valid =
                (s1.length > ind1 && s1[ind1] == s3[ind3] && isInterleaveDfs(s1, s2, s3, ind1 + 1, ind2, ind3 + 1))
                        ||  (s2.length > ind2 && s2[ind2] == s3[ind3] && isInterleaveDfs(s1, s2, s3, ind1, ind2 + 1, ind3 + 1));
        return valid;
    }

    private static boolean isInterleaveDfsDp(char s1[],char s2[],char[]s3,int ind1,int ind2,int ind3,boolean memo[][]) {
        if(memo[ind1][ind2]){
            return false;
        }

        if (ind3 == s3.length) {
            return true;
        }
        boolean valid =
                s1.length > ind1 && s1[ind1] == s3[ind3]
                        && isInterleaveDfsDp(s1, s2, s3, ind1 + 1, ind2, ind3 + 1,memo)
                        ||  s2.length > ind2 && s2[ind2] == s3[ind3]
                        && isInterleaveDfsDp(s1, s2, s3, ind1, ind2 + 1, ind3 + 1,memo);

        // memo[ind1][ind2] = valid;
        // return valid;
        if(!valid) memo[ind1][ind2]=true;
        return valid;
    }

    private boolean dfsDp(char[] c1,char[]c2,char[]c3,int i,int j,int k,boolean invalid[][]){
        if(invalid[i][j]) return false;
        if(k == c3.length) return true;

        boolean valid =
                i<c1.length && c1[i]==c3[k]
                        && dfsDp(c1,c2,c3,i+1,j,k+1,invalid) ||
                        j < c2.length && c2[j] == c3[k]
                                && dfsDp(c1,c2,c3,i,j+1,k+1,invalid);

        if(!valid) invalid[i][j]=true;
        return valid;

    }

    private static boolean isInteravDp(String s1,String s2,String s3){
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean[][] matrix = new boolean[s2.length()+1][s1.length()+1];

        matrix[0][0] = true;

        for (int i = 1; i < matrix[0].length; i++){
            matrix[0][i] = matrix[0][i-1]&&(s1.charAt(i-1)==s3.charAt(i-1));
        }

        for (int i = 1; i < matrix.length; i++){
            matrix[i][0] = matrix[i-1][0]&&(s2.charAt(i-1)==s3.charAt(i-1));
        }

        for (int i = 1; i < matrix.length; i++){
            for (int j = 1; j < matrix[0].length; j++){
                matrix[i][j] = (matrix[i-1][j]&&(s2.charAt(i-1)==s3.charAt(i+j-1)))
                        || (matrix[i][j-1]&&(s1.charAt(j-1)==s3.charAt(i+j-1)));
            }
        }

        return matrix[s2.length()][s1.length()];
    }
}
