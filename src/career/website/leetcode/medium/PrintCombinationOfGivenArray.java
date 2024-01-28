package career.website.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintCombinationOfGivenArray {
    public static void main(String[] args) {
        int arr[]={1,2,3};

        List<int[]> res= new ArrayList<>();
        generateAllCombinations(arr,0,arr.length-1,res);
        for(int []i:res){
            System.out.println(Arrays.toString(i));
        }
    }

    public static void generateAllCombinations(int arr[], int start, int end, List<int[]> res){
        if(start == end){
            res.add(arr.clone());
        }else {
            for (int i = start; i <= end; i++) {
                swap(arr, start, i);
                generateAllCombinations(arr, start+1, end, res);
                swap(arr, start, i);
            }
        }
    }

    public static void swap(int arr[],int i,int j){
        int temp = arr[i];
        arr[i] = arr[j] ;
        arr[j] = temp;
    }
}
