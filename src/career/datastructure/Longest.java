package career.datastructure;

import java.util.HashSet;

public class Longest {
    public static int longestConsecutiveSubarray(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        HashSet<Integer> numSet = new HashSet<>();
        for (int num : arr) {
            numSet.add(num);
        }

        int maxLength = 0;

        for (int num : arr) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {7, 14, 18, 18, 9, 12, 1, 20, 11, 10,
                6, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 5, 16, 2, 7, 6, 16, 3, 20,
                11, 2, 17};

        System.out.println("Length of longest consecutive subarray: " + longestConsecutiveSubarray(arr));
    }

    public static int maxLength(int[] arr) {
        // Write your code here.
        return findLargetSubArray(arr);
    }

    public static int findLargetSubArray(int []arr){
        if (arr.length == 0) {
            return 0;
        }

        HashSet<Integer> numSet = new HashSet<>();
        for (int num : arr) {
            numSet.add(num);
        }

        int maxLength = 0;

        for (int num : arr) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }
}
