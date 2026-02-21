package career.interview.priceline;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB",2));
        System.out.println(characterReplacement("AABABBA",1));
        System.out.println(characterReplacement("IMNJJTRMJEGMSOLSCCQICIHLQIOGBJAEHQOCRAJQMBIBATGLJDTBNCPIFRDLRIJHRABBJGQAOLIKRLHDRIGERENNMJSDSSMESSTR",2));
    }
    public static int characterReplacement(String s, int k) {
        return sw(s,k);
    }

    public static int sw(String s,int k){
        int[] count = new int[26]; // To store frequency of each character
        int maxFreq = 0, left = 0, result = 0;

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);

            // Check if window is valid
            while ((right - left + 1) - maxFreq > k) { // AABBA -> max = 3 and total = 5-3 = 2 > 1 window is invalid
                count[s.charAt(left) - 'A']--;
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}
