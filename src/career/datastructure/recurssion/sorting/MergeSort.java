package career.datastructure.recurssion.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int[]arr={3,5,2,7,1,9,4,8};
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int []nums,int start,int end){
        if(start >= end) return;
        int mid = start + (end - start)/2;
        mergeSort(nums,start,mid);
        mergeSort(nums,mid+1,end);
        merge(nums,start,mid,end);
    }

    private static void merge(int nums[],int start,int mid,int end){
        int low = start;
        int high = mid+1;
        List<Integer> lst = new ArrayList<>();

        while(low <=mid && high <= end ){
            if(nums[low] < nums[high]) {
                lst.add(nums[low]);
                low++;
            }else if(nums[low] > nums[high]){
                lst.add(nums[high]);
                high++;
            }else{
                lst.add(nums[high]);
                lst.add(nums[low]);
                low++;
                high++;
            }
        }

        while(low <= mid){
            lst.add(nums[low]);
            low++;
        }

        while(high <= end){
            lst.add(nums[high]);
            high++;
        }

        for(int i=start;i<=end;i++){
            nums[i]=lst.get(i-start); // very important
        }
    }
}
