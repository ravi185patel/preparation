package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;

public class HouseRobberI {
    public static void main(String[] args) {
        System.out.println(houseRobber(new int[]{1,2,3,1,3}));
        System.out.println(houseRobber(new int[]{1,2,1,1,1}));
    }

    public static int houseRobber(int nums[]){
//        return houseRobberRec(nums,nums.length-1);
//        int n = nums.length;
//        int memo[]=new int[n];
//        Arrays.fill(memo,-1);
//        return houseRobberMemo(nums,nums.length-1,memo);

//           return houseRobberDp(nums,0);
        return houseRobberNormal(nums,0);
    }

    public static int houseRobberRec(int nums[],int index){
        if(index<0){
            return 0;
        }

        int noTake = houseRobberRec(nums,index-1);
        int take = nums[index] + houseRobberRec(nums,index-2);

        return Math.max(take,noTake);

    }

    public static int houseRobberMemo(int nums[],int index,int memo[]){
        if(index<0){
            return 0;
        }

        if(memo[index] != -1){
            return memo[index];
        }

        int noTake = houseRobberMemo(nums,index-1,memo);
        int take = nums[index] + houseRobberMemo(nums,index-2,memo);

        return memo[index] = Math.max(take,noTake);

    }

    public static int houseRobberDp(int nums[],int index){
        int n = nums.length;
        int dp[]=new int[n];
        dp[0]=nums[0];
        dp[1] = Math.max(nums[0],nums[1]); // (I) -> remove for (II) solution
        for(int i=2;i<n;i++){

             dp[i]= Math.max(dp[i-1],nums[i]+dp[i-2]); // (I)

//            int noTake = dp[i-1];  // (II)
//            int take = 0;
//            if(i-2 >= 0 ){
//                take = nums[i]+dp[i-2];
//            }
//            dp[i] = Math.max(noTake,take);
        }
        return dp[n-1];
    }

    public static int houseRobberNormal(int nums[],int index){
        int prev2 = 0;          // dp[i-2]
        int prev1 = 0;          // dp[i-1]

        for (int i = 0; i < nums.length; i++) {
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
