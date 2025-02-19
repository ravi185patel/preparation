package career.sixmonthssep.dp.subsequence.string;

import java.util.*;

/*
Print all LCS sequences
https://www.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1

https://learnersbucket.com/examples/algorithms/longest-common-subsequence-print-all-lcs/

 */
public class PrintLongestCommonSubsequence {
    public static void main(String[] args) {
//        System.out.println(all_longest_common_subsequences("abc","ab"));
//        System.out.println(all_longest_common_subsequences("abc","de"));
//        System.out.println(all_longest_common_subsequences("abc","cba"));
//        System.out.println(all_longest_common_subsequences("a","bca"));

//        System.out.println(all_longest_common_subsequences("abcde","ace"));
//        System.out.println(all_longest_common_subsequences("abc","abc"));
//        System.out.println(all_longest_common_subsequences("abaaa","baabaca"));
//        System.out.println(all_longest_common_subsequences("aaa","a"));
//        System.out.println(all_longest_common_subsequences("aababbb","bbbaababababb"));
        System.out.println(all_longest_common_subsequences("abbbabaaaa","baababab"));
//        [aabaa, ababa, abbab, baaaa, babaa, bbaba, bbbab]
    }

    public static List<String> all_longest_common_subsequences(String s, String t) {
        // Code here
//        List<String> res = new ArrayList<>();
//        longestCommonSubsequence(s,t,0,0,new StringBuilder(),res);
//        return res;
        Map<Integer,TreeSet<String>> resMap = new HashMap<>();
     /*
        longestCommonSubsequence(s,t,0,0,new StringBuilder(),resMap);
        System.out.println(resMap+" max "+maxLength);
        for(int k: resMap.keySet()){
            if(maxLength == k){
                return new ArrayList<>(resMap.get(maxLength));
            }
        }
        return new ArrayList<>();*/


        maxLength = longestCommonSubsequenceDp(s,t);

        return new ArrayList<>();
    }

    static int maxLength=0;
    static int MAX=100;
    private static void longestCommonSubsequence(String s,String t,int indexS,int indexT,StringBuilder sb,List<String> res){
        if(indexS >= s.length() || indexT >= t.length()){
            if(maxLength != 0 && maxLength <= sb.length()) {
                if(maxLength < sb.length()){
                    res.clear();
                }
                if(!res.contains(sb.toString())){
                    res.add(sb.toString());
                }
            }
            maxLength = Math.max(maxLength,sb.length());
            return;
        }

        if(s.charAt(indexS) == t.charAt(indexT)){
            sb.append(s.charAt(indexS));
            longestCommonSubsequence(s,t,indexS+1,indexT+1,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }else{
            longestCommonSubsequence(s, t, indexS + 1, indexT, sb, res);
            longestCommonSubsequence(s, t, indexS, indexT + 1, sb, res);
        }
    }


    private static void longestCommonSubsequence(String s, String t, int indexS, int indexT, StringBuilder sb, Map<Integer,TreeSet<String>> res){
        if(indexS >= s.length() || indexT >= t.length()){
            if(res.containsKey(sb.length())){
                res.get(sb.length()).add(sb.toString());
            }else{
                res.put(sb.length(),new TreeSet<>());
                res.get(sb.length()).add(sb.toString());
            }
            maxLength = Math.max(maxLength,sb.length());
            return;
        }

        if(s.charAt(indexS) == t.charAt(indexT)){
            sb.append(s.charAt(indexS));
            longestCommonSubsequence(s,t,indexS+1,indexT+1,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }else{
            longestCommonSubsequence(s, t, indexS + 1, indexT, sb, res);
            longestCommonSubsequence(s, t, indexS, indexT + 1, sb, res);
        }
    }

    private static int longestCommonSubsequenceDp(String s,String t){
        int m = s.length();
        int n = t.length();
        int dp[][]=new int[m+1][n+1];
//        for(int d[]:dp){
//            Arrays.fill(d,-1);
//        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }



        char[] data = new char[dp[m][n]];
        maxLength = dp[m][n];

//        for(int memo[]:dp){
//            System.out.println(Arrays.toString(memo));
//        }
//        printLongestCommonSubsequence(dp,s, t, s.length(),t.length() ,data, 1, 1,0);
        printLongestCommonSubsequenceRec(dp,s,t,s.length()-1,t.length()-1);
        return dp[m][n];
    }

    public static String printLongestCommonSubsequenceRec(int dp[][],String s,String t,int indexS,int indexT){
        if(indexS == 0 || indexT == 0){
            return "";
        }

        if(s.charAt(indexS) == t.charAt(indexT)){
            return printLongestCommonSubsequenceRec(dp,s,t,indexS - 1,indexT - 1) + s.charAt(indexS-1);
        }

        if(dp[indexS-1][indexT] > dp[indexS][indexT-1]){
            return printLongestCommonSubsequenceRec(dp,s,t,indexS-1,indexT);
        }else{
            return printLongestCommonSubsequenceRec(dp,s,t,indexS,indexT-1);
        }
    }

    public static String printLongestCommonSubsequenceRecMul(int dp[][],String s,String t,int indexS,int indexT){
        if(indexS == 0 || indexT == 0){
            return "";
        }

        if(s.charAt(indexS) == t.charAt(indexT)){
            return printLongestCommonSubsequenceRec(dp,s,t,indexS - 1,indexT - 1) + s.charAt(indexS-1);
        }

        if(dp[indexS-1][indexT] > dp[indexS][indexT-1]){
            return printLongestCommonSubsequenceRec(dp,s,t,indexS-1,indexT);
        }else{
            return printLongestCommonSubsequenceRec(dp,s,t,indexS,indexT-1);
        }
    }
    public static void printLongestCommonSubsequence(int dp[][],String s,String t,int sLength,int tLength
            ,char memo[],int indexS,int indexT,int currLength){
//        System.out.println(currLength+" "+maxLength);
           if(currLength == maxLength){
               memo[currLength]='\0';
//               System.out.println(String.valueOf(memo));
               return;
           }

           if(indexS > sLength || indexT > tLength){
               return;
           }

           System.out.println("print "+Arrays.toString(memo));
           for(char c='a';c<='z';c++){
               boolean done = false;
               for(int index=indexS;index<=sLength;index++){

                   if(c == s.charAt(index-1)){
                       for(int index1=indexT;index1<=tLength;index1++){
                           if(t.charAt(index1-1)== c &&
                            dp[index][index1] == maxLength - currLength){
                               memo[currLength] = c;
                               printLongestCommonSubsequence(dp,s, t, sLength,tLength ,memo, index+1, index1+1,currLength+1);
                               done = true;
                               break;
                           }
                       }
                   }
                   if(done){
                       break;
                   }
               }
           }
    }
}
