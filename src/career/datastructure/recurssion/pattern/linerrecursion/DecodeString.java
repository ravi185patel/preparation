package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
    }

    public static String decodeString(String s) {
         solve(s);
         return"";
    }

    public static void solve(String s){

        Stack<StringBuilder> stackStr = new Stack<>();
        Stack<Integer> stackNo = new Stack<>();
        int k=0;
        StringBuilder sb = new StringBuilder();
        for(char c:s.toCharArray()){
            if(Character.isDigit(c)){
                k= k*10 + (c-'0');
            }else {
                if (c == '[') {
                    stackNo.push(k);
                    k=0;
                    stackStr.push(sb);
                    sb = new StringBuilder();
                } else if(c==']'){
                    StringBuilder sbDecode = stackStr.pop();
                    int currentK = stackNo.pop();
                    for(int i=0;i<currentK;i++){
                        sbDecode.append(sb);
                    }
                    sb = sbDecode; // main point as you combine all inner
                }else{
                    sb.append(c);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
