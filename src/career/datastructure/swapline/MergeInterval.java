package career.datastructure.swapline;

import java.util.*;

/*
https://leetcode.com/problems/merge-intervals/description/?envType=problem-list-v2&envId=ak9fi45v
 */
public class MergeInterval {
    public static void main(String[] args) {

    }

    public int[][] merge2(int[][] intervals) {
        Map<Integer, Integer> diff = new TreeMap<>(); // Use TreeMap to auto-sort keys
        for (int[] interval : intervals) {
            diff.put(interval[0], diff.getOrDefault(interval[0], 0) + 1);
            diff.put(interval[1], diff.getOrDefault(interval[1], 0) - 1);
        }

        List<int[]> merged = new ArrayList<>();
        int count = 0;
        int start = -1;

        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            int point = entry.getKey();
            int delta = entry.getValue();

            if (count == 0) {
                // Starting a new merged interval
                start = point;
            }
            count += delta;
            if (count == 0) {
                // Ending the current merged interval
                merged.add(new int[]{start, point});
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(i1,i2) -> i1[0]-i2[0]);
        List<int[]> intervalList = new ArrayList<>();
        for(int i=0;i<intervals.length;i++){
            if(i==0){
                intervalList.add(intervals[i]);
            }else{
                int prev[]= intervalList.remove(intervalList.size()-1);
                if(prev[1] < intervals[i][0]){
                    intervalList.add(prev);
                    intervalList.add(intervals[i]);
                }else{
                    intervalList.add(new int[]{
                            Math.min(prev[0],intervals[i][0]),
                            Math.max(prev[1],intervals[i][1])
                    });
                }
            }
        }

        return intervalList.toArray(new int[intervalList.size()][]);
    }
    public int[][] merge1(int[][] intervals) {
        Arrays.sort(intervals,(i1, i2) -> i1[0]-i2[0]);
        Stack<int[]> stack = new Stack<>();
        for(int i[]:intervals){
            if(stack.isEmpty()){
                stack.push(i);
            }else{
                int prev[]=stack.peek();
                if((prev[0] <= i[0] && i[0] <= prev[1])
                        || (prev[0] <= i[1] && i[1] <= prev[1])){ // prev0 < i0 < prev1 || prev0 <i1<prev1
                    prev[0] = Math.min(prev[0],i[0]);
                    prev[1] = Math.max(prev[1],i[1]);
                    stack.pop();
                    stack.push(prev);
                }else{
                    stack.push(i);
                }
            }
        }

        int[][] res = new int[stack.size()][2];
        int index = stack.size()-1;
        while(!stack.isEmpty()){
            res[index] = stack.pop();
            index--;
        }

        return res;
    }
}
