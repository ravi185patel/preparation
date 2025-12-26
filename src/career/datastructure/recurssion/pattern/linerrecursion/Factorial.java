package career.datastructure.recurssion.pattern.linerrecursion;

public class Factorial {
    public static void main(String[] args) {

    }
    public static int fib(int n){
        if(n==0) return 1;
        return n*fib(n-1);
    }
}
