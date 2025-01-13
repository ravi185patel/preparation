package career.datastructure.recurssion.subsequence;

import java.util.*;

/*
https://leetcode.com/problems/count-palindromic-subsequences/description/
 */
public class CountPalindromSubSequenceOfFiveLength {
    public static void main(String[] args) {
//        String s = "103301";
        String s = "0000000";
//        String s = "9999900000";
        System.out.println(countPalindromes(s));
    }

    public static int countPalindromes(String s) {
//        String revS = new StringBuilder(s).reverse().toString();
//        List<String> sb = new ArrayList<>();

//        findPalinSubseq(s.toCharArray(),0,new StringBuilder(),sb);
//        System.out.println(sb.size());
//        return sb.size()%(1000000007)-1;


//        int res = findPalinSubseqCount(s.toCharArray(),0,new StringBuilder());
//        return res%(1000000007);


//        int dp[]=new int[s.length()];
//        Arrays.fill(dp,-1);
//        int res = findPalinSubseqCount(s.toCharArray(),0,new StringBuilder(),dp);
//        System.out.println(Arrays.toString(dp));
//        return res%(1000000007);


        return findPalinSubseqCountDp(s.toCharArray())%(1000000007);
    }

    private static void findPalinSubseq(char sChArr[], int index, StringBuilder sb, List<String> res){
        if(index == sChArr.length){
            if(sb.length() == 5 && isPal(sb.toString())) {
                res.add(sb.toString());
            }
            return;
        }
        if(sb.length() == 5 && isPal(sb.toString())) {
            res.add(sb.toString());
        }

        for(int i=index;i<sChArr.length;i++){
            sb.append(sChArr[i]);
            findPalinSubseq(sChArr,i+1,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private static boolean isPal(String s){
        int left =0,right = s.length()-1;
        while(left <= right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private static int findPalinSubseqCount(char sChArr[], int index, StringBuilder sb){
        if(index == sChArr.length){
            if(sb.length() == 5 && isPal(sb.toString())) {
                return 1;
            }
            return 0;
        }
        if(sb.length() == 5 && isPal(sb.toString())) {
            return 1;
        }

        int count=0;
        for(int i=index;i<sChArr.length;i++){
            sb.append(sChArr[i]);
            count+=findPalinSubseqCount(sChArr,i+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        return count;
    }

    private static int findPalinSubseqCount(char sChArr[], int index, StringBuilder sb,int dp[]){
        if(index == sChArr.length){
            if(sb.length() == 5 && isPal(sb.toString())) {
                return 1;
            }
            return 0;
        }
        if(sb.length() == 5 && isPal(sb.toString())) {
            return 1;
        }

        if(sb.length() == 5 && dp[index]!=-1 ){
            return dp[index];
        }

        int count=0;
        for(int i=index;i<sChArr.length;i++){
            sb.append(sChArr[i]);
            count+=findPalinSubseqCount(sChArr,i+1,sb,dp);
            sb.deleteCharAt(sb.length()-1);
        }
        return dp[index] = count;
    }


    private static int findPalinSubseqCountDp(char sChArr[]){
        int n = sChArr.length;
        int left[]=new int[10];
        int right[]=new int[10];
        Arrays.fill(left,-1);
        Arrays.fill(right,Integer.MIN_VALUE);

        for(int i=0;i<sChArr.length;i++){
            if(left[sChArr[i]-'0'] == -1){
                left[sChArr[i]-'0']=i;
            }
            right[sChArr[i]-'0'] = i;
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        int ans=0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(left[i] == -1 && left[j] == -1){
                    continue;
                }
                int start = Math.max(left[i],left[j]);
                int end = Math.min(right[i],right[j]);
                System.out.println(start+" "+end);
                Set<Character> between = new HashSet<>();
                for (int k = start + 1; k < end; k++) {
                    between.add(sChArr[k]);
                }

                ans += between.size();
                System.out.println(between);
            }
        }
        return ans;
    }
}
