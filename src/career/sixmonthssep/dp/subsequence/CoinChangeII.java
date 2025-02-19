package career.sixmonthssep.dp.subsequence;

/*
https://leetcode.com/problems/coin-change-ii/description/?envType=study-plan-v2&envId=dynamic-programming
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.



Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1
 */
public class CoinChangeII {
    public static void main(String[] args) {
        int amount = 5, coins[] = {1,2,5};
        System.out.println(changeDp(amount,coins));
    }

    public static int changeDp(int amount, int[] coins) {
        int prev[] = new int[amount+1];

        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                prev[i] = 1;
            else
                prev[i] = 0;
        }

        for(int i=1;i<coins.length;i++){
            int curr[] = new int[amount+1];
            for(int target = 0;target<=amount;target++){
                int noTake = prev[target];
                int take = 0;
                if(target >= coins[i]){
                    take = curr[target-coins[i]];
                }
                curr[target] = take + noTake;
            }
            prev = curr.clone();
        }
        return prev[amount];
    }
}
