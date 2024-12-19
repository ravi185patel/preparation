package career.datastructure.recurssion.striver;

public class RegularExpression {
    public static void main(String[] args) {
        String s = "aa",p = "*a";
        System.out.println(solve(0,0,s,p));
    }

    private static boolean solve(int i,int j,String s,String pattern){
        if(pattern.length() == j){
            return s.length() ==i;
        }

        boolean isFirstCharMatched=false;
        if(i < s.length() && j < pattern.length() && (s.charAt(i) == pattern.charAt(j)
                || pattern.charAt(j) == '.') ){
            isFirstCharMatched = true;
        }

        if(j+1 < pattern.length() && pattern.charAt(j+1) == '*'){
            boolean noTake = solve(i,j+2,s,pattern);
            boolean take = isFirstCharMatched && solve(i+1,j,s,pattern);

            return noTake || take;
        }

        return isFirstCharMatched && solve(i+1,j+1,s,pattern);
    }
}
