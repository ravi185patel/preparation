package career.datastructure.binarysearch;

public class BinarySearchSimple {
    public static void main(String[] args) {
        int[] a = {3, 4, 6, 7, 9, 12, 16, 17};
        int target = 12;
        int ind = binarySearch(a, target);
        System.out.println(ind);
    }

    public static int binarySearch(int []a,int target){
        int start=0,end = a.length-1;
        while(start <= end){
            int mid = (start + end)/2;
            if(a[mid] == target){
                return mid;
            }else if(a[mid] < target){
                start=mid+1;
            }else{
                end =mid-1;
            }
        }
        return -1;
    }
}
