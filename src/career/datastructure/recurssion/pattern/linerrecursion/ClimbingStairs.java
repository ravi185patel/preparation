package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
    }
    public static  int climbStairs(int n) {
/*//        return climbStairsRec(n);
        int memo[]=new int[n+1];
        Arrays.fill(memo,-1);
//        return climbStairsRecMemo(n,memo);
        return climbStairsDp(n,memo);*/

        return climbStairsNormal(n,new int[3]);
    }

    public static int climbStairsRec(int n){
        if(n < 0 ) return 0;
        if(n == 0) return 1;
        return climbStairs(n-1)+climbStairs(n-2);
    }
    public static int climbStairsRecMemo(int n,int memo[]){
        if(n < 0 ) return 0;
        if(n == 0) return 1;
        if(memo[n]!=-1){
            return memo[n];
        }
        return memo[n] = climbStairs(n-1)+climbStairs(n-2);
    }

    public static int climbStairsDp(int n,int memo[]){
        Arrays.fill(memo,0);
        memo[0]=1;
        memo[1]=1;
        for(int i=2;i<=n;i++){
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }

    public static int climbStairsNormal(int n,int memo[]){
        int climb1 = 0, climb2 = 1;
        for (int i = 0; i <= n; i++) {
            int curr = climb1 + climb2;
            climb1 = climb2;
            climb2 = curr;
        }
        return climb1;
    }
}
