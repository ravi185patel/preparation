package career.datastructure.stack.mono;

import java.util.Stack;

public class Find132Pattern {
    public static void main(String[] args) {

    }
    private static boolean is132Pattern(int nums[]){
        Stack<Integer> stack = new Stack<>();

        int possibleMiddelval = Integer.MIN_VALUE;
        for(int i=nums.length;i>=0;i--){
            if(nums[i-1] < possibleMiddelval){
                return true;
            }
            while(!stack.isEmpty() && stack.peek() < nums[i]){
                possibleMiddelval = stack.pop();
            }

            stack.push(nums[i]);
        }
        return false;
    }
}
