package career.datastructure.recurssion.pattern.backtracking;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WordBreak {
    public static void main(String[] args) {
       System.out.println(wordBreak("leetcode",new String[]{"leet","code"}));
       System.out.println(wordBreak("applepenapple",new String[]{"apple","pen"}));
       System.out.println(wordBreak("catsandog",new String[]{"cats","dog","sand","and","cat"}));
    }
    public static boolean wordBreak(String s, String[]wordDict) {
//        return solve(0,new String(),s,wordDict);

        /*Boolean memo[]=new Boolean[s.length()+1];
        return solve1(0,s,wordDict, memo);*/

        return solveOP(s,wordDict);
    }
    public static boolean solve(int index,String newStr,String target,String[]wordDict){
        if(newStr.length() == target.length()){
            if(newStr.equalsIgnoreCase(target)){
                return true;
            }
            return false;
        }
//        System.out.println(newStr);
        if(newStr.length() > target.length()){
            return false;
        }

        for(int i=0;i<wordDict.length;i++){
            if(solve(i,newStr+wordDict[i],target,wordDict)){
                return true;
            }
        }
        return false;
    }
/*
        | Optimization         | Benefit              |
        | -------------------- | -------------------- |
        | No string building   | Saves memory         |
        | Index-based matching | Faster               |
        | Memoization          | Avoids recomputation |
        | Early exit           | Stops once true      |
*/

    // no string creation required as only want to be sure lendth will be same and startWith will confirm it will be part of string.
    public static boolean solve1(int index,String target,String[]wordDict,Boolean[]memo){

        if (index == target.length()) {
            return true;
        }

        if (memo[index] != null) {
            return memo[index];
        }

        for (String word : wordDict) {
            int len = word.length();

            if (index + len <= target.length()
                    && target.startsWith(word, index)) {

                if (solve1(index + len, target, wordDict, memo)) {
                    return memo[index] = true;
                }
            }
        }

        return memo[index] = false;
    }

    public static boolean solveOP(String target,String[]wordDict){

        List<String> dict = Arrays.asList(wordDict);
        boolean dp[]=new boolean[target.length()+1];
        dp[0]=true;

        for(int i=1;i<=target.length();i++){
            for(int j=0;j<i;j++){
                if(dp[j] && dict.contains(target.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[target.length()];
    }
}
