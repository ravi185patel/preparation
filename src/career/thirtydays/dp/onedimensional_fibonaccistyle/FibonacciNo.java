package career.thirtydays.dp.onedimensional_fibonaccistyle;

public class FibonacciNo {
    public static void main(String[] args) {
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
    }

    public static int fib(int n) {
        int prev1 = 0;
        int prev = 1;
        for(int i=2;i<=n;i++){
            int curr = prev1 + prev;
            prev1 = prev;
            prev = curr;
        }

        return prev;
    }
}
