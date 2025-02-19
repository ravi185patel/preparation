package career.sixmonthssep.dp.substring_subrray.substring;

import java.util.ArrayList;
import java.util.List;

/*

https://leetcode.com/problems/palindrome-partitioning/

 */
public class PalindromePartitioningOrListOfPaliPart {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),0,s);
        return res;
    }

    private static void helper(List<List<String>> res,List<String> temp,int index,String s){
        if(index == s.length()){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i= index;i<s.length();i++){
            if(isPelin(index,i,s)){
                temp.add(s.substring(index,i+1));
                helper(res,temp,i+1,s);
                temp.remove(temp.size()-1);
            }
        }
    }

    private static boolean isPelin(int start,int end,String s){
        while(start<end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;end--;
        }
        return true;
    }
}
