package career.thirtydays.stack.monotonic;


import java.util.Stack;

/*
https://leetcode.com/problems/maximal-rectangle/description/

it is same has finding largest histogram
1] calculate one at ach row and call find largest histogram method

 */
public class MaximumRectangle{
    public static void main(String[] args) {
        char matrix[][] = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalRectangle(matrix));
    }

    public static int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int height[]=new int[n];
        int max=0;
        for(char mat[]:matrix){
            for(int i=0;i<n;i++){
                if(mat[i] == '0'){
                    height[i] = 0;
                }else{
                    height[i] += 1;
                }
            }
            int area = findMaxArea(height);
            max = Math.max(max,area);
        }

        return max;
    }

    private static int findMaxArea(int heights[]){
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int maxArea=0;
        stack.push(-1);
        for(int i=0;i<n;i++){
            while(stack.peek()!= -1 && heights[stack.peek()] >= heights[i]){
                int currHeight = heights[stack.pop()]; // very important
                int currIndex = stack.peek(); // peek index
                int area = currHeight * (i - currIndex - 1);
                maxArea = Math.max(maxArea,area);
            }
            stack.push(i);
        }

        while(stack.peek()!= -1){
            int currHeight = heights[stack.pop()];
            int currIndex = stack.peek();
            int area = currHeight * (heights.length - currIndex - 1);
            maxArea = Math.max(maxArea,area);
        }

        return maxArea;
    }
}
