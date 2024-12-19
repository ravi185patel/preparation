package career.datastructure.recurssion.striver.subsetAndCombinnation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressAddOperators {
    public static void main(String[] args) {
        System.out.println(addOperators("123",6));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        helper(0,target,0,num.toCharArray(),new StringBuilder(),res);
        return res;
    }

    private static void helper(int index,int target,int sum,char[]num,StringBuilder sb,List<String> res){
        System.out.println(target+" "+sum);
        if(target == sum){
            res.add(sb.toString());
            return;
        }

        if(index == 0){
            int no = num[index]-'0';
            helper(index+1,target,no,num,sb.append(no),res);
        }
        if(index <= num.length-1) {
            int val = num[index] - '0';
            for (char c : Arrays.asList('*', '-', '+')) {
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
                    System.out.println(sb.toString());
                    helper(index + 1, target, val, num, sb, res);
                    sb.deleteCharAt(sb.length() - 1);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}
