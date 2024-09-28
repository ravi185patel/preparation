package career.datastructure.arrays.easy;

public class FindNoAppearOnceAndOtherTwice {
    public static void main(String[] args) {
//        int  arr[] = {2,2,1};
        int arr[]= {4,1,2,1,2};
        int res = findNo(arr);
        System.out.println(res);
    }

    public static int findNo(int arr[]){
        int no = arr[0];
        for(int i=1;i<arr.length;i++){
            no ^= arr[i];
        }

        return no;
    }
}
