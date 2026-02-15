package career.datastructure.dp.dponsequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DeleteAndRun {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2,2,3,3,3,4}));
        System.out.println(deleteAndEarn(new int[]{3,4,2}));
    }
    public static int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        return solve(nums, 0);
    }

    private static int solve(int[] nums, int i) {

        if (i >= nums.length) return 0;

        int skip = solve(nums, i + 1);

        int takeValue = nums[i];
        int j = i + 1;

        while (j < nums.length && nums[j] == nums[i]) {
            takeValue += nums[j];
            j++;
        }
        while (j < nums.length && nums[j] == nums[i] + 1) {
            j++;
        }

        int take = takeValue + solve(nums, j);

        return Math.max(skip, take);
    }

    public static int solveDp(int[] nums) {

        int max = 0;
        for (int n : nums) max = Math.max(max, n);

        int[] points = new int[max + 1];

        for (int n : nums)
            points[n] += n;

        int take = 0, skip = 0;

        for (int i = 0; i <= max; i++) {
            int takeNew = skip + points[i];
            int skipNew = Math.max(skip, take);

            take = takeNew;
            skip = skipNew;
        }

        return Math.max(take, skip);
    }

    static Map<Integer,Integer> points,cache;
    public int deleteAndEarn1(int[] nums) {
        points = new HashMap<>();
        cache = new HashMap<>();

        int max=0;
        for(int num:nums){
            points.put(num,points.getOrDefault(num,0)+num);
            max = Math.max(max,num);
        }

//        return maxEarn(max);
        return maxEarnDp(max);
    }

    private static int maxEarn(int num){
        // Check for base cases
        if (num == 0) {
            return 0;
        }

        if (num == 1) {
            return points.getOrDefault(1, 0);
        }

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        // Apply recurrence relation
        int gain = points.getOrDefault(num, 0);
        cache.put(num, Math.max(maxEarn(num - 1), maxEarn(num - 2) + gain));
        return cache.get(num);

    }

    private static int maxEarnDp(int maxNumber){
        // Declare our array along with base cases
        int[] maxPoints = new int[maxNumber + 1];
        maxPoints[1] = points.getOrDefault(1, 0);

        for (int num = 2; num < maxPoints.length; num++) {
            // Apply recurrence relation
            int gain = points.getOrDefault(num, 0);
            maxPoints[num] = Math.max(maxPoints[num - 1], maxPoints[num - 2] + gain);
        }

        return maxPoints[maxNumber];
    }
}
