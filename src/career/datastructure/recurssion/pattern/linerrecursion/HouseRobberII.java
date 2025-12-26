package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

public class HouseRobberII {
    public static void main(String[] args) {
        System.out.println(houseRobberII(new int[]{1,2,3,1,3})); // 5
        System.out.println(houseRobberII(new int[]{1,2,1,1,1})); // 3
        System.out.println(houseRobberII(new int[]{1,2,1,1,4})); // 6
        System.out.println(houseRobberII(new int[]{1,2,1,1})); // 3
    }

    public static int houseRobberII(int nums[]){
//        int left = houseRobberRecII(nums,1,nums.length-1);
//        int right = houseRobberRecII(nums,0,nums.length-2);
//        return Math.max(left,right);
//        int n = nums.length;
//        int memo[]=new int[n];
//        Arrays.fill(memo,-1);
//        int left = houseRobberMemo(nums,1,nums.length-1,memo);
//        int right = houseRobberMemo(nums,0,nums.length-2,memo);
//        return Math.max(left,right);

//          int left =  houseRobberDp(nums,1,nums.length-1);
//          int right = houseRobberDp(nums,0,nums.length-2);
//          return Math.max(left,right);
        int left =  houseRobberNormal(nums,1,nums.length-1);
        int right = houseRobberNormal(nums,0,nums.length-2);
        return Math.max(left,right);
    }

    public static int houseRobberRecII(int nums[],int end,int index){
        if(index<end){
            return 0;
        }

        int noTake = houseRobberRecII(nums,end,index-1);
        int take = nums[index] + houseRobberRecII(nums,end,index-2);

        return Math.max(take,noTake);

    }

    public static int houseRobberMemo(int nums[],int end,int index,int memo[]){
        if(index<end){
            return 0;
        }

        if(memo[index] != -1){
            return memo[index];
        }

        int noTake = houseRobberMemo(nums,end,index-1,memo);
        int take = nums[index] + houseRobberMemo(nums,end,index-2,memo);

        return memo[index] = Math.max(take,noTake);

    }

    public static int houseRobberDp(int nums[],int start,int end){
        int dp[]=new int[end+1];
        dp[start]=nums[start];
        for(int i=start+1;i<=end;i++){
            int noTake = dp[i-1];
            int take = 0;
            if(i-2 >= start ){
                take = nums[i]+dp[i-2];
            }
            dp[i] = Math.max(noTake,take);
        }
        return dp[end];
    }

    public static int houseRobberNormal(int nums[],int start,int end){
        int prev2 = 0;          // dp[i-2]
        int prev1 = 0;          // dp[i-1] // you can assign nums[start]

        for (int i = start; i <= end; i++) { // i = start + 1
            int take = nums[i] + prev2; // prev2 prev1 curr => prev2 + curr | prev1
            int noTake = prev1;
            int cur = Math.max(take, noTake);

            prev2 = prev1;
            prev1 = cur;

//            dp[i-2] = dp[i-1]
//            dp[i-1] = cur
        }
        return prev1; // ans
    }
}
