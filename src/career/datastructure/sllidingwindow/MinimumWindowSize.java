package career.datastructure.sllidingwindow;

public class MinimumWindowSize {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        String res = minWindow(s,t);
        System.out.println(res);
    }

    public static String minWindow(String s, String t) {
        int freqT[] = new int[26];
        int freqS[] = new int[26];

        for(char c:t.toCharArray()){
            freqT[c-'A']++;
        }
        int start=0;
        int length = t.length();
        int minWinSize =Integer.MAX_VALUE;
        int startI=0;
        String res="";
        for(int end=0;end<s.length();end++){
            int indexE = s.charAt(end)-'A';
            if(freqT[indexE] > 0){
                length--;
            }
            freqT[indexE]--;
            while(length == 0){
                int currWinSize = end-start+1;
                if(minWinSize > currWinSize){
                    minWinSize = currWinSize;
                    startI=start;
                }
                int indexS = s.charAt(start)-'A';
                freqT[indexS]++;
                if(freqT[indexS] > 0){
                    length++;
                }
                start++;
            }
        }

        return minWinSize == Integer.MAX_VALUE ? "": s.substring(startI,startI+minWinSize);
    }

}
