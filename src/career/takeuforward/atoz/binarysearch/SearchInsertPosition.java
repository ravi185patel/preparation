package career.takeuforward.atoz.binarysearch;

public class SearchInsertPosition {
    public static void main(String[] args) {
//        int  arr[] = {1,2,4,7}, x = 6;
        int arr[] = {1,2,4,7}, x = 2;
        int ind = findIndex(arr,x);
        System.out.println(ind);
    }

    public static int findIndex(int arr[],int x){
        int start=0,end=arr.length-1;
        int ans=arr.length;
        while(start<=end){
            int mid =(start+end)/2;
            if(arr[mid] >= x){
               ans = mid;
               end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return ans;
    }
}
