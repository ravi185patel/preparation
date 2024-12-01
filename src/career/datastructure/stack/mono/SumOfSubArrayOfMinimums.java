package career.datastructure.stack.mono;

import java.util.Arrays;
import java.util.Stack;

public class SumOfSubArrayOfMinimums {
    public static void main(String[] args) {
        SumOfSubArrayOfMinimums sumOfSubArrayOfMinimums = new SumOfSubArrayOfMinimums();
        int arr[]={3,1,2,4};
        int res[][]= sumOfSubArrayOfMinimums.findNSLAndNSR(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));

        long ans =0;
        for(int i=0;i<arr.length;i++){
            long leftLength = i - res[1][i];
            long rightLength = res[0][i] - i;
            long totalSubArray = leftLength * rightLength;
            long totalSum = totalSubArray * arr[i];
            ans = (ans + totalSum)%(1000000007);
        }

        System.out.println(ans);

    }

    public int[][] findNSLAndNSR(int arr[]){

        int ns[][]= new int[2][arr.length];

        Stack<Integer> stack = new Stack<>();
        Arrays.fill(ns[0],arr.length);
        Arrays.fill(ns[1],-1);

        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                ns[0][stack.peek()] = i;
                stack.pop();
            }
            if(!stack.isEmpty()){
                ns[1][i] = stack.peek();
            }
            stack.push(i);
        }
        return ns;
    }
    public int[] findNSL(int [] arr){
        int nsl[]= new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(nsl,-1);
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                nsl[i] = stack.peek();
            }
            stack.push(i);
        }
        return nsl;
    }

    public int[] findNSR(int [] arr){
        int nsr[]= new int[arr.length];
        Arrays.fill(nsr,arr.length);
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                nsr[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }
        return nsr;
    }
    public int sumSubarrayMins(int[] arr) {
        int [] nsl = findNSL(arr);
        int [] nsr = findNSR(arr);
        long ans =0;
        for(int i=0;i<arr.length;i++){
            long leftLength = i - nsl[i];
            long rightLength = nsr[i] - i;
            long totalSubArray = leftLength * rightLength;
            long totalSum = totalSubArray * arr[i];
            ans = (ans + totalSum)%(1000000007);
        }
        return (int)ans;

    }
    public int sumSubarrayMins1(int[] arr) {
        int ans=0;
        for(int i=0;i<arr.length;i++){
            int min = arr[i];
            ans+=min;
            for(int j=i+1;j<arr.length;j++){
                min = Math.min(min,arr[j]);
                ans+=min;
            }
        }

        return ans;
    }
}
