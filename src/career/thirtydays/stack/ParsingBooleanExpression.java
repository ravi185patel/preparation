package career.thirtydays.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
https://leetcode.com/problems/parsing-a-boolean-expression/description/
 */
public class ParsingBooleanExpression {
    public static void main(String[] args) {
        System.out.println(parseBoolExpr("&(|(f))"));
        System.out.println(parseBoolExpr("|(f,f,f,t)"));
    }

    public static boolean parseBoolExpr(String expression) {
        Stack<Character> stack=new Stack<>();

        for(char ch:expression.toCharArray()){
            if(ch==',' || ch == '(') continue;

            if(isOperator(ch) || ch == 't' || ch == 'f'){
                stack.push(ch);
            }else if(ch == ')'){
                boolean hasTrue = false,hasFalse = false;

                while(!isOperator(stack.peek())){
                    char c= stack.pop();
                    if(c == 't') hasTrue = true;
                    if( c== 'f') hasFalse = true;
                }

                char op = stack.pop();

                if(op == '|'){
                    stack.push(hasTrue ? 't':'f');
                }else if(op == '&'){
                    stack.push(hasFalse ? 'f':'t');
                }else if(op == '!'){
                    stack.push(hasTrue ? 'f':'t');
                }

            }
        }
        return stack.isEmpty() || stack.peek() != 'f';
    }
    public static boolean parseBoolExpr1(String expression) {
        //using stack and list.
        Stack<Character> stackOp=new Stack<>();
        Stack<Character> stackCh=new Stack<>();

        for(char ch:expression.toCharArray()){
            if(isOperator(ch)){
                stackOp.push(ch);
            }else{
                if(ch == ')'){

                    System.out.println(stackCh);
                    char op = stackOp.pop();
                    boolean res = stackCh.peek() != 'f';
                    stackCh.pop();
                    if(!stackCh.isEmpty() && stackCh.peek() == '('){
                        if(op == '!'){
                            res = !res;
                        }
                    }else{
                        while(!stackCh.isEmpty() && stackCh.peek() != '('){
                            boolean val = stackCh.peek() != 'f';
                            if(op == '|'){
                                res |= val;
                            }else if(op == '&'){
                                res &= val;
                            }else if(op == '!'){
                                res = val == false;
                            }
                            stackCh.pop();
                        }
                    }
                    stackCh.pop();
                    stackCh.push(!res ? 'f':'t');
                }else{
                    if(ch==',') continue;
                    stackCh.push(ch);
                }
            }
        }
        // System.out.println(stackCh);
        return stackCh.isEmpty() || (stackCh.peek() != 'f');
    }
    static Set<Character> set =new HashSet<>();
    static{
        set.add('&');
        set.add('|');set.add('!');
    }
    private static boolean isOperator(char c){
        return set.contains(c);
    }
}
