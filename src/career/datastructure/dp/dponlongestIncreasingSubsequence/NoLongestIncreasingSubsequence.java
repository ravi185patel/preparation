package career.datastructure.dp.dponlongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NoLongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(lic(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lic(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lic(new int[]{7,7,7,7,7,7,7}));
        System.out.println(lic(new int[]{1,3,6,7,9,4,10,5,6}));
    }

    public static int lic(int nums[]){
//        return licRec(nums.length-1,-1,nums);
        return licDp(nums);
//        return licBs(nums);
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

    public static int licDp(int nums[]){
        int n = nums.length;
        int dp[]=new int[n+1];
        int cnt[]=new int[n+1];

        Arrays.fill(dp,1);
        Arrays.fill(cnt,1);
        int maxi=1;
        for(int index=1;index<nums.length;index++){
            for(int prevInd =0;prevInd < index;prevInd++){
                if(nums[index] > nums[prevInd] && dp[prevInd]+1 > dp[index]){
                    dp[index] = dp[prevInd] + 1;
                    cnt[index] = cnt[prevInd];
                }else if(nums[index] > nums[prevInd] && dp[prevInd]+1 == dp[index]){
                    cnt[index] = cnt[prevInd]+cnt[index];
                }
            }
            maxi = Math.max(maxi,dp[index]);
        }

        int totalCnt=0;
        for(int i=0;i<nums.length;i++){
            if(maxi == dp[i]){
                totalCnt +=cnt[i];
            }
        }
        return totalCnt;
    }

    public static int licBs(int nums[]) {
        int n = nums.length;
        List<Integer> temp = new ArrayList<>();
        temp.add(nums[0]);
        for (int i = 1; i < n; i++) {

            // If the current element can be added to the subsequence
            if (nums[i] > temp.get(temp.size() - 1))
                temp.add(nums[i]);

                // Otherwise
            else {
                // Index at which the current element must be placed
                int ind = Collections.binarySearch(temp, nums[i]);
                if (ind < 0) ind = -(ind + 1);

                // Place the current element in the subsequence
                temp.set(ind, nums[i]);
            }
        }

        // Return the length of temporary subsequence created so far
        return temp.size();
    }
}
