package career.datastructure.dp.dponlongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestDivisibleSubset {
    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{1,2,3}));
        System.out.println(largestDivisibleSubset(new int[]{1,2,4,8}));
        System.out.println(largestDivisibleSubset(new int[]{3,4,16,8}));
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {

//        return ldsDp(nums);
        return lis(nums);
    }

    private static List<Integer> lis(int[]nums){
        Arrays.sort(nums)    ;
        int parent[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            parent[i]=i;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int index = 1; index < nums.length; index++) {
            for (int prevInd = 0; prevInd < index; prevInd++) {
                if ((nums[index] % nums[prevInd] == 0
                        || nums[prevInd] % nums[index] == 0) && dp[prevInd] + 1 > dp[index]) {
                    dp[index] = dp[prevInd] + 1;
                    parent[index] = prevInd;
                }
            }
        }

        int ans = -1;
        int lastIndex =-1;

        for(int i=0; i<nums.length; i++){
            if(dp[i] > ans){
                ans = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(nums[lastIndex]);

        while(parent[lastIndex] != lastIndex){ // till not reach the initialization value
            lastIndex = parent[lastIndex];
            temp.add(0,nums[lastIndex]);
        }
        return temp;
    }
    public static List<Integer> ldsDp(int []nums){
        int n = nums.length;
        int parent[] = new int[n+1];
        int dp[]=new int[n+1];
        Arrays.fill(parent,-1);

        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(dp[i] < dp[j] + 1 && (nums[i]%nums[j] == 0 || nums[j]%nums[i] == 0)){
//                System.out.println(nums[i]+" <> "+nums[j]);
                    dp[i] = dp[j] + 1;
                    parent[i]=j;
                }
            }
        }

//        System.out.print(Arrays.toString(parent));
        int maxLength=0,maxIndex=0;
        for(int i=0;i<n;i++){
            if(dp[i] > maxLength){
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        List<Integer> lst = new ArrayList<>();
        int index = maxIndex;
        while(index != -1){
            lst.add(nums[index]);
            index = parent[index];
        }
        Collections.reverse(lst);
        return lst;
    }
}
