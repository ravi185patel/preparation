package career.datastructure.swapline;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/meeting-rooms-iii/description/
Approach
1] Sort Meetings: First, sort the meetings based on their start times.

2] Available Rooms: Use a min-heap to keep track of available rooms (initially all rooms are free).

3] Busy Rooms: Use another min-heap to track rooms that are currently in use, along with their end times.

Process Each Meeting:

1] Free up rooms that have ended by the current meeting's start time.

2] If a room is available, assign the meeting to the smallest-numbered room.

3] If no room is available, delay the meeting until the earliest available room, maintaining the original duration.

4] Count Meetings: Maintain an array to count how many meetings each room holds.

5] Determine Result: After processing all meetings, find the room with the most meetings. If there's a tie, return the smallest room number.
 */
public class MeetingRoomIII {
    public static void main(String[] args) {

    }
    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings based on start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Min-heap for available rooms (stores room numbers)
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }

        // Min-heap for busy rooms: (end_time, room_number)
        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            } else {
                return Long.compare(a[1], b[1]);
            }
        });

        // Array to count the number of meetings per room
        int[] meetingCount = new int[n];

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            // Free up rooms that have ended by the current meeting's start time
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                long[] roomInfo = busyRooms.poll();
                int room = (int) roomInfo[1];
                availableRooms.offer(room);
            }

            if (!availableRooms.isEmpty()) {
                // Assign to the smallest available room
                int room = availableRooms.poll();
                busyRooms.offer(new long[]{end, room});
                meetingCount[room]++;
            } else {
                // No available rooms, delay the meeting
                long[] earliestEnd = busyRooms.poll();
                int room = (int) earliestEnd[1];
                long newEnd = earliestEnd[0] + (end - start);
                busyRooms.offer(new long[]{newEnd, room});
                meetingCount[room]++;
            }
        }

        // Find the room with the maximum meetings
        int maxCount = 0;
        int resultRoom = 0;
        for (int i = 0; i < n; i++) {
            if (meetingCount[i] > maxCount) {
                maxCount = meetingCount[i];
                resultRoom = i;
            }
        }
        return resultRoom;
    }
}
