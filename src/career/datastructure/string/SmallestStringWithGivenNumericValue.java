package career.datastructure.string;

public class SmallestStringWithGivenNumericValue {
    public static void main(String[] args) {
         String res = getSmallestString(5,73);
         System.out.println(res);
    }
    public static String getSmallestString(int n, int k) {
        char chArr[]=new char[n];
        k-=n;
        for(int i=n-1;i>=0;i--){
            if(k >= 25 ){
                chArr[i]='z';
                k-=25;
            }else{
                chArr[i] = (char)('a'+k);
                k=0;
            }
        }
        return new String(chArr);
    }
}
