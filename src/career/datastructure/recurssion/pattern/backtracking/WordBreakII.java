package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        solve(0,s,new String(),wordDict,res);        
        return res;
    }

    public static void solve(int index,String s,String sb, List<String> wordDict,List<String> res){
        if(index == s.length()){
            res.add(sb);
            return;
        }

        if(index > s.length()){
            return;
        }

        for(String word:wordDict){
             int len = word.length();

            if (index + len <= s.length() && s.startsWith(word,index)){
                 solve(index+word.length(),s,index == 0 ? word:sb+" "+word,wordDict,res);
            }
        }
    }

    public static List<String> solve(int index,String s,
                             List<String> wordDict,
    Map<Integer,List<String>> memo){
        if(memo.containsKey(index)){
            return memo.get(index);
        }
        List<String> res = new ArrayList<>();
        if(index == s.length()){
            res.add("");
            return res;
        }


        for(String word:wordDict){
            int len = word.length();

            if (index + len <= s.length() && s.startsWith(word,index)){
                List<String> sub = solve(index+len,s,wordDict,memo);
                for(String tail:sub){
                    res.add(tail.isEmpty() ? word:word+" "+tail);
                }
            }
        }
        memo.put(index,res);
        return res;
    }
}