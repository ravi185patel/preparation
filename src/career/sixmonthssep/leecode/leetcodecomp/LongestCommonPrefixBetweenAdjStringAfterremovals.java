package career.sixmonthssep.leecode.leetcodecomp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestCommonPrefixBetweenAdjStringAfterremovals {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(longestCommonPrefix(new String[]{"jump","run","run","jump","run"})));
        System.out.println(Arrays.toString(longestCommonPrefix(new String[]{"dog","racer","car"})));
        System.out.println(Arrays.toString(longestCommonPrefix(new String[]{"dog","racer","rac"})));
        System.out.println(Arrays.toString(longestCommonPrefix(new String[]{"dog","car","racer","rac"})));
        System.out.println(Arrays.toString(longestCommonPrefix(new String[]{"f","cfe","feab","fcc","cdfda","fcec","afae","cdeb","dc","bffd","edabe"})));
        System.out.println(Arrays.toString(longestCommonPrefix(new String[]{"efe","feae","fb"})));
    }

    public static int[] longestCommonPrefix(String[] words) {
        int length = words.length;
        int res[]=new int[length];
        for(int i=0;i<words.length;i++){

            int maxLen=0;
            for(int j=0;j<words.length-1;j++){
              if(i==j) continue;
              if(i==j+1 && j+2 >= words.length) continue;
                if(i==j+1 && j+2 <= words.length-1) {
                    if(maxLen >= words[j+2].length() || maxLen >= words[j].length()) continue;
                    maxLen = Math.max(maxLen,commonPrefix(words[j],words[j+2]));
                }else{
                    if(maxLen >= words[j+1].length() || maxLen >= words[j].length()) continue;
                    maxLen = Math.max(maxLen,commonPrefix(words[j],words[j+1]));
                }
            }
            res[i]=maxLen;
        }
        return res;
    }

    public static int commonPrefix(String word1,String word2){
        int len=0;
        int ind1=0,ind2=0;
        while(ind1 < word1.length() && ind2 < word2.length()){
            if(word1.charAt(ind1) != word2.charAt(ind2)){
                return len;
            }
            ind2++;
            ind1++;
            len++;
        }
        return len;
    }
}
