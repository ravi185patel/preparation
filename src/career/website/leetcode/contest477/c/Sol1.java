package career.website.leetcode.contest477.c;

import java.util.Arrays;

public class Sol1 {
    public static void main(String[] args) {
//        String s="abc";  int k=2;
//        String s="aabb"; int k=2;
//        String s="yyyzz";int k=1;
        String s="uxyhf";int k=7;


        int freq[]=new int[26];
        int distinctChar =0;
        for(char c:s.toCharArray()){
            freq[c-'a']++;
            if(freq[c-'a']==1){
                distinctChar++;
            }
        }

        if(distinctChar == k || distinctChar < k){
            System.out.println(0);
        }else {
            int noRemove = Math.abs(distinctChar-k);
            Arrays.sort(freq);
            int sum = 0;
            System.out.print(Arrays.toString(freq));
            for (int i = 0; i < 26; i++) {
                if (freq[i] != 0) {
                    if(noRemove!=0){
                        sum+=freq[i];
                        noRemove--;
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
