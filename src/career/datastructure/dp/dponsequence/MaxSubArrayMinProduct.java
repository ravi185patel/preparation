package career.datastructure.dp.dponsequence;

import java.util.Arrays;
import java.util.Stack;

public class MaxSubArrayMinProduct {
    public static void main(String[] args) {
        System.out.println(maxSumMinProduct(new int[]{1,2,3,2}));
        System.out.println(maxSumMinProduct(new int[]{2,3,3,1,2}));
        System.out.println(maxSumMinProduct(new int[]{3,1,5,6,4,2}));
    }
    public static int maxSumMinProduct(int[] nums) {
//        return solve(nums);
        return solveOp(nums);
    }

    static long MOD = 1000000007;
    public static int solve(int nums[]){
        long max=0;
        for(int i=0;i<nums.length;i++){
            long min = Long.MAX_VALUE;
            long sum=0;
            for(int j=i;j<nums.length;j++){
                min = Math.min(min,nums[j]);
                sum+=(nums[j]%MOD);
                max = Math.max(max,min*sum);
            }
        }
        return (int)(max%MOD);
    }

    public static int solveOp(int nums[]){
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int[] prevMin = new int[n];
        int[] nxtMin = new int[n];
        Arrays.fill(prevMin, -1);
        Arrays.fill(nxtMin, n);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                prevMin[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nxtMin[i] = stack.peek();
            }
            stack.push(i);
        }

        long res = 0;
        int MOD = 1_000_000_007;
        for (int i = 0; i < n; i++) {
            int l = prevMin[i] + 1, r = nxtMin[i] - 1;
            long totalSum = prefixSum[r + 1] - prefixSum[l];
            res = Math.max(res, nums[i] * totalSum);
        }
// 2th 6th april

        return (int)(res % MOD);
    }
}
