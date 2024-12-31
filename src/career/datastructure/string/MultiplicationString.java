package career.datastructure.string;

import java.util.Stack;

public class MultiplicationString {
    public static void main(String[] args) {
        System.out.println(multiplyString("1","12"));
    }

    private static String multiplyString(String s1,String s2){
        Stack<Character> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        StringBuilder sb = new StringBuilder(s2);
        for(char c:s1.toCharArray()){
            st1.push(c);
        }


        for(int i=s2.length()-1;i>=0;i--){
            int mul = s2.charAt(i)-'0';
            int carry=0;
            StringBuilder temp = new StringBuilder(sb.length());
            for(int j=sb.length()-1;j>=0;j--){
                int curr = sb.charAt(j)-'0' + carry;
                mul *= curr;
                int qua = mul%10;
                carry = mul/10;
                temp.setCharAt(0,(char)qua);
            }
            for(int k=0;k<s2.length();k--){
                temp.setCharAt(0,'0');
            }
            System.out.println(temp.toString());
            temp.reverse();
            sb = new StringBuilder(temp);
        }
        return sb.toString();
    }
}
