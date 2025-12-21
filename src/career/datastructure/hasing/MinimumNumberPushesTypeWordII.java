package career.datastructure.hasing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*

 */
public class MinimumNumberPushesTypeWordII {
    public static void main(String[] args) {

    }
    public int minimumPushes(String word) {
        int[] mp = new int[26];

        // Generic solution
        for (char ch : word.toCharArray()) {
            mp[ch - 'a']++; // Mentioned in the question, all letters will be distinct
        }

        // Descending order of frequency
        Integer[] mpInteger = Arrays.stream(mp).boxed().toArray(Integer[]::new);
        Arrays.sort(mpInteger, Comparator.reverseOrder());

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += mpInteger[i] * ((i / 8) + 1); // freq * (times press)
        }
        return ans;
    }

}
