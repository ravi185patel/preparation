package career.datastructure.recurssion.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[]arr={3,5,2,7,1,9,4,8};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    private static void quickSort(int arr[],int  low,int high){
        if(low < high) {
            int mid = partition(arr, low, high);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid+1, high);
        }
    }

    private static int partition(int arr[],int low,int high){
        int pivot = arr[low];
        int i=low,j=high;
        while(i<j){
            while(arr[i] <= pivot && i<=high-1){
                i++;
            }
            while(arr[j] > pivot && j >= low+1){
                j--;
            }

            if(i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }
}
