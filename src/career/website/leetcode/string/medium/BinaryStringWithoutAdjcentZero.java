package career.website.leetcode.string.medium;

import java.util.ArrayList;
import java.util.List;
/*

010
011
101
101
110




 */

public class BinaryStringWithoutAdjcentZero {

    public static void main(String[] args) {
        List<String> res = validStrings(4);
        System.out.println(res);
        res.clear();
        helper(0,4,res,"");
        System.out.println(res);
    }

    public static void helper(int index,int n,List<String> res,String str){
            if(index == n){
                res.add(str);
                return;
            }

            if(str.length() == 0 || str.charAt(str.length()-1)=='1'){
                helper(index+1,n,res,str+'0');
            }
            helper(index+1,n,res,str+'1');
    }

    public static List<String> validStrings(int n) {
        List<String> res = new ArrayList<>();

        if(n == 1){
            res.add("0");
            res.add("1");
            return res;
        }
        int total = (int)(Math.pow(2,n)); // n

        for(int i=1;i<total;i++){
            String bitString =  Integer.toString(i, 2); // logn
            if(bitString.length() < n){
                int len = n - bitString.length();
                for(int j=0;j<len;j++){
                    bitString = '0'+bitString;
                }
                if(len >= 2){
                    continue;
                }
            }
            char prev = ' ';
            boolean flag = true;
            for(char c:bitString.toCharArray()){
                if(prev == c && c == '0'){
                    flag = false;
                    break;
                }
                prev = c;
            }
            if(flag){
                res.add(bitString);
            }
        }
        return res;
    }
    public static List<String> validStrings1(int n) {
        List<String> res = new ArrayList<>();

        if(n == 1){
            res.add("0");
            res.add("1");
            return res;
        }
        int k = (int)(Math.pow(2,n));

        for(int i=2;i<k;i++){
            String bitString =  Integer.toString(i, 2);
            int len = bitString.length()-1;
            int appendZero = n - len;
            for(int j=0;j<appendZero;j++){
                bitString = '0'+bitString;
            }
            char prev = ' ';
            boolean flag = true;
            for(char c:bitString.toCharArray()){
                if(prev == c && prev == '0'){
                    flag = false;
                    break;
                }
                prev = c;
            }
            if(flag){
                // count++;
                res.add(bitString);
            }
        }
        return res;
    }

}
