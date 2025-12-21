package career.datastructure.hasing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MinimumNumberPushesTypeWordI {
    public static void main(String[] args) {

    }
    public int minimumPushes(String word) {
        Map<Integer,Integer> map = new HashMap<>();
        int totalType  = 0;
        int index=2;
        for(char c:word.toCharArray()){
            if(index > 9 ){
                index=2;
            }
            map.put(index,map.getOrDefault(index,0)+1);
            totalType += map.get(index);
            index++;
        }
        System.out.println(map);
        // for(int key:map.keySet()){
        //     totalType += map.get(key);
        // }
        return totalType;
    }

    public int minimumPushes1(String word) {
        int totalType=0;
        for(int i=0;i<word.length();i++){
            totalType+= (i/8)+1;
        }

        return totalType;
    }
    public int minimumPushesGeneric(String word) {
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
