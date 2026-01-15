package career.datastructure.recurssion.pattern.divideandconqure;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }
    public static List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = helper(expression);
        List<Integer>[][] memo =new ArrayList[expression.length()][expression.length()];
        res = helperMemo(expression,memo,0,expression.length()-1);
        return res;
    }

    public static List<Integer> helper(String expression){
        List<Integer> res = new ArrayList<>();
        boolean isNumber =true;

        for(int i=0;i<expression.length();i++){
            char c= expression.charAt(i);
            if(c=='-'||c=='+'||c=='*'){
                isNumber = false;
                List<Integer> left = helper(expression.substring(0,i));
                List<Integer> right = helper(expression.substring(i+1));

                for(int x:left){
                    for(int y:right){
                        res.add(perform(x,y,c));
                    }
                }
            }
        }

        if(isNumber){
            res.add(Integer.parseInt(expression));
        }
        return res;
    }

    public static List<Integer> helperMemo(String expression,List<Integer>[][] memo,int start,int end){
        if(memo[start][end] != null){
            return memo[start][end];
        }
        List<Integer> res = new ArrayList<>();
        if(start == end){
            res.add(expression.charAt(start)-'0');
            return res;
        }
        if(end-start == 1 && Character.isDigit(expression.charAt(start))){
            int tens = expression.charAt(start) - '0';
            int ones = expression.charAt(end) - '0';
            res.add(10 * tens + ones);
            return res;
        }
        boolean isNumber =true;

        for(int i=start;i<=end;i++){
            char c= expression.charAt(i);
            if(c=='-'||c=='+'||c=='*'){
                isNumber = false;
                List<Integer> left = helper(expression.substring(0,i));
                List<Integer> right = helper(expression.substring(i+1));

                for(int x:left){
                    for(int y:right){
                        res.add(perform(x,y,c));
                    }
                }
            }
        }

        if(isNumber){
            res.add(Integer.parseInt(expression));
        }
        return memo[start][end] = res;
    }

    public static int perform(int x,int y,char c){
        if(c =='+'){
            return x+y;
        }else if(c=='-'){
            return x-y;
        }else{
            return x*y;
        }
    }
}
