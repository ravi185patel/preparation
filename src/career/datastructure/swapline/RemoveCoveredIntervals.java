package career.datastructure.swapline;

import java.util.Arrays;

/*
https://leetcode.com/problems/remove-covered-intervals/?envType=problem-list-v2&envId=ak9fi45v
 */

public class RemoveCoveredIntervals {
    public static void main(String[] args) {

    }

    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals,(p1, p2) -> p1[0]-p2[0]);


        int count=0;
        int end,prevEnd =0;
        for(int i[]:intervals){
            end = i[1];
            if(prevEnd < end){
                ++count;
                prevEnd = end;
            }
        }

        return count;

        //    int count=0;
        //    Stack<int[]> stack = new Stack<>();

        //    for(int i[]:intervals){
        //         if(stack.isEmpty()){
        //             stack.push(i);
        //         }else{
        //             int prev[]=stack.pop();
        //             if((prev[0] <= i[0] && prev[1] >= i[1]) || (prev[0] >= i[0] && prev[1] <= i[1])){
        //                 int start = Math.min(prev[0],i[0]);
        //                 int end = Math.max(prev[1],i[1]);
        //                 stack.push(new int[]{start,end});
        //             }else{
        //                 stack.push(prev);
        //                 stack.push(i);
        //             }
        //             // if((prev[0] <= i[0] && prev[1] >= i[1]) || (prev[0] >= i[0] && prev[1] >= i[1])){
        //             //     count++;
        //             //     continue;
        //             // }

        //         }
        //    }

        //    return stack.size();
    }
}
