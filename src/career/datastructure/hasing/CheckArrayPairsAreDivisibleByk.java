package career.datastructure.hasing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/description/
 */
public class CheckArrayPairsAreDivisibleByk {
    public static void main(String[] args) {

    }

    public boolean canArrange(int[] arr, int k) {

        Map<Integer,Integer> remainderCount = new HashMap<>();

        for(int i:arr){
            int rem = ((i % k) + k) % k;
            remainderCount.put(rem,remainderCount.getOrDefault(rem,0)+1);
        }
        int count=0;

        for(int i:arr){
            int rem = ((i % k) + k) % k;

            // If the remainder for an element is 0, then the count
            // of numbers that give this remainder must be even.
            if(rem == 0){
                if(remainderCount.get(rem)%2 == 1) return false;
            }

            // If the remainder rem and k-rem do not have the
            // same count then pairs cannot be made.
            else if (
                    !Objects.equals(
                            remainderCount.get(rem),
                            remainderCount.get(k - rem)
                    )
            ) return false;
        }
        return true;
    }

    private class Comparator implements java.util.Comparator<Integer> {

        private int k;

        public Comparator(int k) {
            this.k = k;
        }

        public int compare(Integer i, Integer j) {
            return ((k + (i % k)) % k) - ((k + (j % k)) % k);
        }
    }

    public boolean canArrange1(int[] arr, int k) {
        Integer[] array = new Integer[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            array[i] = arr[i];
        }

        Arrays.sort(array, new Comparator(k));

        // Assign the pairs with modulo 0 first.
        int start = 0, end = array.length - 1;
        while (start < end) {
            if (array[start] % k != 0) break;
            if (array[start + 1] % k != 0) return false;
            start = start + 2;
        }

        // Now, pick one element from the beginning and one element from the end.
        while (start < end) {
            if ((array[start] + array[end]) % k != 0) return false;
            start++;
            end--;
        }
        return true;
    }

    public boolean canArrange2(int[] arr, int k) {
        int[] mp = new int[k];  // Array to store remainders frequency

        // Calculate the remainder frequencies
        for (int num : arr) {
            int rem = (num % k + k) % k;  // Handle negative remainders
            mp[rem]++;
        }

        // Check if the frequency of numbers with 0 remainder is even
        if (mp[0] % 2 != 0) {
            return false;
        }

        // Check if each remainder has a complement remainder with matching frequency
        // why k/2 because  reminder always between 0 to k-1 and if you get first part second part is always counter of first part so k/2;
        for (int rem = 1; rem <= k / 2; rem++) {
            int counterHalf = k - rem;
            if (mp[counterHalf] != mp[rem]) {
                return false;
            }
        }

        return true;
    }
}
