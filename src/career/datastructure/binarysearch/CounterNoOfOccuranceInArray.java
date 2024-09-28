package career.datastructure.binarysearch;

public class CounterNoOfOccuranceInArray {
    public static void main(String[] args) {
        int  N = 7,  X = 3 , array[] = {2, 2 , 3 , 3 , 3 , 3 , 4};
        int res = lastOccurance(array,N,X) - firstOccurance(array,N,X) + 1;
        System.out.println(res);
    }

    public static int firstOccurance(int a[],int N,int x){
        int start =0 , end =N-1,firstOccurance=-1;

        while(start <= end){
            int mid = (start + end)/2;
            if(a[mid] == x){
                firstOccurance = mid;
                end = mid-1; // main condition which always look for first occurance so start with mid-1 (<=) direction
            }else if(a[mid] < x){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return firstOccurance;
    }

    public static int lastOccurance(int a[],int N,int x){
        int start =0 , end =N-1,lastOccurance=N;

        while(start <= end){
            int mid = (start + end)/2;
            if(a[mid] == x){
                lastOccurance = mid;
                start = mid+1;
            }else if(a[mid] < x){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return lastOccurance;
    }
}
