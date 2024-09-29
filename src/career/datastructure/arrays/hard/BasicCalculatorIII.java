package career.datastructure.arrays.hard;

import java.util.Stack;

public class BasicCalculatorIII {
    public static void main(String[] args) {
        String str="1 + 1" ; //ans = 2
//        " 6-4 / 2 " = 4
//        "2*(5+5*2)/3+(6/2+8)" = 21
//        "(2+6* 3+5- (3*14/7+2)*5)+3"=-12

        int res = calculator(str);
        System.out.println(res);
    }

    private static int calculator(String s){
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();


        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') continue;

            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                nums.push(num);
                i--;
            }
            else if (s.charAt(i) == '(') {
                ops.push(s.charAt(i));
            }
            else if (s.charAt(i) == ')') {
                while (ops.peek() != '(') {
                    int b = nums.pop();
                    int a = nums.pop();
                    nums.push(applyOperand(ops.pop(), a, b));
                }
                ops.pop();
            } else {
                while (!ops.empty() && ops.peek() != '(' && precedence(ops.peek()) >= precedence(s.charAt(i))) {
                    /*
                    If it's an operator '+', '-', '*', '/', check the
                    precedence of the operator in stack with the current operator.
                    If the operator in the stack has higher or equal precedence,
                    perform calculation and then push the current operator in the stack.
                     */
                    int b = nums.pop();
                    int a = nums.pop();
                    nums.push(applyOperand(ops.pop(), a, b));
                }
                ops.push(s.charAt(i));
            }
        }
        while (!ops.empty()) {
            int b = nums.pop();
            int a = nums.pop();
            nums.push(applyOperand(ops.pop(), a, b));
        }

        return nums.peek();
    }

    private static int precedence(char op) {
        if (op == '(' || op == ')') return 0;
        else if (op == '+' || op == '-') return 1;
        else return 2;
    }

    private static int applyOperand(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return a / b;
        }
    }

    private static boolean isOperator(char c){
        return c=='+' || c=='-' || c=='*' || c=='/';
    }
}
