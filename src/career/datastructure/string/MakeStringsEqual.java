package career.datastructure.string;

public class MakeStringsEqual {
    public static void main(String[] args) {
        String s = "101110100", target = "110011000";
        System.out.println(makeStringsEqual(s,target));
    }

    public static boolean makeStringsEqual(String s, String target) {
        return s.contains("1") == target.contains("1");
    }

    private static boolean helper(char c[],int start,String target){
        if(start == c.length){
            return target.equals(new String(c));
        }

        boolean isMatched = false;
        for(int i = start;i<c.length;i++){
            if(start != i) {
                swap(c, start, i);
                isMatched = isMatched || helper(c, start + 1, target);
                swap(c, start, i);
            }
        }
        return isMatched;
    }

    public static void swap(char c[],int start,int end){
        char temp = c[start];
        c[start] = c[end];
        c[end] = temp;
    }
}
