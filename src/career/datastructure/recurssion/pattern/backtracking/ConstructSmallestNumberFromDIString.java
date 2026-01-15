package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ConstructSmallestNumberFromDIString {
    public static String resStr="";
    public static void main(String[] args) {
        System.out.println(smallestNumber("IIIDIDDD"));
    }
    public static String smallestNumber(String pattern) {
        int[] arr = new int[pattern.length() + 1];
//        solve(0, pattern.toCharArray(), -1, arr, new HashSet<>());
        solveBitmasking(0, pattern.toCharArray(), -1, arr, 0 );
        resStr = Arrays.toString(arr);
        return resStr;
    }

    public String smallestNumber1(String pattern) {
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for(int i=0;i<=pattern.length();i++){
            sb.append((char)('1'+i));
            if(i==pattern.length() || pattern.charAt(i) == 'I'){
                res.append(sb.reverse());
                sb = new StringBuilder();
            }
        }

        return res.toString();
    }

    public static boolean solve(int index,char[] pattern,int prevValue,int[] arr,Set<Integer> used) {
        if (index == pattern.length + 1) {
            return true;
        }

        for (int val = 1; val <= pattern.length + 1; val++) {
            if (used.contains(val)) continue;

            if (index > 0) {
                char p = pattern[index - 1];
                if (p == 'I' && val <= prevValue) continue;
                if (p == 'D' && val >= prevValue) continue;
            }

            arr[index] = val;
            used.add(val);

            if (solve(index + 1, pattern, val, arr, used)) {
                return true;
            }
            used.remove(val);
        }
        return false;
    }

    public static boolean solveBitmasking(int index,char[] pattern,int prevValue,int[] arr,int mask) {
        if (index == pattern.length + 1) {
            return true;
        }

        for (int val = 1; val <= pattern.length + 1; val++) {
            if ((mask & (1<<val)) != 0) continue;

            if (index > 0) {
                char p = pattern[index - 1];
                if (p == 'I' && val <= prevValue) continue;
                if (p == 'D' && val >= prevValue) continue;
            }

            arr[index] = val;

            if (solveBitmasking(index + 1, pattern, val, arr, mask | 1 << val)) {
                return true;
            }
        }
        return false;
    }

//    (index, prevValue, mask)
//    index → current position
//
//    prevValue → last placed digit
//
//    mask → used digits
    public static boolean solveBitmaskingTopDown(int index,char[] pattern,int prevValue,int[] arr,int mask) {
        if (index == pattern.length + 1) {
            return true;
        }

        for (int val = 1; val <= pattern.length + 1; val++) {
            if ((mask & (1<<val)) != 0) continue;

            if (index > 0) {
                char p = pattern[index - 1];
                if (p == 'I' && val <= prevValue) continue;
                if (p == 'D' && val >= prevValue) continue;
            }

            arr[index] = val;

            if (solveBitmasking(index + 1, pattern, val, arr, mask | 1 << val)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValid(int arr[],char[] pattern){
        int length = pattern.length;
        for(int i=0;i<length-1;i++){
            if(pattern[i] =='I' && arr[i-1] >= arr[i]){
                return false;
            } else if(pattern[i] =='D' && arr[i-1] <= arr[i]){
                return false;
            }
        }
        return true;
    }
}
