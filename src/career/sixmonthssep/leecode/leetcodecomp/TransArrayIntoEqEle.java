package career.sixmonthssep.leecode.leetcodecomp;

import java.util.Arrays;

public class TransArrayIntoEqEle {
    /*
    -1,-1,-1,1,1,1
    1,1,-1,1,1,1
    1,1,1,-1,1,1
    1,1,1,1,-1,1
    1,1,1,1,1,-1
    1,1,1,1,1,1

     */
    public static void main(String[] args) {
//        int nums[] = {1,-1,1,-1,1}, k = 3;
//        int nums[] = {-1,-1,-1,1,1,1}, k = 5; // 1,1,-1,1,1,1 -> 1,1,1,-1,1,1 -> 1,1,1,1,-1,1 -> 1,1,1,1,1,-1 -> 1,1,1,1,1,1
//        int nums[] = {-1,-1,1,1,1,1}, k = 5; // 1,1-1,1,1,1 -> 1,1,1,-1,1,1 -> 1,1,1,1,-1,1 -> 1,1,1,1,1,-1 -> 1,1,1,1,1,1
//        int nums[] = {-1,-1,1,-1,1,1}, k = 5; // 1,1,1,-1,1,1 -> 1,1,1,1,-1,1 -> 1,1,1,1,1,-1 -> 1,1,1,1,1,1
//        int nums[] = {-1,-1,1,-1,1,-1}, k = 3; // 1,1,1,-1,1,1 -> 1,1,1,1,-1,1 -> 1,1,1,1,1,-1 -> 1,1,1,1,1,1
//        int nums[] = {1,-1,1}, k = 2; // 1,-1,1 -> 1,1,-1 -> 1,-1,1
//        int nums[] = {-1,1,1,1,-1,-1,-1,1,1}, k = 4; // 1,-1,1 -> 1,1,-1 -> 1,-1,1
        int nums[] = {1,-1,1,-1,1,-1,1,-1,-1,-1}, k = 4; // 1,-1,1 -> 1,1,-1 -> 1,-1,1
        System.out.println(Arrays.toString(nums));
        System.out.println((canMakeEqual(nums,k,1) || canMakeEqual(nums,k,-1)));
    }

    public static boolean canMakeEqual(int[] nums, int k,int target) {
        int count=0;
        int temp[]=nums.clone();
        for(int i=0;i<temp.length-1;i++){
            if(temp[i] != target){
                temp[i+1]*=-1;
                temp[i]*=-1;
                count++;
            }
        }
        if(temp[temp.length-1]!= target){
            return false;
        }
        return count <= k;
    }
}
