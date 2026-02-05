package career.datastructure.dp.dponlongestIncreasingSubsequence;

import java.util.Arrays;

public class PrintLongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(lic(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lic(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lic(new int[]{7,7,7,7,7,7,7}));
    }

    public static String lic(int nums[]){
//        return licRec(nums.length-1,-1,nums);
        return licDp(nums);
    }

    public static int licRec(int index,int prev,int nums[]){
        if(index < 0){
            return 0;
        }

        int noTake = licRec(index-1,prev,nums);
        int take = Integer.MIN_VALUE;
        if(prev == -1 || prev > nums[index]){
            take = 1 + licRec(index-1,nums[index],nums);
        }
        return Math.max(noTake,take);
    }

    public static String licDp(int nums[]){
        int n = nums.length;
        int dp[]=new int[n+1];
        int parent[]=new int[n+1];
//        for(int i=0;i<n;i++){
//            parent[i]=i;
//        }
        Arrays.fill(parent,-1);
        Arrays.fill(dp,1);
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j] && dp[j] + 1 > dp[i]){
                    dp[i]=dp[j]+1;
                    parent[i]=j;
                }
            }
        }

        System.out.println(Arrays.toString(parent));
        int maxLen = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int index=maxIndex;
        while(index != -1){
            sb.append(nums[index]).append(",");
            index = parent[index];
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.reverse().toString();

    }

}
