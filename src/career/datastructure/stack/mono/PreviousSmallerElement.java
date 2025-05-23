package career.datastructure.stack.mono;

import java.util.Arrays;
import java.util.Stack;

public class PreviousSmallerElement {
    public static void main(String[] args) {
        int arr[] = {13, 8, 1, 5, 2, 5, 9, 7, 6, 12};
        int res[] = findPreviousSmallerElement(arr);
        System.out.println(Arrays.toString(res));
    }

    public static int[] findPreviousSmallerElement(int arr[]) {
        Stack<Integer> stack = new Stack<>();
        int res[] = new int[arr.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if(!stack.isEmpty()){
                res[i] = arr[stack.peek()];
            }
            stack.push(i);
        }

        return res;
    }
}