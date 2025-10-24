package career.datastructure.swaplineordifferencearray;

import java.util.*;

/*
https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomII {
    public static void main(String[] args) {
        // return minMeetingRoomsPq(intervals);
//        return minMeetingRoomsq(intervals);
    }
    public int minMeetingRoomsPq(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((i,j) -> i-j);
        Arrays.sort(intervals,(a1,a2) -> a1[0]-a2[0]);
        for(int interval[]:intervals){
            if(pq.size() >=1 && pq.peek() <= interval[0]){
                pq.remove();
            }
            pq.add(interval[1]);
        }
        return pq.size();
    }

    public static int minMeetingRoomsq(int[][] intervals) {
        Queue<Integer> pq = new LinkedList<>(); // used only for find greatest one not for overlapped.
        int noOfConfRoom=0;
        /*
        test case failed for below where stack gives last entered interval whereas first element has 10 < 11 which means 2 rooms required.
        intervals = [
  [1, 10],   // ends at 10
  [2, 20],   // ends at 20 → pushed on top of 10
  [11, 12]   // starts at 11 — room [1,10] is free, but it's at bottom!
]
*/
        // Arrays.sort(intervals,(a1,a2) -> a1[0]-a2[0]);
        for(int interval[]:intervals){
            while(!pq.isEmpty() && pq.peek() <= interval[0]){
                pq.remove();
            }
            pq.add(interval[1]);
        }
        return pq.size();
    }

    public int minMeetingRoomsSL(int[][] intervals) {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int interval[]:intervals){
            map.put(interval[0],map.getOrDefault(interval[0],0)+1);
            map.put(interval[1],map.getOrDefault(interval[1],0)-1);
        }

        int op=0;
        int minNoOfConferenceRoom= Integer.MIN_VALUE;
        for(int key:map.keySet()){
            op+=map.get(key);
            minNoOfConferenceRoom = Math.max(op,minNoOfConferenceRoom);
        }

        return minNoOfConferenceRoom;
    }
}
