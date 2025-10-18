package career.datastructure.swapline;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/employee-free-time/description/?envType=problem-list-v2&envId=a4szt2rh

We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common,
positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y],
the objects inside are Intervals, not lists or arrays.
For example, schedule[0][0].start = 1, schedule[0][0].end = 2,
and schedule[0][0][0] is not defined).
Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



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

    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        Map<Integer, Integer> map = schedule.stream()
                .flatMap(List::stream)
                .flatMap(interval -> Stream.of(
                        new AbstractMap.SimpleEntry<>(interval.start, 1),
                        new AbstractMap.SimpleEntry<>(interval.end, -1)
                ))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                ));

        Set<Integer> set = schedule.stream().flatMap(List::stream)
                .flatMap(interval -> Stream.of(interval.start,interval.end))
                .collect(Collectors.toSet());

        List<Integer> lst = new ArrayList<>(set);
        Collections.sort(lst);
        int prevOp =-1,prevPoint=-1,op=0;
        List<Interval> res = new ArrayList<>();
        for(int point:lst){
            op += map.get(point);
            if(prevOp == 0 && op > 0){
                res.add(new Interval(prevPoint,point));
            }
            prevPoint = point;
            prevOp = op;
        }

        return res;
    }

    public List<Interval> mergeInterval1(List<List<Interval>> schedules){
        List<Interval> schedule = schedules.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        Collections.sort(schedule,(l1,l2) -> l1.start - l2.start);
        List<Interval> lst = new ArrayList<>();
        for(Interval interval: schedule){
            if(lst.isEmpty()){
                lst.add(interval);
            }else{
                Interval prevInterval = lst.remove(lst.size()-1);
                if(
                        (prevInterval.start <= interval.start
                                && prevInterval.end >= interval.start)
                                || (prevInterval.start <= interval.end
                                && prevInterval.end >= interval.end)
                ){

                    Interval newInterval = new Interval(
                            Math.min(prevInterval.start,interval.start)
                            ,Math.max(prevInterval.end,interval.end));
                    lst.add(newInterval);
                }else{
                    lst.add(prevInterval);
                    lst.add(interval);
                }
            }
        }

        Collections.sort(lst,(l1,l2)-> l1.start-l2.start);
        List<Interval> res = new ArrayList<>();
        for(int i=1;i<lst.size();i++){
            int x = lst.get(i-1).start;
            int y = lst.get(i-1).end;

            int x1 = lst.get(i).start;
            int y1 = lst.get(i).end;

            if(y < x1){
                res.add(new Interval(y,x1));
            }
        }
        return res;
    }



    public List<Interval> mergeInterval3(List<List<Interval>> schedule) {
        PriorityQueue<Interval> pq = new PriorityQueue<>((a,b) -> {
            return a.start - b.start;
        });

        for(List<Interval> s : schedule) {
            for(Interval i : s) {
                pq.offer(i);
            }
        }

        List<Interval> res = new ArrayList<>();

        int end = pq.poll().end;

        while(!pq.isEmpty()) {
            Interval in = pq.poll();

            if(in.start > end) {
                res.add(new Interval(end, in.start));
                end = in.end;
            } else {
                end = Math.max(end, in.end);
            }
        }

        return res;
    }

}
