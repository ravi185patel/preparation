package career.sixmonthssep.sw.substring;

import java.util.Arrays;

/*
https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
 */
public class LongestSubStrWithAtLeastKRepChar {
    public static void main(String[] args) {
//        System.out.println(longestSubstring("aaabb",3));
//        System.out.println(longestSubstring("ababbc",2));
//        System.out.println(longestSubstring("aaaaa",3));
        System.out.println(longestSubstring("bbaaacbd",3));
        System.out.println(longestSubstringBf("bbaaacbd",3));
        System.out.println(longestSubstring("aaaaa",3));
        System.out.println(longestSubstringBf("aaaaa",3));
    }

    public static int longestSubStringBF(String s,int k){
        int freq[]=new int[26];
        int length = s.length();
        int maxLength=0;
        for(int i=0;i<length;i++){
            Arrays.fill(freq,0);
            for(int j=i;j<length;j++) {
                int index = s.charAt(j) - 'a';
                freq[index]++;
                if(freq[index] >= k){
                    if(isAllAboveK(freq,k)){
                        maxLength = Math.max(maxLength,j-i+1);
                    }
                }
            }

        }
        return maxLength;
    }

    private static boolean isAllAboveK(int freq[],int k){
        for(int i:freq){
            if(i!=0 && i <k){
                return false;
            }
        }
        return true;
    }
    public static int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] countMap = new int[26];
        int maxUnique = getMaxUniqueLetters(s);
        int result = 0;
        for (int currUnique = 1; currUnique <= maxUnique; currUnique++) {
            // reset countMap
            Arrays.fill(countMap, 0);
            int windowStart = 0, windowEnd = 0, idx = 0, unique = 0, countAtLeastK = 0;
            while (windowEnd < str.length) {
                // expand the sliding window
                if (unique <= currUnique) {
                    idx = str[windowEnd] - 'a';
                    if (countMap[idx] == 0) unique++;
                    countMap[idx]++;
                    if (countMap[idx] == k) countAtLeastK++;
                    windowEnd++;
                }
                // shrink the sliding window
                else {
                    idx = str[windowStart] - 'a';
                    if (countMap[idx] == k) countAtLeastK--;
                    countMap[idx]--;
                    if (countMap[idx] == 0) unique--;
                    windowStart++;
                }
                if (unique == currUnique && unique == countAtLeastK)
                    result = Math.max(windowEnd - windowStart, result);
            }
        }

        return result;
    }

    // get the maximum number of unique letters in the string s
    static int getMaxUniqueLetters(String s) {
        boolean map[] = new boolean[26];
        int maxUnique = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map[s.charAt(i) - 'a']) {
                maxUnique++;
                map[s.charAt(i) - 'a'] = true;
            }
        }
        return maxUnique;
    }

    public int longestSubstringRec(String s, int k) {
        char[] str = s.toCharArray();
        return helper(str,0,s.length(),k);
    }

    private int helper(char[] str, int start, int end,  int k){
        if (end - start < k) return 0;//substring length shorter than k.
        int[] count = new int [26];
        for (int i = start; i<end; i++) {
            int idx = str[i] - 'a';
            count[idx]++;
        }
        for (int i=0; i<26; i++) {
            if (count[i] < k && count[i] > 0) { //count[i]=0 => i+'a' does not exist in the string, skip it.
                for (int j = start; j<end; j++) {
                    if (str[j] == i+'a') {
                        int left = helper(str, start, j, k);
                        int right = helper(str, j+1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start;
    }

    public static int longestSubstringBf(String s, int k) {
        if (s == null || s.isEmpty() || k > s.length()) {
            return 0;
        }
        int[] countMap = new int[26];
        int n = s.length();
        int result = 0;
        for (int start = 0; start < n; start++) {
            // reset the count map
            Arrays.fill(countMap, 0);
            for (int end = start; end < n; end++) {
                countMap[s.charAt(end) - 'a']++;
                if (isValid(s, start, end, k, countMap)) {
                    result = Math.max(result, end - start + 1);
                }
            }
        }
        return result;
    }

    private static boolean isValid(String s, int start, int end, int k, int[] countMap) {
        int countLetters = 0, countAtLeastK = 0;
        for (int freq : countMap) {
            if (freq > 0) countLetters++;
            if (freq >= k) countAtLeastK++;
        }
        return countAtLeastK == countLetters;
    }
}
