package career.datastructure.recurssion.striver.subsetAndCombinnation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LetterCombinationOfPhoneNumber {
    static char map[][]={{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'}
            ,{'j','k','l'},{'m','n','o'},{'p','q','r','s'}
            ,{'t','u','v'},{'w','x','y','z'}};
    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        helper(0,new StringBuilder(),digits.toCharArray(),res);
        return res;
    }

    private static void helper(int index,StringBuilder sb,char digits[],List<String> res ){
        if(index == digits.length){
            res.add(sb.toString());
            return;
        }

        int digitIndex = digits[index]-'0';
        for(char c:map[digitIndex]){
            sb.append(c);
            helper(index+1,sb,digits,res);
            sb.deleteCharAt(sb.length()-1);
        }
    }


}
