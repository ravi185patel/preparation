package career.interview.priceline.bs;

import java.util.Arrays;

public class MinTimeToCompleteTrip {

    public static void main(String[] args) {
        System.out.println(minimumTime(new int[]{1,2,3},5));
    }
    public static long minimumTime(int[] time, int totalTrips) {
        long low = 1;
        long high = (long) Arrays.stream(time).min().getAsInt() * totalTrips;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (canComplete(time, totalTrips, mid)) {
                high = mid;     // try smaller time
            } else {
                low = mid + 1;  // need more time
            }
        }
        return low;
    }

    private static boolean canComplete(int[] time, int totalTrips, long T) {
        long trips = 0;

        for (int t : time) {
            trips += T / t;
            if (trips >= totalTrips) return true; // early stop
        }

        return false;
    }
}
