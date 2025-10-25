package career.datastructure.swaplineordifferencearray;

import java.util.*;

/*
https://leetcode.com/problems/my-calendar-i/description/

input is dynmaic and we have to maintain ordering
treemap and line sweep algorithm

check if floorKey <= start
endKey <= end which means you can add entry

Why TreeMap?

TreeMap (a Red-Black Tree in Java) maintains keys in sorted order,
 allowing O(log N) operations (put, get, floorKey, ceilingKey).

Efficiently checks for overlapping intervals without scanning all entries.

Key Idea:

Store intervals as (start → end) in a TreeMap.
For a new booking [start, end):
Find the greatest start time ≤ start (floorKey).
Find the smallest start time ≥ start (ceilingKey).
Ensure start ≥ floorEntry.end AND end ≤ ceilingEntry.start.

Key Notes
floorKey(start): Returns the greatest key ≤ start (previous interval).
If prevStart.end > start → overlap.

ceilingKey(start): Returns the smallest key ≥ start (next interval).
If nextStart < end → overlap.

 */
public class MyCalenderI {
    TreeMap<Integer,Integer> calendar = new TreeMap<>();
    List<int[]> intervals = new ArrayList<>();
    private List<Integer> starts;
    private List<Integer> ends;
    public static void main(String[] args) {
        MyCalenderI myCalenderI = new MyCalenderI();
        boolean flag = myCalenderI.bookArrBS(10,20);
        System.out.println(flag);
        flag = myCalenderI.bookArrBS(15,25);
        System.out.println(flag);
        flag = myCalenderI.bookArrBS(20,30);
        System.out.println(flag);
    }

    public boolean bookArrBS(int start, int end) {
        int left = 0, right = intervals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int[] midp = intervals.get(mid);

            if (midp[1] <= start) {
                // new interval is after mid
                left = mid + 1;
            } else if (midp[0] >= end) {
                // new interval is before mid
                right = mid - 1;
            } else {
                // Overlap found
                return false;
            }
        }
        intervals.add(left, new int[]{start, end});
        return true;
    }

    public boolean bookBS(int start, int end) {

        starts = new ArrayList<>();
        ends = new ArrayList<>();

        int index = Collections.binarySearch(ends,start); // provide lower bound

        if(index < 0){
            index =-index-1;
        }
        if(index < starts.size() && end > starts.get(index)){
            return false;
        }

        starts.add(index,start);
        ends.add(index,end);
        return true;
    }

    public boolean book(int start, int end) {
        Integer prevStart = calendar.floorKey(start);
//        floorKey(start):
//        Finds the latest interval that starts before (or at) start.
        // Check the previous interval (greatest start <= start)
        if(prevStart != null && calendar.get(prevStart) > start){
            return false; // overlaps
        }

        Integer nextStart  = calendar.ceilingKey(start);
        // Check the next interval (smallest start >= start)
//        ceilingKey(start):
//        Finds the earliest interval that starts after (or at) start.

        if(nextStart  != null && nextStart < end){
            return false;
        }

        calendar.put(start,end);
        return true;
    }
}
