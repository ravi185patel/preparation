package career.datastructure.recurssion.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(1));
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis1(3));
//        System.out.println(generateParenthesis(6));
    }
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(0,n,0,0,new StringBuilder(),res);
//        helperForLoop(0,n,0,0,new StringBuilder(),res);
        return res;
    }

    private static  void helper(int index,int n,int open,int close,StringBuilder sb,List<String> res){
        if(index == n*2){
            res.add(sb.toString());
            return;
        }

        if(open < n){
            sb.append("(");
            helper(index+1,n,open+1,close,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
        if(close < open){
            sb.append(")");
            helper(index+1,n,open,close+1,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static List<String> generateParenthesis1(int n) {
        if (n == 0) {
            return new ArrayList(Arrays.asList(""));
        }

        List<String> answer = new ArrayList();
        for (int leftCount = 0;leftCount < n;++leftCount) {
            for (String leftString : generateParenthesis(leftCount)) {
                for (String rightString : generateParenthesis(n - 1 - leftCount)) {
                    answer.add("(" + leftString + ")" + rightString);
                }
            }
        }
        return answer;
    }
}
