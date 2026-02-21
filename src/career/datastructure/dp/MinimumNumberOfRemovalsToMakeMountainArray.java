package career.datastructure.dp;

import java.util.Arrays;

public class MinimumNumberOfRemovalsToMakeMountainArray {
    public static void main(String[] args) {
        System.out.println(minimumMountainRemovals(new int[]{2,1,1,5,6,2,3,1}));
        System.out.println(minimumMountainRemovals(new int[]{1,3,1}));
        System.out.println(minimumMountainRemovals(new int[]{4,3,2,1,1,2,3,1}));
        System.out.println(minimumMountainRemovals(new int[]{9,8,1,7,6,5,4,3,2,1}));
        System.out.println(minimumMountainRemovals(new int[]{1,2,3,4,4,3,2,1}));
    }
    static int count=0;
    public static int minimumMountainRemovals(int[] nums) {
//        int maxMountain = solveRec(0, -1, 0, nums);
        int maxMountain = solveDp(nums);
        return  nums.length - maxMountain;
    }

    public static int solveRec(int index,
                               int prevIndex,
                               int phase,
                               int[] nums) {

        if (index == nums.length) {
            return 0;
        }

        // Option 1: skip current element
        int noTake = solveRec(index + 1, prevIndex, phase, nums);

        int take = 0;

        if (prevIndex == -1) {
            // Always allowed to start
            take = 1 + solveRec(index + 1, index, 0, nums);
        }
        else {
            if (phase == 0) { // Increasing phase

                if (nums[index] > nums[prevIndex]) {
                    // continue increasing
                    take = 1 + solveRec(index + 1, index, 0, nums);
                }
                else if (nums[index] < nums[prevIndex]) {
                    // switch to decreasing (start downhill)
                    take = 1 + solveRec(index + 1, index, 1, nums);
                }

            } else { // Decreasing phase

                if (nums[index] < nums[prevIndex]) {
                    take = 1 + solveRec(index + 1, index, 1, nums);
                }
            }
        }

        return Math.max(take, noTake);
    }

    public static int solveDp(int nums[]){
        int n =nums.length;
        int lis[]=new int[n+1];
        int lds[]=new int[n+1];
        Arrays.fill(lis,1);
        Arrays.fill(lds,1);
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                if(nums[i] > nums[j] && lis[j]+1 > lis[i]){
                    lis[i] = lis[j]+1;
                }
            }
        }
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=i;j--){
                if(nums[i] > nums[j] && lds[j]+1 > lds[i]){
                    lds[i] = lds[j]+1;
                }
            }
        }
        int max=0;
        for(int i=0;i<n;i++){
            if(lis[i]> 1 && lds[i] > 1){
                max = Math.max(lis[i] + lds[i]-1,max);
            }
        }
        return max;
    }
}
