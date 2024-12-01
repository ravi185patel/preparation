package career.datastructure.arrays.easy;

import java.util.Arrays;

public class MettingRooms {
    public static void main(String[] args) {
        int intervals[][] = {{0,30},{5,10},{15,20}};
        System.out.println(canAttendMeetings(intervals));
    }
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; ++i) {
            var a = intervals[i - 1];
            var b = intervals[i];
            if (a[1] > b[0]) {
                return false;
            }
        }
        return true;
    }

}
