package career.datastructure.recurssion.subsequence.subsetAndCombinnation.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressAddOperators {


    static public ArrayList<String> answer;
    static public String digits;
    static public long targetM;

    public static void main(String[] args) {
        System.out.println(addOperators("123",6));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        helper(0,target,0,num.toCharArray(),new StringBuilder(),res);
        return res;

//        if (num.length() == 0) {
//            return new ArrayList<String>();
//        }
//
//        targetM = target;
//        digits = num;
//        answer = new ArrayList<String>();
//        recurse(0, 0, 0, 0, new ArrayList<String>());
//        return answer;
    }

    public static void recurse(
            int index, long previousOperand, long currentOperand, long value, ArrayList<String> ops) {
        String nums = digits;

        // Done processing all the digits in num
        if (index == nums.length()) {

            // If the final value == target expected AND
            // no operand is left unprocessed
            if (value == targetM && currentOperand == 0) {
                StringBuilder sb = new StringBuilder();
                ops.subList(1, ops.size()).forEach(v -> sb.append(v));
                answer.add(sb.toString());
            }
            return;
        }

        // Extending the current operand by one digit
        currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));
        String current_val_rep = Long.toString(currentOperand);
        int length = nums.length();

        // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
        // valid operand. Hence this check
        if (currentOperand > 0) {

            // NO OP recursion
            recurse(index + 1, previousOperand, currentOperand, value, ops);
        }

        // ADDITION
        ops.add("+");
        ops.add(current_val_rep);
        recurse(index + 1, currentOperand, 0, value + currentOperand, ops);
        ops.remove(ops.size() - 1);
        ops.remove(ops.size() - 1);

        if (ops.size() > 0) {

            // SUBTRACTION
            ops.add("-");
            ops.add(current_val_rep);
            recurse(index + 1, -currentOperand, 0, value - currentOperand, ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            // MULTIPLICATION
            ops.add("*");
            ops.add(current_val_rep);
            recurse(
                    index + 1,
                    currentOperand * previousOperand,
                    0,
                    value - previousOperand + (currentOperand * previousOperand),
                    ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);
        }
    }
    private static void helper(int index,int target,int sum,char[]num,StringBuilder sb,List<String> res){
        System.out.println(sb.toString()+" <> "+sum);
        if(index == num.length){
            if(target==sum){
                System.out.println("in");
                res.add(sb.toString());
            }
            return;
        }

        if(index == 0){
            int no = num[index]-'0';
            helper(index+1,target,no,num,sb.append(no),res);
        }
        if(index <= num.length-1) {
            int val = num[index] - '0';
            for (char c : Arrays.asList('+', '-', '*')) {
                sb.append(c);
                sb.append(val);
                if (c == '*') {
                    val = val * sum;
                } else if (c == '-') {
                    val = val - sum;
                } else {
                    val = val + sum;
                }
                if (target >= val) {
                    helper(index + 1, target, val, num, sb, res);
                    sb.deleteCharAt(sb.length() - 1);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}
