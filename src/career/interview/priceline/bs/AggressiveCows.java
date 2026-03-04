package career.interview.priceline.bs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class AggressiveCows {
    public static void main(String[] args) {
        System.out.println(aggressiveCows(new int[]{1,2,3},2));
        System.out.println(aggressiveCows(new int[]{4,2,1,3,6},2));
    }
    public static int aggressiveCows(int []stalls, int k) {
        Arrays.sort(stalls);

        int low = 1;
        int high = stalls[stalls.length - 1] - stalls[0];

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMake(stalls, k, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    public static boolean canMake(int [] stalls,int k,int distance){
        int count = 1;
        int lastPos = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= distance) {
                count++;
                lastPos = stalls[i];
                if (count == k) return true;
            }
        }

        return false;
    }
}
