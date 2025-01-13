package career.thirtydays.dp.subsequence;

import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change/description/?envType=study-plan-v2&envId=dynamic-programming

You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

 */
public class CoinChangeI {
    public static void main(String[] args) {
//        int coins[] = {1,2,5}, amount = 11;
//        int coins[] = {2}, amount = 3;
//        int coins[] = {1}, amount = 0;
        int coins[] = {186,419,83,408}, amount = 6249;
        int res = 0;//coinChangeRec(coins,0,amount);
        System.out.println( res == (int)1e9 ? -1 : res);

        res= dfs(coins,0,amount);
        System.out.println( res == (int)1e9 ? -1 : res);

        res= dfsDp(coins,0,amount);
        System.out.println( res == (int)1e9 ? -1 : res);

        res= dfsDpOp(coins,0,amount);
        System.out.println( res == (int)1e9 ? -1 : res);
    }

    // why 1e9 work not max value;
    public static int coinChangeRec(int[] coins,int index, int amount) {
        if(index == coins.length-1){
            return amount%coins[index] == 0 ? amount/coins[index]:(int)1e9;
        }

        int noTaken = coinChangeRec(coins,index+1,amount);

        int taken = (int)1e9;
        if(amount >= coins[index]){
            taken = 1 + coinChangeRec(coins,index,amount - coins[index]);
        }
        return Math.min(noTaken,taken);
    }


    public static int dfs(int[] coins,int index, int amount) {
        if(index == coins.length-1){
            return amount%coins[index] == 0 ? amount/coins[index]:(int)1e9;
        }

        int res = (int)1e9;
        for(int i=index;i<coins.length;i++) {
            if (amount >= coins[i]) {
                res = Math.min(res,1 + dfs(coins, i, amount - coins[i]));
            }
        }
        return res;
    }


    public static int dfsDp(int[] coins,int index, int amount) {
        int n= coins.length;
        int dp[][]=new int[n+1][amount+1];

        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[0][i] = i / coins[0];
            else
                dp[0][i] = (int) Math.pow(10, 9);
        }

        for(int i=1;i<coins.length;i++){
            for(int target = 0;target<=amount;target++){
                int noTake = dp[i-1][target];
                int take = (int)(1e9);
                if(target >= coins[i]){
                    take = 1 + dp[i][target-coins[i]];
                }
                dp[i][target] = Math.min(take,noTake);
            }
        }
        return dp[coins.length-1][amount];
    }


    public static int dfsDpOp(int[] coins,int index, int amount) {
        int n= coins.length;
        int prev[] = new int[amount+1];

        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                prev[i] = i / coins[0];
            else
                prev[i] = (int) Math.pow(10, 9);
        }

        for(int i=1;i<coins.length;i++){
            int curr[] = new int[amount+1];
            for(int target = 0;target<=amount;target++){
                int noTake = prev[target];
                int take = (int)(1e9);
                if(target >= coins[i]){
                    take = 1 + curr[target-coins[i]];
                }
                curr[target] = Math.min(take,noTake);
            }
            prev = curr.clone();
        }
        return prev[amount];
    }




    public static int coinChangeDpOP(int[] coins, int amount) {
        int n= coins.length;
        int dp[] = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for(int target = 1;target<=amount;target++){
            for(int i=0;i<coins.length;i++){
                if(target >= coins[i]){
                    dp[target] = Math.min(dp[target],dp[target-coins[i]]+1);
                }
            }
        }
        return dp[amount] > amount ? (int)1e9:dp[amount];
    }

}
