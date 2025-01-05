package career.datastructure.swapline;

import java.util.Arrays;

public class ShiftLettersII {
    public static void main(String[] args) {
//        String s = "abc"; int shifts[][] = {{0,1,0}, {1,2,1}, {0,2,1}};
        String s = "dztz"; int shifts[][] = {{0,0,0}, {1,1,1}};
//        String s = "abc"; int shifts[][] = {{0,0,0}, {1,1,1}};
        System.out.println(shiftingLetters(s,shifts));
    }

    public static String shiftingLetters(String s, int[][] shifts) {
//        return shiftingLettersBF(s,shifts);
        return shiftingLettersSL(s,shifts);
    }

    private static String shiftingLettersBF(String s,int[][]shifts){
        char chArr[]= s.toCharArray();
        for(int shift[]:shifts){
            shiftsCharacterInRange(chArr,shift[0],shift[1],shift[2]);
        }
        return new String(chArr);
    }

    private static void shiftsCharacterInRange(char chArr[],int start,int end,int dir){

        for(int i=start;i<=end;i++){
            if(dir == 1){
                if(chArr[i] == 'z'){
                    chArr[i] = 'a';
                }else{
                    chArr[i]++;
                }
            }else{
                if(chArr[i] == 'a'){
                    chArr[i] = 'z';
                }else{
                    chArr[i]--;
                }
            }
        }
    }

    private static String shiftingLettersSL(String s,int[][]shifts){
        char chArr[]= s.toCharArray();
        int length = chArr.length;
        int temp[]=new int[length+1];
        for(int shift[]:shifts){
            int start = shift[0];
            int end = shift[1]+1;
            int sign = 1;
            if(shift[2] == 0){
                sign = -1;
            }
            temp[start]+=sign;
            temp[end]-=sign;
        }

        for(int i=1;i<temp.length;i++){
            temp[i]=temp[i]+ temp[i-1];
        }

        System.out.println(Arrays.toString(temp));

        StringBuilder result = new StringBuilder(s);
        for(int i=0;i<chArr.length;i++){
            int netShift = (temp[i] % 26 + 26) % 26;
            result.setCharAt(i, (char) ('a' + (s.charAt(i) - 'a' + netShift) % 26));
        }
//        Formula = s[i] = 'a' + (s[i] - 'a' + netShift) % 26; netShift = number of total shift at index i
        return result.toString();
    }

}
