package career.datastructure.recurssion.striver.subsetAndCombinnation;

public class WildcardMatching {
    public static void main(String[] args) {
        String s="aa",p="*";
        System.out.println(isMatch(0,0,s,p));
        System.out.println(isMatch(0,0,"aa","a"));
        System.out.println(isMatch(0,0,"cb","?a"));
    }

    public static boolean isMatch(int i,int j,String s, String p) {
        if(s.length() == i){
            return true;
//            return s.length() == j;
        }

        if(i < s.length() && j < p.length()
            && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
            return isMatch(i+1,j+1,s,p);
        }

        if(j < p.length() && p.charAt(j) == '*'){
            boolean noTake = isMatch(i,j+1,s,p);
            boolean take = isMatch(i+1,j,s,p);
            return noTake||take;
        }else{
            return false;
        }
    }
}
