package career.datastructure.swapline;

import java.util.Arrays;
import java.util.Stack;

/*
https://leetcode.com/problems/merge-intervals/description/?envType=problem-list-v2&envId=ak9fi45v
 */
public class MergeInterval {
    public static void main(String[] args) {

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
