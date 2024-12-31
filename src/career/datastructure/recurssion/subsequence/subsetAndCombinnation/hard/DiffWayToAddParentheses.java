package career.datastructure.recurssion.subsequence.subsetAndCombinnation.hard;

import java.util.ArrayList;
import java.util.List;

public class DiffWayToAddParentheses {
    public static void main(String[] args) {

    }

    public static List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = helper(expression);
        return res;
    }

    private static int perform(int x,int y,char op){
        if(op == '+') return x+y;
        if(op == '-') return x-y;
        if(op == '*') return x*y;
        return 0;
    }

    private static List<Integer> helper(String expression) {
        List<Integer> res = new ArrayList<>();
        Boolean isNumber = true;
        for(int i=0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(c=='-' || c=='+' || c=='*' ){
                isNumber = false;
                List<Integer> left = helper(expression.substring(0,i));
                List<Integer> right = helper(expression.substring(i+1));

                for(int x:left){
                    for(int y:right){
                        int val = perform(x,y,expression.charAt(i));
                        res.add(val);
                    }
                }
            }
        }
        if(isNumber){
            res.add(Integer.valueOf(expression));
        }
        return res;
    }
}
