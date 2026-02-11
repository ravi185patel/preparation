package career.datastructure.dp.mcmandpartitiondp;

import java.util.Arrays;

public class BurstBalloons {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3,1,5,8}));
        System.out.println(maxCoins(new int[]{1,5}));
    }
    public static int maxCoins(int[] nums) {
        int length = nums.length;
        int temp[]=new int[length+2];
        temp[0]=1;
        temp[temp.length-1]=1;
        for(int i=1;i<=length;i++){
            temp[i] = nums[i-1];
        }
//        System.out.println(Arrays.toString(temp));
        return solve(1,length,temp);

    }

    public static int solve(int i,int j,int nums[]){
        if(i > j) return 0;

        int max=Integer.MIN_VALUE;
        for(int k=i;k<=j;k++){
            int coins = nums[i-1] * nums[k] * nums[j+1]
                    + solve(i,k-1,nums) + solve(k+1,j,nums);
            max = Math.max(max,coins);
        }

        return max;
    }
}
