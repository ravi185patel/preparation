package career.interview.priceline.bs;

public class SearchRotatedSortedArrayII {
    public static void main(String[] args) {
        System.out.println(search(new int[]{2,5,6,0,0,1,2},0));
        System.out.println(search(new int[]{2,5,6,0,0,1,2},1));
        System.out.println(search(new int[]{2,5,6,0,0,1,2},3));
    }

    public static boolean search(int[] arr, int k) {
        int start= 0,end = arr.length-1;

        while(start <= end){
            int mid = start + (end-start)/2;
            if(arr[mid] == k){
                return true;
            }
            if(arr[start] == arr[mid] && arr[mid] == arr[end]){
                start++;
                end--;
                continue;
            }else if(arr[start]<= arr[mid]){
                if(arr[start] <= k && k<=arr[mid] ){
                    end = mid-1;
                }else{
                    start = mid+1;
                }
            }else{
                if(arr[mid] <= k && k <= arr[end]){
                    start = mid +1;
                }else{
                    end = mid-1;
                }
            }
        }
        return false;
    }
}
