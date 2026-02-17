package career.datastructure.dp.dponsequence;

import java.util.*;

public class DeleteAndRun {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2,2,3,3,3,4}));
        System.out.println(deleteAndEarn(new int[]{3,4,2}));
    }

    private static Map<Integer, Integer> val;
    private static int[] memo;
    public static int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
//        return solve(nums, 0);
        val = new HashMap<>();
        for(int i:nums){
            val.put(i,val.getOrDefault(i,0)+1);
        }


        List<Integer> uniqueNums = new ArrayList<>(val.keySet());
        Collections.sort(uniqueNums);
        memo = new int[uniqueNums.size()];
        Arrays.fill(memo, -1);

        return solveMemo(uniqueNums, 0);
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

    private static int solveMemo(List<Integer> nums, int i) {
        if (i >= nums.size()) return 0;
        if (memo[i] != -1) return memo[i];

        int noTake = solveMemo(nums, i + 1);
        int take = val.get(nums.get(i));
        if (i + 1 < nums.size() && nums.get(i) + 1 == nums.get(i + 1)) {
            take += solveMemo(nums, i + 2);
        } else {
            take += solveMemo(nums, i + 1);
        }

        return memo[i] = Math.max(noTake, take);
    }

    public static int solveDp(int []nums){
        Map<Integer, Integer> val = new HashMap<>();
        for (int num : nums) val.put(num, val.getOrDefault(num, 0) + num);
        List<Integer> sortedNums = new ArrayList<>(val.keySet());
        Collections.sort(sortedNums);

        int[] dp = new int[sortedNums.size() + 1];
        for (int i = sortedNums.size() - 1; i >= 0; i--) {
            int take = val.get(sortedNums.get(i));
            if (i + 1 < sortedNums.size() && sortedNums.get(i + 1) == sortedNums.get(i) + 1) {
                take += dp[i + 2];
            } else {
                take += dp[i + 1];
            }
            dp[i] = Math.max(dp[i + 1], take);
        }

        return dp[0];

    }
    public static int solveDpOp1(int[]nums){
        int m = 0;
        for (int num : nums) m = Math.max(m, num);

        int[] dp = new int[m + 2];
        for (int num : nums) dp[num] += num;
        for (int i = m - 1; i > 0; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + dp[i]);
        }
        return dp[1];
    }
    public static int solveDpOp2(int[]nums){
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) count.put(num, count.getOrDefault(num, 0) + num);
        List<Integer> uniqueNums = new ArrayList<>(count.keySet());
        Collections.sort(uniqueNums);

        int earn1 = 0, earn2 = 0;
        for (int i = 0; i < uniqueNums.size(); i++) {
            int curEarn = count.get(uniqueNums.get(i));
            if (i > 0 && uniqueNums.get(i) == uniqueNums.get(i - 1) + 1) {
                int temp = earn2;
                earn2 = Math.max(curEarn + earn1, earn2);
                earn1 = temp;
            } else {
                int temp = earn2;
                earn2 = curEarn + earn2;
                earn1 = temp;
            }
        }
        return earn2;
    }

    public static int solveDpOp(int[] nums) {

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
