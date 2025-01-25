package career.thirtydays.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
        String s = "leetcode";List<String> wordDict = Arrays.asList("leet","code");
//        String s = "applepenapple";List<String> wordDict = Arrays.asList("apple","pen");
//        String s = "catsandog";List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        System.out.println(wordBreak(s,wordDict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set=new HashSet<>(wordDict);
        return dfs(s,set);
    }

    private static boolean dfs(String s,Set<String> set){
        if(s.length() == 0) return true;

        for(int i=1;i<=s.length();i++){
            if(set.contains(s.substring(0,i)) && dfs(s.substring(i),set)){
                return true;
            }
        }

        return false;
    }

    /*
    increasing subsequence code
     */
    public static boolean wordBreakDp(String s, List<String> dict) {
        boolean[] f = new boolean[s.length() + 1];

        f[0] = true;
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }

        return f[s.length()];
    }
}
