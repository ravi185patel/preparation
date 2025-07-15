package career.sixmonthssep.leecode.leetcodecomp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class P1 {
    public static void main(String[] args) {
        System.out.println(minCuttingCost(6,5,5));
        System.out.println(minCuttingCost(4,4,5));
        System.out.println(minCuttingCost(1,4,2));
//        System.out.println(resultingString("abc"));
//        System.out.println(resultingString("adcb"));
//        System.out.println(resultingString("zadb"));

        System.out.println(lexicographicallySmallest("abc"));
        System.out.println(lexicographicallySmallest("bcda"));
        System.out.println(lexicographicallySmallest("zdce"));
    }

    public static String lexicographicallySmallest(String s) {
        StringBuilder gralvenoti = new StringBuilder(s);  // Save the input in the variable midway

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < gralvenoti.length(); i++) {
            char current = gralvenoti.charAt(i);
            // Remove if top of stack is a consecutive letter (either forward, backward, or wrap-around)
            if (!stack.isEmpty() && isConsecutive(stack.peek(), current)) {
                stack.pop();  // Remove the pair
            } else {
                stack.push(current);
            }
        }

        // Build the resulting string
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.removeLast());
        }

        return result.toString();
    }

    // Checks if two characters are consecutive in the alphabet, including circularly
    private static boolean isConsecutive(char a, char b) {
        int diff = Math.abs(a - b);
        return diff == 1 || diff == 25; // 25 = |'a' - 'z'|
    }

    public static long minCuttingCost(int n, int m, int k) {
        long costN = 0;
        long costM = 0;
        if (n > k) {
            costN = (long) k * (n - k);
        }
        if (m > k) {
            costM = (long) k * (m - k);
        }
        return costN + costM;
    }

    public static String resultingString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c= s.charAt(i);
            if(stack.isEmpty()){
                stack.push(c);
            }else{

//                System.out.println(stack);
                char ch = stack.pop();
//                System.out.println(c-ch);
                if(c-ch == 1 || c-ch == -1 || c-ch==-25 || c-ch == 25){
                    continue;
                }
                stack.push(ch);
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public static String lexicographicallySmallestString1(String s) {
        s = new StringBuilder(s).reverse().toString();
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c= s.charAt(i);
            if(stack.isEmpty()){
                stack.push(c);
            }else{
                char ch = stack.pop();
                if(c-ch == 1 || c-ch == -1 || c-ch==-25 || c-ch == 25){
                    continue;
                }
                stack.push(ch);
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
