package career.datastructure.sllidingwindow;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB",2));
        System.out.println(characterReplacement("AABABBA",1));
    }
    public static int characterReplacement(String s, int k) {

        int freq[]=new int[126];
        int maxFreq=0,left=0,result=0;
        for(int right=0;right<s.length();right++){
            char c= s.charAt(right);
            freq[c-'A']++;
            maxFreq = Math.max(maxFreq,freq[c-'A']);
            // lets validate window -> length - maxFreq = total distinct character > k which means you need more then k op to make it
            while((right-left + 1) - maxFreq >k ){
                char c1 = s.charAt(left);
                freq[c1-'A']--;
                left++; // why we are not set maxFreq again:- as you are just moving point no need to maintain maxFreq again, as it is compute before while loop
            }
            result = Math.max(result,right-left + 1);
        }
        return result;
    }

    public static int characterReplacement(String s, int k,char c) {
        return -1;
    }
}
