package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/expression-add-operators/description/
 */

public class ExpressionAddOperators {
    public static void main(String[] args) {
//        System.out.println(addOperators("123",6));
//        System.out.println(addOperators("232",8));
//        System.out.println(addOperators("3456237490",9191));  String nums = "123";
        int target = 6;
        String nums = "123";
        List<String> result = new ArrayList<>();
        backtrack(result, nums, target, 0, 0, 0, "");

        System.out.println("Expressions that evaluate to " + target + ":");
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void backtrack(List<String> result, String nums, int target,
                                  int index, long currentValue, long lastValue, String expression) {

        // If we've used all digits
        if (index == nums.length()) {
            if (currentValue == target) {
                result.add(expression);
            }
            return;
        }

        for (int i = index; i < nums.length(); i++) {
            // Avoid numbers with leading zero
            if (i != index && nums.charAt(index) == '0') break;

            long num = Long.parseLong(nums.substring(index, i + 1));

            if (index == 0) {
                // First number (no operator)
                backtrack(result, nums, target, i + 1, num, num, "" + num);
            } else {
                // Addition
                backtrack(result, nums, target, i + 1,
                        currentValue + num, num, expression + "+" + num);

                // Subtraction
                backtrack(result, nums, target, i + 1,
                        currentValue - num, -num, expression + "-" + num);

                // Multiplication
                backtrack(result, nums, target, i + 1,
                        currentValue - lastValue + lastValue * num,
                        lastValue * num, expression + "*" + num);
            }
        }
    }
}
