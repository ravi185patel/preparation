package career.thirtydays.dp.subsequence.lis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain {
    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
        System.out.println(longestStrChain(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"}));
        System.out.println(longestStrChain(new String[]{"abcd","dbqca"}));
    }

    /*
    Let N be the number of words in the list and L be the maximum possible length of a word.

Time complexity: O(N⋅(logN+L
2
 )).

Sorting a list of size N takes O(NlogN) time. Next, we use two for loops in which the outer loop runs for O(N) iterations and the inner loop runs for O(L
2
 ) iterations in the worst case scenario. The first L is for the inner loop and the second L is for creating each predecessor. Thus the overall time complexity is O(NlogN+(N⋅L
2
 )) which equals O(N⋅(logN+L^2)).

Space complexity: O(N).
     */
    public static int longestStrChain(String[] words) {
        Arrays.sort(words,(s1, s2) ->{
            int len = s1.length() - s2.length();
            if(len == 0){
                return s1.compareTo(s2);
            }
            return len;
        });

        Map<String,Integer> dp = new HashMap<>();
        int res=0;
        for(String word:words){
            int best =0;
            System.out.println(word+" < word");
            for(int i=0;i<word.length();i++){
                String prev = word.substring(0,i) + word.substring(i+1);
                System.out.println(prev);
                best = Math.max(best,dp.getOrDefault(prev,0)+1);
            }
            dp.put(word,best);
            res = Math.max(res,best);
        }

        return res;
    }
}
