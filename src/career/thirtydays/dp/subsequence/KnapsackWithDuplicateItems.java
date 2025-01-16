package career.thirtydays.dp.subsequence;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1

The task is to fill the knapsack in such a way that we can get the maximum profit.
Return the maximum profit.

Given a set of items, each with a weight and a value,
represented by the array wt and val respectively.
Also, a knapsack with a weight limit capacity.

Note: Each item can be taken any number of times.

 */
public class KnapsackWithDuplicateItems {
    public static void main(String[] args) {
//        int val[] = {1, 1}, wt[] = {2, 1}, capacity = 3;
        int val[] = {6, 1, 7, 7}, wt[] = {1, 3, 4, 5}, capacity = 8; // 48
        System.out.println(knapSack(val,wt,capacity));
        System.out.println(dfs(val,wt,capacity,0,0));
        System.out.println(dfsStriver(val,wt,capacity,wt.length-1));// please refer this
    }


    public static int dfsStriver(int val[],int wt[],int capacity,int index){
        if(index == 0){
            return (capacity/wt[0])*val[0];
        }
        int noTake = dfsStriver(val,wt,capacity,index-1);
        int take = Integer.MIN_VALUE;
        if(wt[index] <= capacity){
            take = val[index]+dfsStriver(val,wt,capacity-wt[index],index);
        }

        return Math.max(noTake,take);
    }

    public static int dfs(int val[],int wt[],int capacity,int index,int sum){
        if(index == val.length){
            return sum;
        }
        int noTake = dfs(val,wt,capacity,index+1,sum);
        int take = Integer.MIN_VALUE;
        if(wt[index] <= capacity){
            take = dfs(val,wt,capacity-wt[index],index,sum+val[index]);
            // when you pass value in parameter it is very difficulty to write memo or dp of this solution.
        }

        return Math.max(noTake,take);
    }
    public static int knapSack(int val[], int wt[], int capacity) {
        int prev[]=new int[capacity+1];

        // main step
        for(int i=wt[0];i<=capacity;i++){
            prev[i] = (i/wt[0])*val[0];
        }


        for(int i=1;i<wt.length;i++){
            int curr[]=new int[capacity+1];
            for(int weight=0;weight<=capacity;weight++){
                int noTake = prev[weight];
                int take = Integer.MIN_VALUE;
                if(weight >= wt[i]){
                    take = val[i]+curr[weight-wt[i]];
                }
                curr[weight] = Math.max(noTake,take);
            }
            System.out.println(Arrays.toString(prev));
            prev = curr.clone();
        }
        return prev[capacity];
    }
}
