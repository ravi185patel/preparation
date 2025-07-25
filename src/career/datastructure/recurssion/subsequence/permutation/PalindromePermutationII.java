package career.datastructure.recurssion.subsequence.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Example 1:

Input: s = "aabb"
Output: ["abba","baab"]
Example 2:

Input: s = "abc"
Output: []

1]
create all palindrome permuatation.
check each substring is pelindrom or not.

2] palindrome feature -> left half == right half
so lets first find out given string has all even character occurance
or one odd and other even
then try to make string half and add in result.

 */
public class PalindromePermutationII {
    public static void main(String[] args) {

        System.out.println(generatePalindromes("aabb"));
        System.out.println(generatePalindromes("abc"));
        System.out.println(generatePalindromes("aabbhijkkjih"));
    }

    public static  List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
//        permutation(s.toCharArray(),0,res);
//        return res.stream().filter(PalindromePermutationII::isPalindrome).collect(Collectors.toList());


        int [] map = new int[128];
        char[]st = new char[s.length()/2];
        if(!canPermutePalindrome(s,map)) return res;
        char ch=0;
        int k =0;
        for(int i=0;i<map.length;i++){
            if(map[i] %2 ==1) ch = (char) i;
            for(int j=0;j<map[i] /2;j++){
                st[k++] = (char) i;
            }
        }
        Set<String> set = new HashSet<>();
        permutation(st,0,set,ch);
        return new ArrayList<String>(set);
    }

    public static boolean canPermutePalindrome(String s, int[] map) { // very imp
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0) count--;
            else count++;
        }
        return count <= 1;
    }

    public static void permutation(char st[],int index,Set<String>set,char oddChar){
        if(index == st.length){
            String res = new String(st)+(oddChar == 0 ? "" : oddChar)+(new StringBuffer(new String(st)).reverse().toString());
            set.add(res);
            return;
        }

        for(int i=index;i<st.length;i++){
            swap(st,index,i);
            permutation(st,index+1,set,oddChar);
            swap(st,index,i);
        }
    }


    public static boolean isPalindrome(String s){
        int left =0,right = s.length()-1;
        while(left <= right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void permutation(char c[],int index,List<String> res){
        if(index == c.length){
            res.add(new String(c));
            return;
        }

        Set<Character> set = new HashSet<>();
        for(int i=index;i<c.length;i++){
            if(set.contains(c[i])) continue;
            set.add(c[i]);
            swap(c,index,i);
            permutation(c,index+1,res);
            swap(c,index,i);
        }
    }

    public static void swap(char c[],int i, int j){
        char ch = c[i];
        c[i] = c[j];
        c[j]=ch;
    }
}
