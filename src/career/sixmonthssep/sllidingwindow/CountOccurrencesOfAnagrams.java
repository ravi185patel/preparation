package career.sixmonthssep.sllidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://www.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
https://leetcode.com/problems/find-all-anagrams-in-a-string/description/  -> return start index of each anagram occurrence
 */
public class CountOccurrencesOfAnagrams {
    public static void main(String[] args) {
//        String txt = "forxxorfxdofr", pat = "for";
        String txt = "cbaebabacd", pat = "abc";
//        String  txt = "aabaabaa", pat = "aaba";
//        String  txt = "", pat = "aaba";
//        String  txt = "aaba", pat = "";
//        String  txt = "", pat = "";
//        String  txt = "abcdefghijklmn", pat = "xyz";
//        String  txt = "zbcoejuvpvaboyg", pat = "po";
//        String  txt = "wljfrimpmyhchzriwkbarxbgfcbceyhjugixwtbvtrehb", pat = "b";
        /*
          fixed window size
         */
        System.out.println(search(pat,txt));
    }

    public static int search(String pat, String txt) {
        if(txt == null || txt.length()==0 || pat == null || pat.length() == 0) return 0;
//        return countPatOccurrenceInTxt(pat,txt);
//        return countPatOccurrenceInTxtOp(pat,txt);


        List<Integer> res=indexOfPatOccurrenceInTxtOp(pat,txt);
        System.out.println(res);
        return res.size();
    }

    private static int countPatOccurrenceInTxt(String pat,String txt){
        int k = pat.length();
        int freq[]=new int[26];
        for(char c:pat.toCharArray()){
            freq[(int)(c-'a')]++;
        }

        int freq1[] = new int[26];
        int count=0,start=0;
        for(int i=0;i<txt.length();i++){
            char c= txt.charAt(i);
            freq1[(int)(c-'a')]++;
            if(k <= i-start+1){
                if(k==i-start+1 && isAllCharCovered(freq1,freq)){
                    count++;
                }
                while(start <= i && k <= i-start+1){ // only need when you work with dynamic window
                    c=txt.charAt(start);
                    freq1[(int)(c-'a')]--;
                    start++;
                }
            }
        }

        return count;
    }

    private static int countPatOccurrenceInTxtOp(String pat,String txt){
        int k = pat.length();
        int freq[]=new int[26];
        for(char c:pat.toCharArray()){
            freq[(int)(c-'a')]++;
        }
        int count=0,start=0;
        for(int i=0;i<txt.length();i++){
            char c= txt.charAt(i);
            freq[(int)(c-'a')]--;
            if(k == i-start+1){
                if(isAllCharCovered(freq)){
                    count++;
                }
                c= txt.charAt(start);
                freq[(int)(c-'a')]++;
                start++;
            }
        }

        return count;
    }


    private static List<Integer> indexOfPatOccurrenceInTxtOp(String pat,String txt){
        List<Integer> index = new ArrayList<>();
        int k = pat.length();
        int freq[]=new int[26];
        for(char c:pat.toCharArray()){
            freq[(int)(c-'a')]++;
        }
        int count=0,start=0;
        for(int i=0;i<txt.length();i++){
            char c= txt.charAt(i);
            freq[(int)(c-'a')]--;
            if(k == i-start+1){
                if(isAllCharCovered(freq)){
                    index.add(start);
                    count++;
                }
                c= txt.charAt(start);
                freq[(int)(c-'a')]++;
                start++;
            }
        }
        return index;
    }

    private static boolean isAllCharCovered(int[] freq1,int freq[]){
        for(int i=0;i<26;i++){
            if(freq1[i] != freq[i]){
                return false;
            }
        }

        return true;
    }

    private static boolean isAllCharCovered(int freq[]){
        System.out.println(Arrays.toString(freq));
        for(int i=0;i<26;i++){
            if(freq[i]!=0){
                return false;
            }
        }

        return true;
    }
}
