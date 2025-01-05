package career.datastructure.recurssion.substring;

import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(())("));
        System.out.println(longestValidParentheses(")(())"));
        System.out.println(longestValidParentheses(")(()()()())"));
    }
    static int max=0;
    public static int longestValidParentheses(String s) {

        /*max=0;
        for(int i=0;i<s.length();i++){// n
            helper(s.toCharArray(),i,0,0); // 2^n , 2^n-1.2^n-2 =>
        }
        // O(2^n)
        return max;*/

//        return iterativeStack(s.toCharArray());
        return iterativeWithoutSpace(s.toCharArray());
    }

    private static void helper(char ch[],int index,int open,int close) {
        if(open == close){
            max = Math.max(max,open+close);
        }
        if(index == ch.length){
            return;
        }

        if(ch[index] =='('){
            helper(ch,index+1,++open,close);
        }else if( open > close){
            helper(ch,index+1,open,++close);
        }
    }

    private static int iterativeStack(char ch[]){
        Stack<Integer> stack = new Stack<>();

        int length=0;
        stack.push(-1); // to handel edge case () -> [-1] and 1-(-1) = 2
        for(int i=0;i<ch.length;i++){
            if(ch[i] == '(') {
                stack.push(i);
            }else{
                stack.pop();  // why pop first because we need length so [1,3,4] and found ) then 5-3 = 2
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    length = Math.max(length, i - stack.peek());
                }
            }
        }

        return length;
    }

    private static int iterativeWithoutSpace(char ch[]){
        int length=0;
        int open=0,close=0;
        for(int i=0;i<ch.length;i++){
            if(ch[i] == '('){
                open++;
            }else{
                close++;
            }
            if(open==close){
                length = Math.max(length,2*close);
            }else if(close > open){
                close = open =0;
            }
        }

        open = close = 0;
        for(int i=ch.length-1;i>=0;i--){
            if(ch[i] == '('){
                open++;
            }else{
                close++;
            }
            if(open==close){
                length = Math.max(length,2*open);
            }else if(open > close){
                close = open =0;
            }
        }
        return length;
    }
}
