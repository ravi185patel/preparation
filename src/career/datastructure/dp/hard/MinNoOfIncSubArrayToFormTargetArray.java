package career.datastructure.dp.hard;

import java.util.Stack;

/*

clarification question
1] is array contains only positive or both neg and pos.
2] range of n -> 0 to 10^5
3] Only increment op
4] start with 0.


Approach
1] Monotonic increasing -> tc=O(n) sc = O(N)
2]


1] monotonic
1,2,3,2,1
1,2,3,0,0 -> 3

3,1,1,2
3,0,0,1 -> 4

3,1,5,4,2
3,0,4,0,0 -> 7

 */
public class MinNoOfIncSubArrayToFormTargetArray {
    public static void main(String[] args) {
//        int target[] = {1,2,3,2,1};
//        int target[] = {3,1,1,2};
        int target[] = {3,1,5,4,2};

        System.out.println(minNoOpMono(target));
    }

    public static int minNumberOperations(int[] target) {
//        return minNoOpMono(target);
        return minNoOpWS(target);
    }

    private static int minNoOpWS(int[] target){
        int ans=0;
        for(int i=0;i<target.length;i++){
            if(i== 0 || target[i-1] < target[i]){
                if( i== 0){
                    ans+= target[i];
                }else {
                    ans += target[i] - target[i - 1];
                }
            }
        }

        return ans;
    }

    private static int minNoOpMono(int[] target){
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ans=0;
        for(int i=0;i<target.length;i++){
            if(!stack.isEmpty() && stack.peek() <= target[i]){
                ans+=target[i]-stack.peek();
                stack.pop();
            }
            stack.push(target[i]);
        }

        return ans;
    }

}
