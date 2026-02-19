package career.datastructure.dp.dponsequence;

import java.util.TreeSet;

public class UglyNumberII {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
        System.out.println(nthUglyNumber(1));
        System.out.println(nthUglyNumber(35));
    }
    public static int nthUglyNumber(int n) {
        return solveNormal(n);
    }
    public static int solveNormal(int n) {
        TreeSet<Long> uglyNoSet = new TreeSet<>();
        uglyNoSet.add(1L);

        Long currUgly = 1L;
        for(int i=0;i<n;i++){
            currUgly = uglyNoSet.pollFirst();

            uglyNoSet.add(currUgly*2);
            uglyNoSet.add(currUgly*3);
            uglyNoSet.add(currUgly*5);
        }

        return currUgly.intValue();
    }

    public static int solveDp(int n) {
        int[] uglyNumbers = new int[n]; // DP array to store ugly numbers
        uglyNumbers[0] = 1; // The first ugly number is 1

        // Three pointers for the multiples of 2, 3, and 5
        int indexMultipleOf2 = 0, indexMultipleOf3 = 0, indexMultipleOf5 = 0;
        int nextMultipleOf2 = 2, nextMultipleOf3 = 3, nextMultipleOf5 = 5;

        // Generate ugly numbers until we reach the nth one
        for (int i = 1; i < n; i++) {
            // Find the next ugly number as the minimum of the next multiples
            int nextUglyNumber = Math.min(
                    nextMultipleOf2,
                    Math.min(nextMultipleOf3, nextMultipleOf5)
            );
            uglyNumbers[i] = nextUglyNumber;

            // Update the corresponding pointer and next multiple
            if (nextUglyNumber == nextMultipleOf2) {
                indexMultipleOf2++;
                nextMultipleOf2 = uglyNumbers[indexMultipleOf2] * 2;
            }
            if (nextUglyNumber == nextMultipleOf3) {
                indexMultipleOf3++;
                nextMultipleOf3 = uglyNumbers[indexMultipleOf3] * 3;
            }
            if (nextUglyNumber == nextMultipleOf5) {
                indexMultipleOf5++;
                nextMultipleOf5 = uglyNumbers[indexMultipleOf5] * 5;
            }
        }

        return uglyNumbers[n - 1]; // Return the nth ugly number
    }
}
