package career.datastructure.arrays.medium;

import java.util.Stack;

public class BasicCalculatorII {
    public static void main(String[] args) {
        String str="1 + 1" ; //ans = 2
//        " 6-4 / 2 " = 4
//        "2*(5+5*2)/3+(6/2+8)" = 21
//        "(2+6* 3+5- (3*14/7+2)*5)+3"=-12

        int res = calculator(str);
        System.out.println(res);
    }

    private static int calculator(String str){
        char ch[]=str.toCharArray();
        int length =str.length(),index=0;
        Stack<Integer> stack = new Stack<>();
        int no=0;
        char op ='+';
        while(index < length){
            char c=ch[index];
            if(Character.isDigit(c)){
                no=(no*10)+(c-'0');
            }
            if(isOperator(c) || index == length-1) {
                if (op == '+') {
                    stack.push(no);
                }else if(op =='-'){
                    stack.push(-no);
                }else if(op =='/'){
                    stack.push(stack.pop()/no);
                }else if(op =='*'){
                    stack.push(stack.pop()*no);
                }
                no=0;
                op=c;
            }
            index++;
        }
        System.out.println(stack);
        int ans =0;
        while(!stack.isEmpty()){
            ans+=stack.pop();
        }

        return ans;
    }

    private static boolean isOperator(char c){
        return c=='+' || c=='-' || c=='*' || c=='/';
    }
}
