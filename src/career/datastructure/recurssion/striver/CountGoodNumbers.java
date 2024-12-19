package career.datastructure.recurssion.striver;

public class CountGoodNumbers {

    static int MODE = 1000000007;
    public static void main(String[] args) {
        System.out.println(countGoodNumbers(1)); // 20;
        System.out.println(countGoodNumbers(4)); // 400;
        System.out.println(countGoodNumbers(50));//564908303
    }

    private static int countGoodNumbers(long n) {
        long odd = n/2;
        long even = (n+1)/2;

        return (int)((pow(5,even) * pow(4,odd)) %MODE);
    }

//    private static long pow(long no,long times){
//        if(times == 0){
//            return no;
//        }
//        long temp = pow(no,times/2);
//        if(times%2 == 0){
//            return (temp * temp) % MODE;
//        }
//        else{
//            return (no * times * times) %MODE;
//        }
//    }

    public static long pow(long x, long n){

        if(n==0)
            return 1;
        long temp = pow(x,n/2);
        if(n%2==0){
            return (temp * temp)% MODE;
        }
        else{
            return (x * temp * temp)% MODE;
        }
    }
}
