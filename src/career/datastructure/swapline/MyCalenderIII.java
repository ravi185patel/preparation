package career.datastructure.swapline;

import java.util.TreeMap;

/*
https://leetcode.com/problems/my-calendar-iii/description/

1] TreeMap + line sweep algo

 */
public class MyCalenderIII {
    TreeMap<Integer,Integer> calendar = new TreeMap<>();
    int count=0;
    public static void main(String[] args) {
    }

    public int book(int start, int end) {
        calendar.put(start,calendar.getOrDefault(start,0)+1);
        calendar.put(end,calendar.getOrDefault(end,0)-1);
        count=0;
        int max=0;
        for(Integer key:calendar.keySet()){
            count+=calendar.get(key);
            max = Math.max(max,count);
        }
        return max;
    }
}
