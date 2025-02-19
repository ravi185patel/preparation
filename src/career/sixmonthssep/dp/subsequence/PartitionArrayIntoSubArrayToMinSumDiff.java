package career.sixmonthssep.dp.subsequence;

import java.util.Arrays;

/*
https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
contains negative + positive
https://www.naukri.com/code360/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494?source=youtube&campaign=striver_dp_videos&leftPanelTab=0
only positive value
 */
public class PartitionArrayIntoSubArrayToMinSumDiff {

    static long X[] = new long[2000005];
    static long Y[] = new long[2000005];

    public static void main(String[] args) {
//        int arr[]={3,9,7,3};
        int arr[]={-36,36};
//        int arr[]={2,-1,0,4,-2,-9};
        long a[] = { 3, 34, 4, 12, 5, 2 };
        int n = a.length;
        long S = 10;
        System.out.println("Largest value smaller " + "than or equal to given " + "sum is "
                + solveSubsetSum(a, n, S));
    }

    static int lowerBound(long a[], long x, int size_Y) {

        // x is the target value or key
        int l = -1, r = size_Y;
        while (l + 1 < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= x)
                r = m;
            else
                l = m;
        }
        return r;
    }

    static void calcsubarray(long a[], long x[], int n, int c) {
        for (int i = 0; i < (1 << n); i++) {
            long s = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) { // Check inclusion in subset
                    s += a[j + c];
                }
            }
            x[i] = s;
        }
    }

    // Returns the maximum possible sum
    // less or equal to S
    static long solveSubsetSum(long a[], int n, long S) {

        // Compute all subset sums of first and second
        // halves
        calcsubarray(a, X, n / 2, 0);
        calcsubarray(a, Y, n - n / 2, n / 2);

        int size_X = 1 << (n / 2);
        int size_Y = 1 << (n - n / 2);

        // Sort Y (we need to do doing
        // binary search in it)
        Arrays.sort(Y, 0, size_Y);

        // To keep track of the maximum sum
        // of a subset such that the maximum
        // sum is less than S
        long max = 0;

        // Traverse all elements of X and do
        // Binary Search for a pair in Y with
        // maximum sum less than S.
        for (int i = 0; i < size_X; i++) {
            if (X[i] <= S) {

                // lower_bound() returns the first address
                // which has value greater than or equal to
                // S-X[i].
                int p = lowerBound(Y, S - X[i], size_Y);

                // If S-X[i] was not in array Y then
                // decrease p by 1
                if (p == size_Y || Y[p] != (S - X[i]))
                    p--;

                if ((Y[p] + X[i]) > max)
                    max = Y[p] + X[i];
            }
        }
        return max;
    }


}
