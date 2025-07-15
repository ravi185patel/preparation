package career.datastructure.swapline;

import java.util.*;

/*
https://leetcode.com/problems/employee-free-time/description/?envType=problem-list-v2&envId=a4szt2rh

We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
 */
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
public class EmployeeFreeTime {
    public static void main(String[] args) {

    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        Set<Integer> set = new HashSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(List<Interval> intervals:schedule){
            for(Interval interval:intervals) {
                map.put(interval.start,map.getOrDefault(interval.start,0)+1);
                map.put(interval.end,map.getOrDefault(interval.end,0)-1);
                set.add(interval.start);
                set.add(interval.end);
            }
        }

        List<Integer> lst = new ArrayList<>(set);
        Collections.sort(lst);
        int op=0,prevOp=-1,prevPoint=-1;
        List<Interval> newSchedule = new ArrayList<>();
        for(int point:lst){
            op+=map.get(point);
            if( prevOp == 0 && op > 0){
                newSchedule.add(new Interval(prevPoint,point));
            }
            prevPoint=point;
            prevOp=op;
        }
        return newSchedule;
    }

}
