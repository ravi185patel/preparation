package career.datastructure.stack.mono;

import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElement {
    public static void main(String[] args) {
//        int arr[] = {13, 8, 1, 5, 2, 5, 9, 7, 6, 12};
        int arr[] ={3,1,2,4};
        int res[] = findNextSmallerElement(arr);
        System.out.println(Arrays.toString(res));
    }

    public static int[] findNextSmallerElement(int arr[]) {
        Stack<Integer> stack = new Stack<>();
        int res[] = new int[arr.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                res[stack.peek()] = arr[i];
                stack.pop();
            }
            stack.push(i);
        }

        return res;
    }
}