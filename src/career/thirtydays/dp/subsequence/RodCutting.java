package career.thirtydays.dp.subsequence;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/problems/rod-cutting0840/1
Given a rod of length n(size of price) inches and an array of prices, price.
price[i] denotes the value of a piece of length i.
Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 */
public class RodCutting {
    public static void main(String[] args) {
        int price[] = {1, 5, 8, 9, 10, 17, 17, 20}; //22
//        int price[] = {1, 10, 3, 1, 3, 1, 5, 9}; //40
//        int price[] = {3, 5, 8, 9, 10, 17, 17, 20}; //24

        System.out.println(cutRodRec(price,price.length,price.length-1));
        System.out.println(cutRodRecDp(price,price.length));
        System.out.println(cutRodRecDpSingle(price,price.length));
    }
    public static int cutRodRec(int[] price,int N,int index) {

        if(index == 0){
            return N * price[0];
        }

        int noTake = cutRodRec(price, N, index - 1);
        int take = Integer.MIN_VALUE;
        int rodLength = index+1;
        if(rodLength <= N){
            take = price[index]+cutRodRec(price,N-rodLength,index);
        }
        return Math.max(noTake,take);
    }

    public static int cutRodRecDp(int[] price,int N) {

        int prev[] = new int[N+1];

        for(int i=0;i<=N;i++){
            prev[i] = price[0]*i;
        }
        for(int i=1;i<N;i++){
            int curr[]=new int[N+1];
            for(int j=0;j<=N;j++){
                int noTake = prev[j];
                int take = Integer.MIN_VALUE;
                int rodLength = i+1;
                if(rodLength <= j){
                    take = price[i]+curr[j-rodLength];
                }
                curr[j]=Math.max(noTake,take);
            }
            prev = curr.clone();
        }
        return prev[N];
    }

    public static int cutRodRecDpSingle(int[] price,int N) {

        int prev[] = new int[N+1];

        for(int i=0;i<=N;i++){
            prev[i] = price[0]*i;
        }
        for(int i=1;i<N;i++){
            for(int j=0;j<=N;j++){
                int noTake = prev[j];
                int take = Integer.MIN_VALUE;
                int rodLength = i+1;
                if(rodLength <= j){
                    take = price[i]+prev[j-rodLength];
                }
                prev[j]=Math.max(noTake,take);
            }
        }
        return prev[N];
    }

    public static int dfs(int val[],int wt[],int capacity,int index,int sum){
        if(index == val.length){
            return sum;
        }
        int noTake = dfs(val,wt,capacity,index+1,sum);
        int take = Integer.MIN_VALUE;
        if(wt[index] <= capacity){
            take = dfs(val,wt,capacity-wt[index],index,sum+val[index]);
        }

        return Math.max(noTake,take);
    }


}
