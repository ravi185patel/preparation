package career.datastructure.swapline;

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
    private List<Integer> starts;
    private List<Integer> ends;
    public static void main(String[] args) {
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
