package career.datastructure.dp.dponsequence;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        System.out.println(subsetSumUtil(new int[]{2, 3, 3, 3, 4, 5}));
        System.out.println(subsetSumUtil(new int[]{1, 2, 3, 5}));
    }

    public static boolean subsetSumUtil(int[] arr) {

//        return solve(0,target,arr);
//        return solveDp(target,arr);
        int sum = Arrays.stream(arr).sum();
        if(sum%2 != 0){
            return false;
        }
        return solveDpOpt(sum/2,arr);
    }

    public static boolean solve1(int index,int target,int[]arr){
        if(index == arr.length){
            System.out.println(target);
            return target==0;
        }

        if(target < 0) return false;

        for(int i=index;i<arr.length;i++){
            if(arr[i] <= target){
                if(solve(i+1,target-arr[i],arr)){
                    return true;
                }
            }
        }

        return false;

    }


    public static boolean solve(int index,int target,int[]arr){
        if(index == arr.length){
            return target==0;
        }

        if(target < 0) return false;


        boolean noTake = solve(index+1,target,arr);
        boolean take = false;
        if(arr[index] <= target){
            take = solve(index+1,target-arr[index],arr);
        }
        return noTake || take;
    }


    public static boolean solveDp(int target,int arr[]){
        int n =arr.length;
        boolean dp[][]=new boolean[n+1][target+1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // Base case: If first element <= k, mark dp[0][arr[0]] true
        if (arr[0] <= target) {
            dp[0][arr[0]] = true;
        }

        for(int i=1;i<n;i++){
            for(int sum=0;sum<=target;sum++){
                boolean noTake = dp[i-1][sum];
                boolean take = false;
                if(arr[i]<=sum){
                    take = dp[i-1][sum-arr[i]];
                }
                dp[i][sum]=noTake || take;
            }
        }
        return dp[n-1][target];
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
