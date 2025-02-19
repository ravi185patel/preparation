package career.sixmonthssep.dp.onedimensional_fibonaccistyle;

import java.util.Arrays;

/*
https://leetcode.com/problems/climbing-stairs/?envType=study-plan-v2&envId=dynamic-programming

Each time you can either climb 1 or 2 steps.
In how many distinct ways can you climb to the top?

n= 2
1-1
2
2 ways

Why recursion
1] because here you have two choice either you take 1 step or 2 steps at each point
whenever you face choices then it is recursion.
statement : find out distinct ways/ max/min result from different ways etc

3
1-1-1
1-2
2-1

time complexity = TC(2^n)
why ? because each step you have two choice which means you are creating binary tree where each parent has two or nodes.
so time complexity is 2^n


can you improve it
By using dp you can improve it by store previous calculate value
which means you are puring branches of tree by using previous value.

TC = O(N) because you are visiting same node only one time.

before jump on dp convert recursion into memo
by using memo array and passed in function


when you find overlapping go with dp.
overlapping happen only in recursion.

 */
public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climb(2));
        System.out.println(climb(3));
        System.out.println(climb(45));

        int memo[]=new int[45+1];
        Arrays.fill(memo,-1);
        System.out.println(climb(45,memo));
        System.out.println(climbDp(45));
        System.out.println(climbDpOp(45));
    }

    private static int climb(int index){
        if(index  == 0 || index == 1){
            return 1;
        }
        return climb(index-1)+climb(index-2);
    }

    private static int climb(int index,int memo[]){
        if(index  == 0 || index == 1){
            return 1;
        }
        if(memo[index]!= -1){
            return memo[index];
        }
        return memo[index] = climb(index-1,memo)+climb(index-2,memo);
    }


    private static int climbDp(int n){
        int dp[]=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    private static int climbDpOp(int n){

        int prev1=1;
        int prev=1;
        for(int i=2;i<=n;i++){
            int res = prev1+prev;
            prev1=prev;
            prev = res;
        }
        return prev;
    }
}
