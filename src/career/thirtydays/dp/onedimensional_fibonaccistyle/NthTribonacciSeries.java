package career.thirtydays.dp.onedimensional_fibonaccistyle;

public class NthTribonacciSeries {
    public static void main(String[] args) {

    }

    public int tribonacci(int n) {
        if(n==0) return n;
        int prev2 = 0;
        int prev1 = 1;
        int prev  = 1;
        for(int i=3;i<=n;i++){
            int curr = prev2 + prev1 + prev;
            prev2 = prev1;
            prev1 = prev;
            prev = curr;
        }

        return prev;
    }
}
