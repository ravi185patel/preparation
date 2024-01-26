package career.interview.atlassian;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SetIntersectionSizeAtLeastTwo {
    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals,(i,j) -> i[0]-j[0] );

//        for(int[] interval:intervals){
//            System.out.println(Arrays.toString(interval));
//        }

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        List<int[]> res = new ArrayList<>();

        for(int i=1;i<intervals.length;i++){
            int point[]=stack.pop();
            if( intervals[i][0] <= point[1]  && point[1] <= intervals[i][1]){
                stack.push(new int[]{point[0],intervals[i][1]});
            }else{
                stack.push(intervals[i]);
                res.add(point);
            }
        }

        while(!stack.isEmpty()){
            res.add(stack.pop());
        }

        // while(!stack.isEmpty()){
        //     int point[]=stack.pop();
        //     boolean flag=false;
        //     for(int i=index;i<intervals.length;i++){
        //         if(point[1] >= intervals[i][0] && point[1] <= intervals[i][1]){
        //             stack.push(new int[]{point[0],intervals[i][1]});
        //             flag =true;

        //             break;
        //         }
        //     }

        //     if(!flag){
        //         res.add(point);
        //         flag = false;
        //     }
        //     index++;
        // }

        int resCnt =0;
        for(int i[]:res){
            resCnt += (i[1]-i[0])-2;
            System.out.println(Arrays.toString(i));
        }

        return resCnt;

    }
    public static void main(String[] args) {
        int arr[][]= {{1,3},{3,7},{8,9}};
        int res = intersectionSizeTwo(arr);
        System.out.println(res);
    }
}
