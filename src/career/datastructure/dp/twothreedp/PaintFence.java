package career.datastructure.dp.twothreedp;

import java.util.Arrays;

public class PaintFence {
    public static void main(String[] args) {
        System.out.println(countWays(2));
        System.out.println(countWays(3));
        System.out.println(countWays(4));
        System.out.println(countWays(5));
    }
    public static int countWays(int n) {
        return 2 * solveRec(n-1,2,1);
    }

    public static int solveRec(int n,int k ,int lastCount){
        if (n == 0) return 1;

        int ways = 0;

        // choose same color
        if (lastCount < 2) {
            ways += solveRec(n - 1, k, lastCount + 1);
        }

        // choose different color
        ways += solveRec(n - 1, k, 1);

        return ways;

    }

    static final long MOD = 1_000_000_007;

    public static long solveDp(int n,int k){

        if (n == 1) return k;

        long same = 0;
        long diff = k;

        for (int i = 2; i <= n; i++) {

            long newSame = diff % MOD;

            long newDiff = ((same + diff) % MOD * (k - 1)) % MOD;

            same = newSame;
            diff = newDiff;
        }

        return (same + diff) % MOD;

    }
}
