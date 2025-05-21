package career.datastructure.swapline;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.com/problems/meeting-rooms/description/
 */
public class MeetingRoom {
    public static void main(String[] args) {

    }
        public boolean canAttendMeetings(int[][] intervals) {
            Map<Integer,Integer> map = new TreeMap<>(); // very important point
            for(int interval[]:intervals){
                map.put(interval[0],map.getOrDefault(interval[0],0)+1);
                map.put(interval[1],map.getOrDefault(interval[1],0)-1);
            }

            int op=0;
            for(int key:map.keySet()){
                op+=map.get(key);
                if(op > 1) return false;
            }

            return true;
        }
        public boolean canAttendMeetings1(int[][] intervals) {
            Arrays.sort(intervals,(i1, i2)-> i1[0] - i2[0]);
            // for(int []i:intervals){
            //     System.out.println(Arrays.toString(i));
            // }
            for(int i=1;i<intervals.length;i++){
                if(
                        (intervals[i-1][0] == intervals[i][0] && intervals[i-1][1] == intervals[i][1])
                                ||                (intervals[i-1][0] < intervals[i][1] && intervals[i-1][1] > intervals[i][1])
                                || (intervals[i-1][0] < intervals[i][0] && intervals[i-1][1] > intervals[i][0]) ){
                    return false;
                }
            }
            return true;
        }
}
