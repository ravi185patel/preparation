package career.datastructure.dp.dponsequence;

import java.util.Arrays;

public class PartitionTwoSubSetWithMinAbsSumDiff {
    public static void main(String[] args) {
        System.out.println(subsetSumUtil(new int[]{2, 3, 3, 3, 4, 5}));
        System.out.println(subsetSumUtil(new int[]{1, 2, 3, 5}));
        System.out.println(subsetSumUtil(new int[]{1, 2, 3, 4}));
        System.out.println(subsetSumUtil(new int[]{8,6,5}));
    }

    public static int subsetSumUtil(int[] arr) {

//        return solve(0,target,arr);
//        return solveDp(target,arr);
        int sum = Arrays.stream(arr).sum();
        return solve(arr.length-1,0,sum,arr);
    }

    public static int solve(int index,int total,int target,int[]arr){
        if(index < 0){
            return Math.abs(total*2-target);
        }

        int noTake = solve(index-1,total,target,arr);
        int take = solve(index-1,total+arr[index],target,arr);
        return Math.min(noTake,take);
    }

    public static int solveDp(int target,int arr[]){
        int n =arr.length;
        int dp[][]=new int[n+1][target+1];

//        for (int i = 0; i < n; i++) {
//            dp[i][0] = true;
//        }
//
//        // Base case: If first element <= k, mark dp[0][arr[0]] true
//        if (arr[0] <= target) {
//            dp[0][arr[0]] = true;
//        }
//
//        for(int i=1;i<n;i++){
//            for(int sum=0;sum<=target;sum++){
//                boolean noTake = dp[i-1][sum];
//                boolean take = false;
//                if(arr[i]<=sum){
//                    take = dp[i-1][sum-arr[i]];
//                }
//                dp[i][sum]=noTake || take;
//            }
//        }
//        return dp[n-1][target];
        return 0;
    }


    public static boolean solveDpOpt(int target,int arr[]){
        int n =arr.length;
        boolean prev[]=new boolean[target+1];

        prev[0]=true;

        // Base case: If first element <= k, mark dp[0][arr[0]] true
        if (arr[0] <= target) {
            prev[arr[0]] = true;
        }

        for(int i=1;i<n;i++){
            boolean curr[]=new boolean[target+1];
            for(int sum=0;sum<=target;sum++){
                boolean noTake = prev[sum];
                boolean take = false;
                if(arr[i]<=sum){
                    take = prev[sum-arr[i]];
                }
                curr[sum]=noTake || take;
            }
            prev=curr.clone();
        }
        return prev[target];
    }
}
