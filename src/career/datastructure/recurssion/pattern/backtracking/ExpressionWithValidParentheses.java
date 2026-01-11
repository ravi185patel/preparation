package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/expression-add-operators/description/
 */
import java.util.*;
import java.util.*;

import java.util.*;

public class ExpressionWithValidParentheses {

    public static void main(String[] args) {
        String nums = "232";
        int target = 8;

        Set<String> result = new HashSet<>();
        backtrack(nums, 0, "", false, result);

        System.out.println("Valid expressions:");
        for (String exp : result) {
            if (evaluate(exp) == target) {
                System.out.println(exp + " = " + target);
            }
        }
    }

    // Generate expressions
    private static void backtrack(String nums, int index,
                                  String expr, boolean hasOp,
                                  Set<String> result) {

        if (index == nums.length()) {
            result.add(expr);
            return;
        }

        for (int i = index; i < nums.length(); i++) {
            if (i != index && nums.charAt(index) == '0') break;
            String num = nums.substring(index, i + 1);

            if (index == 0) {
                backtrack(nums, i + 1, num, false, result);
            } else {
                // normal operators
                backtrack(nums, i + 1, expr + "+" + num, true, result);
                backtrack(nums, i + 1, expr + "-" + num, true, result);
                backtrack(nums, i + 1, expr + "*" + num, true, result);

                // parentheses only if expression has operator
                if (hasOp) {
                    backtrack(nums, i + 1, "(" + expr + ")+"+ num, true, result);
                    backtrack(nums, i + 1, "(" + expr + ")*"+ num, true, result);
                }
            }
        }
    }

    // Evaluate expression
    private static int evaluate(String s) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int val = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                values.push(val);
            }
            else if (c == '(') ops.push(c);
            else if (c == ')') {
                while (ops.peek() != '(') applyOp(values, ops);
                ops.pop();
            }
            else {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    applyOp(values, ops);
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) applyOp(values, ops);
        return values.pop();
    }

    private static void applyOp(Stack<Integer> values, Stack<Character> ops) {
        int b = values.pop();
        int a = values.pop();
        char op = ops.pop();

        if (op == '+') values.push(a + b);
        if (op == '-') values.push(a - b);
        if (op == '*') values.push(a * b);
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*') return 2;
        return 0;
    }
}
