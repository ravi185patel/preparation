package career.datastructure.recurssion.subsequence.subsetAndCombinnation;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/generate-parentheses/description/?envType=problem-list-v2&envId=dynamic-programming

public class GenerateParentheses {
    public static void main(String[] args) {

        List<String> res = new ArrayList<>();
        int n = 4;
        helper(0,0,0,n,new StringBuilder(),res);
        System.out.println(res);

        char temp[]=new char[n*2];
        res.clear();
        helper(n,0,0,0,res,temp);
        System.out.println(res);
    }

    public static void helper(int n,int index, int open,int close,List<String> res,char[] temp){
        if(index == n*2){
            res.add(new String(temp));
            return;
        }

        if(open < n){
            temp[index]='(';
            helper(n,index+1,open+1,close,res,temp);
        }
        if(close < open){
            temp[index]=')';
            helper(n,index+1,open,close+1,res,temp);
        }

    }


    private static void helper(int index,int open, int close, int n, StringBuilder sb, List<String> res){
        if(index == n*2){
            res.add(sb.toString());
            return;
        }

        if(open < n){
            sb.append("(");
            helper(index+1,open+1,close,n,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
        if(close < open){
            sb.append(")");
            helper(index+1,open,close+1,n,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
