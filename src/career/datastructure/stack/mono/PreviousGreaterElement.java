package career.datastructure.stack.mono;

import java.util.Arrays;
import java.util.Stack;


/*

arr = [13, 8, 1, 5, 2, 5, 9, 7, 6, 12]


Previous greater elements -
previousGreaterElements = [null, 13, 8, 8, 5, 8, 13, 9, 7, 13]
nextGreaterIndexes = [-1, 0, 1, 1, 3, 1, 0, 6, 7, 0]

 */
public class PreviousGreaterElement {
    public static void main(String[] args) {
        int arr[] = {13, 8, 1, 5, 2, 5, 9, 7, 6, 12};
        int res[] = findPrevGreaterElement(arr);
        System.out.println(Arrays.toString(res));
    }

    public static int[] findPrevGreaterElement(int arr[]){
        Stack<Integer> stack = new Stack<>();
        int res[]=  new int[arr.length];
        Arrays.fill(res,-1);
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]){
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
