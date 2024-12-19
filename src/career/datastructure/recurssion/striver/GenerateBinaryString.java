package career.datastructure.recurssion.striver;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryString {
    public static void main(String[] args) {
        List<String> res = new ArrayList<>();
        helper(0,3,new StringBuilder(),res);
        System.out.println(res);
        res.clear();
        helperOne(0,3,new StringBuilder(),res);
        System.out.println(res);
        res.clear();
        helperOneForLoop(0,3,new StringBuilder(),res,0);
        System.out.println(res);
        res.clear();
        helperOneForLoop(0,3,new StringBuilder(),res,1);
        System.out.println(res);
    }

    private static void helper(int index,int n, StringBuilder sb, List<String> res){
        if(sb.length() == n){
            res.add(sb.toString());
            return ;
        }
        sb.append('0');
        helper(index+1,n,sb,res);
        sb.deleteCharAt(sb.length()-1);
        if(sb.length() == 0 || sb.charAt(sb.length()-1) == '0') {
            sb.append('1');
            helper(index + 1, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static void helperOne(int index,int n, StringBuilder sb, List<String> res){
        if(sb.length() == n){
            res.add(sb.toString());
            return ;
        }
        if(sb.length() == 0 || sb.charAt(sb.length()-1) == '1') {
            sb.append('0');
            helperOne(index + 1, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append('1');
        helperOne(index + 1, n, sb, res);
        sb.deleteCharAt(sb.length() - 1);
    }

    private static void helperOneForLoop(int index,int n, StringBuilder sb, List<String> res,int ch){
        if(sb.length() == n){
            res.add(sb.toString());
            return ;
        }

        for(int i=index;i<n;i++) {
            for (char c = '0'; c <= '1'; c++) {
                    if(!(sb.length() != 0 && sb.charAt(sb.length()-1) == c && ch == c -'0')) {
                        sb.append(c);
                        helperOneForLoop(i + 1, n, sb, res, ch);
                        sb.deleteCharAt(sb.length() - 1);
                    }
            }
        }
    }
}
