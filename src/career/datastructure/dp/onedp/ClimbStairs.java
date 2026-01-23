package career.datastructure.dp.onedp;

public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
    }
    public static int climbStairs(int n) {
//        return climbStairsDp(n);
        return climbStairsItr(n);
    }

    public static int climbStairsRec(int n) {
        if(n == 0 || n == 1) return 1;
        return climbStairsRec(n-1)+climbStairsRec(n-2);
    }

    public static int climbStairsDp(int n){
        int dp[] = new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<=n;i++){

            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static int climbStairsItr(int n){
        int prev1=1,prev2=1,curr=0;
        for(int i=2;i<=n;i++){
            curr = prev1+prev2;
            prev2 = prev1;
            prev1=curr;

        }
        return prev1;
    }
}
