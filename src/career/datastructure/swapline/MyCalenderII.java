package career.datastructure.swapline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/*
https://leetcode.com/problems/my-calendar-ii/description/?envType=problem-list-v2&envId=a4szt2rh

1] TreeMap + line sweep algo

 */
public class MyCalenderII {
    TreeMap<Integer,Integer> calendar = new TreeMap<>();
    int count=0;
    public static void main(String[] args) {
    }

    public boolean book(int start, int end) {
        calendar.put(start,calendar.getOrDefault(start,0)+1);
        calendar.put(end,calendar.getOrDefault(end,0)-1);
        count=0;
        for(Integer key:calendar.keySet()){
            count+=calendar.get(key);
            if(count > 2){
                /*
                 why we need this portion as we are going to add more entries
                 in map so if start and end was overlapped 3 times no need to
                 add them in map so we are removing it from map.
                 */
                calendar.put(start,calendar.get(start)-1);
                if(calendar.get(start) == 0){
                    calendar.remove(start);
                }

                calendar.put(end,calendar.get(end)+1);
                if(calendar.get(end) == 0){
                    calendar.remove(end);
                }

                return false;
            }
        }
        return true;
    }
}
