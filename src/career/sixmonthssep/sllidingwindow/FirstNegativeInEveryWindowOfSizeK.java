package career.sixmonthssep.sllidingwindow;

import java.util.ArrayList;
import java.util.List;

/*
https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1

fixed size.
[-1,2,3,-1,3,4,-2]  k = 3
-1,2,3
2,3,-1
3,-1,4
[-1,-2,-3,-1,3,4,-2]  k = 3

 */
public class FirstNegativeInEveryWindowOfSizeK {
    public static void main(String[] args) {
//        int arr[] = {-8, 2, 3, -6, 10} , k = 2;
        int  arr[] = {12, -1, -7, 8, -15, 30, 16, 28} , k = 3;
        System.out.println(firstNegInt(arr,k));
    }
    static List<Integer> firstNegInt(int arr[], int k) {
        // write code here
        List<Integer> res = new ArrayList<>();
        int start=0,count=0,negativeNo=0,prevNegativeNo=0;
        for(int  i=0;i<arr.length;i++){
            if(arr[i] < 0){
                if(negativeNo < 0){
                    continue;
                }
                prevNegativeNo = negativeNo;
                negativeNo = arr[i];
            }
            if(i-start+1 == k){
                res.add(negativeNo);
                if(arr[start] < 0){
                    negativeNo = 0;
                    count--;
                }
                start++;
            }
        }
        return res;
    }
}
