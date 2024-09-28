package career.datastructure.binarysearch;

public class SearchInRotatedSubArray {
    public static void main(String[] args) {
        int  arr[] = {4,5,6,7,0,1,2,3}, k = 2;
        System.out.println(findElement(arr,k));
    }

    public static int findElement(int arr[],int k){
        int start= 0,end = arr.length-1;

        while(start <= end){
            int mid = (start + end)/2;
            if(arr[mid] == k){
                return mid;
            }else if(arr[start]<= arr[mid]){
                if(arr[start] <= k && arr[mid] >=k){
                    end = mid-1;
                }else{
                    start = mid+1;
                }
            }else{
                if(arr[mid] <= k && arr[end] >=k){
                    start = mid +1;
                }else{
                    end = mid-1;
                }
            }
        }
        return -1;
    }
}
