package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("101023"));
//        System.out.println(isValidIPAddress("1.0.1.0.23"));
    }
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
//        solve(s,0,3,new String(),res);
        solveItr(s,0,3,new String(),res);
        return res;
    }

    public static void solve(String s,int index,int noOfDots,String newS,List<String> res){
        System.out.println(s+" "+index);
        if(noOfDots ==0 ){
            if(isValidIPAddress(s)){
                res.add(s);
            }
            return;
        }
        if(index > s.length() || noOfDots < 0) return;

        for(int i=index;i<s.length();i++){
            String firstPart = s.substring(0,i+1);
            String secondPart = s.substring(i+1);
            String newString = firstPart+"."+secondPart;

            solve(newString,i+2,noOfDots-1,newS,res);
        }

    }

    public static void solveItr(String s,int index,int noOfDots,String newS,List<String> res){

        if (s.length() > 12) return;

        for (int seg1 = 1; seg1 < 4; seg1++) {
            for (int seg2 = 1; seg2 < 4; seg2++) {
                for (int seg3 = 1; seg3 < 4; seg3++) {
                    for (int seg4 = 1; seg4 < 4; seg4++) {
                        if (seg1 + seg2 + seg3 + seg4 != s.length()) continue;

                        String num1 = s.substring(0, seg1);
                        String num2 = s.substring(seg1, seg1 + seg2);
                        String num3 = s.substring(seg1 + seg2, seg1 + seg2 + seg3);
                        String num4 = s.substring(seg1 + seg2 + seg3);

                        if (isValid(num1) && isValid(num2) && isValid(num3) && isValid(num4)) {
                            res.add(num1 + "." + num2 + "." + num3 + "." + num4);
                        }
                    }
                }
            }
        }
    }

    private static boolean isValid(String num) {
        if (num.length() > 1 && num.charAt(0) == '0') return false;
        int value = Integer.parseInt(num);
        return value <= 255;
    }

    public static boolean isValidIPAddress(String ipAddress){
        String slots[]=ipAddress.split("\\.");
        if(slots.length != 4 )return false;
        for(String slot:slots){
            int no = Integer.parseInt(slot);
            if((slot.length() > 1 && slot.charAt(0)=='0') ||  0 > no || no > 255 ){
                return false;
            }
        }
        return true;
    }
}
