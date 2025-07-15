package career.sixmonthssep.leecode.leetcodecomp;

import java.util.Arrays;

public class P2 {
    public static void main(String[] args) {
        int nums[] = {3,1,6,8,4}, target = 24;
//        int  nums[] = {2,5,3,7}, target = 15;
        System.out.println(checkEqualPartitions(nums,target));
    }
    public static boolean checkEqualPartitions(int[] nums, long target) {
        long mul=1;
        for(int i:nums){
            if(target%i != 0){
                return false;
            }
            mul*=i;
        }

        boolean memo[][]=new boolean[100][100001];

        if(mul%target!=0){
            return false;
        }
        System.out.println(target);
        return findPossible(0,target,nums,memo);
    }



    public static boolean findPossible(int index,long sum,int []nums,boolean[][]memo){
        if(index >= nums.length){
            return sum==1;
        }
        if(sum == 1){
            return true;
        }
        if(memo[index][(int)sum]){
            return memo[index][(int)sum];
        }
        System.out.println(sum);
        boolean noTaken = findPossible(index+1,sum,nums,memo);
        boolean taken = false;
        if(sum != 0 && sum%nums[index] == 0){
            taken = findPossible(index+1,sum/nums[index],nums,memo);
        }

        return memo[index][(int)sum] = taken || noTaken;
    }
}
