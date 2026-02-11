package career.datastructure.dp.mcmandpartitiondp;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        System.out.println(maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10},3));
        System.out.println(maxSumAfterPartitioning(new int[]{1,4,1,5,7,3,6,1,9,9,3},4));
        System.out.println(maxSumAfterPartitioning(new int[]{1},1));
    }
    public static int maxSumAfterPartitioning(int[] arr, int k) {
//        return solve(0,k,arr);
        return solveDp(arr,k);
    }

    public static int solve(int index,int k,int arr[]){
        int length = arr.length;
        if(index == length){
            return 0;
        }

        int maxEle=0;
        int maxSum=0;
        for(int i=1;i<=k && index+i<=length;i++){
            maxEle = Math.max(maxEle,arr[index+i-1]);
            int currentSum=maxEle*i+solve(index+i,k,arr);
            maxSum = Math.max(maxSum,currentSum);
        }

        return maxSum;
    }

    public static int solveDp(int[] arr, int k) {
        int length = arr.length;
        int dp[]=new int[k];

        for(int i=1;i<=length;i++){
            int curMax =0,best =0;
            for(int j=1;j<=k && i-j>=0;++j){
                curMax = Math.max(curMax,arr[i-j]);
                best = Math.max(best,dp[(i-j)%k]+curMax*j);
            }
            dp[i%k] = best;
        }

        return dp[length % k];
    }

}
