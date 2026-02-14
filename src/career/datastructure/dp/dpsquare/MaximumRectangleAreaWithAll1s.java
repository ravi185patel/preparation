package career.datastructure.dp.dpsquare;

import java.util.Arrays;
import java.util.Stack;

public class MaximumRectangleAreaWithAll1s {
    public static void main(String[] args) {
        System.out.println(solve(
                new int[][]{
                        {1,0,1,0,0},
                        {1,0,1,1,1},
                        {1,1,1,1,1},
                        {1,0,0,1,0}
                }
        )); // 6
    }

    public static int solve(int matrix[][]){
        int m = matrix.length;
        int n = matrix[0].length;
        return solveRec(m,n,matrix);
    }

    public static int solveRec(int m,int n,int matrix[][]){
        int dp[]=new int[n];
        int max=0;
        for(int row[]:matrix){
            for(int j=0;j<n;j++){
                if(row[j] == 0){
                    dp[j]=0;
                }else{
                    dp[j]+=1;
                }
            }
            System.out.println(Arrays.toString(dp));
            max=Math.max(max,findMaxArea(dp));
        }
        return max;
    }

    private static int findMaxArea(int dp[]){
        Stack<Integer> stack = new Stack<>();
        int max=0;
        stack.push(-1);
        for(int i=0;i<dp.length;i++){
            while(stack.peek()!=-1 && dp[stack.peek()] >= dp[i]){
                int currHeight = dp[stack.pop()]; // stack -> -1,3,
                int currIndex = stack.peek(); // stack -> -1
                int area = currHeight * (i-currIndex-1); // include current and previous element
                max = Math.max(max,area);
            }
            stack.push(i);
        }

        while(stack.peek()!=-1){
            int currHeight = dp[stack.pop()]; // stack -> -1,3,
            int currIndex = stack.peek(); // stack -> -1
            int area = currHeight * (dp.length-currIndex-1); // include current and previous element
            max = Math.max(max,area);
        }
        return max;
    }


}
