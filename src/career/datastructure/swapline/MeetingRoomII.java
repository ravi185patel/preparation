package career.datastructure.swapline;

import java.util.*;

/*
https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomII {
    public static void main(String[] args) {

    }

    public int minMeetingRoomsPq(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
        PriorityQueue<Integer> allocator =
                new PriorityQueue<Integer>(
                        intervals.length,
                        new Comparator<Integer>() {
                            public int compare(Integer a, Integer b) {
                                return a - b;
                            }
                        });

        // Sort the intervals by start time
        Arrays.sort(
                intervals,
                new Comparator<int[]>() {
                    public int compare(final int[] a, final int[] b) {
                        return a[0] - b[0];
                    }
                });

        // Add the first meeting
        allocator.add(intervals[0][1]);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }
    public int minMeetingRooms(int[][] intervals) {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int interval[]:intervals){
            map.put(interval[0],map.getOrDefault(interval[0],0)+1);
            map.put(interval[1],map.getOrDefault(interval[1],0)-1);
        }

        int op=0;
        int minRoom = Integer.MIN_VALUE;
        for(int key:map.keySet()){
            op+=map.get(key);
            minRoom = Math.max(op,minRoom);
        }

        return minRoom;
    }
}
