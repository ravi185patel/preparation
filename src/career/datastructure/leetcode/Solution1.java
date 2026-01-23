package career.datastructure.leetcode;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println(vowelConsonantScore("cooear"));
        System.out.println(vowelConsonantScore("axeyizou"));
        System.out.println(vowelConsonantScore("au 123"));
        System.out.println(vowelConsonantScore("123"));
        System.out.println(vowelConsonantScore("aue"));
    }
    public static int vowelConsonantScore(String s) {
        int vowel=0,consonants =0;
        for(char c:s.toCharArray())   {
            if(c=='a' ||c=='e' ||c=='i' ||c=='o'||c== 'u'){
                vowel++;
            }else if(!(Character.isSpaceChar(c) || Character.isDigit(c))){
                consonants ++;
            }
        }

        return consonants == 0 ? 0: (int)Math.floor(vowel/consonants );
    }
}
