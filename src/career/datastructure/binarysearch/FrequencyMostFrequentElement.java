package career.datastructure.binarysearch;

import java.util.Arrays;

public class FrequencyMostFrequentElement {
    public static void main(String[] args) {

          System.out.println(maxFrequency(new int[]{1,2,3,4,5},3));
//        System.out.println(maxFrequency(new int[]{1,2,4},5));
//        System.out.println(maxFrequency(new int[]{1,4,8,13},5));
//        System.out.println(maxFrequency(new int[]{3,9,6},2));
//        System.out.println(maxFrequency(new int[]{9930,9923,9983,9997,9934,9952,9945,9914,9985,9982,9970,9932,9985,9902,
//                9975,9990,9922,9990,9994,9937,9996,9964,9943,9963,9911,9925,9935,9945,9933,9916,9930,9938,10000,9916,9911,9959,
//                9957,9907,9913,9916,9993,9930,9975,9924,9988,9923,9910,9925,9977,9981,9927,9930,9927,9925,9923,9904,9928,9928,9986,9903,9985,9954,9938,
//                9911,9952,9974,9926,9920,9972,9983,9973,9917,9995,9973,9977,9947,9936,9975,9954,9932,9964,9972,9935,9946,9966},3056));
    }

    public static int check(int i, int k, int[] nums, long[] prefix) {
        int target = nums[i];
        int left = 0;
        int right = i;
        int best = i;

        while (left <= right) {
            int mid = (left + right) / 2;
            long count = i - mid + 1;
            long finalSum = count * target;
            long originalSum = prefix[i] - prefix[mid] + nums[mid];
            long operationsRequired = finalSum - originalSum;

            if (operationsRequired > k) {
                left = mid + 1;
            } else {
                best = mid;
                right = mid - 1;
            }
        }

        return i - best + 1;
    }

    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long[] prefix = new long[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, check(i, k, nums, prefix));
        }

        return ans;

    }
}
