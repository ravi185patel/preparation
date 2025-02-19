package career.sixmonthssep.dp.onedimensional_fibonaccistyle;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/problems/minimal-cost/1
https://www.naukri.com/code360/problems/minimal-cost_8180930

There is an array of heights corresponding to 'n' stones. You have to reach from stone 1 to stone ‘n’.



From stone 'i', it is possible to reach stones 'i'+1, ‘i’+2… ‘i’+'k' , and the cost incurred will be | Height[i]-Height[j] |, where 'j' is the landing stone.



Return the minimum possible total cost incurred in reaching the stone ‘n’.



Example:
For 'n' = 3 , 'k' = 1, height = {2, 5, 2}.

Answer is 6.

Initially, we are present at stone 1 having height 2.
We can only reach stone 2 as ‘k’ is 1. So, cost incurred is |2-5| = 3.
Now, we are present at stone 2, we can only reach stone 3 as ‘k’ is 1.
So, cost incurred is |5-2| = 3. So, the total cost is 6.


 */
public class FrogJumpII {
    public static void main(String[] args) {
//        int n = 4, heights[] = {10, 40, 30, 10}, k = 2; // 40
        int n = 5, heights[] = {10, 40, 50, 20,60}, k = 3; // 50
//        int n=3,heights[]={10,50,10};
//        int n=8,heights[]={7,4,4,2,6,6,3,4};
//        int n=6,heights[]={4,8,3,10,4,4};
//        int n=9,heights[]={7,5,5,8,4,9,1,1,10};

        //    Arrays.fill(memo,-1);
        //    return frogJumpRecMemo(n-1,heights,memo);
        System.out.println(frogJumpRec(k, n-1, heights));
        System.out.println(frogJumpRecDp(k, n-1, heights));
    }


    /*
    when asked for you can pick any from 1 to k which means parent has k child and you have to pick any
    one of them
    when i == 1 or 2 which means you can pick 1 or 2 but not 3 so picked and notPicked work
    without for loop. (you can also do using for loop by run loop i=1 to <=2)
     */
    public static int frogJumpRec(int k, int n, int arr[]) {
        if(n==0){
            return 0;
        }
        int minCost = Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            if(n-i >= 0) { //
                minCost = Math.min(minCost, frogJumpRec(k, n - i, arr) + Math.abs(arr[n - i] - arr[n]));
            }
        }
        return minCost;
    }

    public static int frogJumpRecDp(int k, int n, int arr[]) {
        int dp[]=new int[n+1];

        for(int j=1;j<=n;j++) {
            int minCost = Integer.MAX_VALUE;
            for (int i = 1; i <= k; i++) {
                if (j - i >= 0) { //
                    minCost = Math.min(minCost,
                                dp[j-i] + Math.abs(arr[j] - arr[j-i]));
                }
            }
            dp[j]=minCost;
            System.out.println(Arrays.toString(dp));
        }
        return dp[n];
    }
}