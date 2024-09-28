package career.datastructure.binarysearch;

public class LowerBound {
    public static void main(String[] args) {
//        int[] a = {3, 4, 6, 7, 9, 12, 16, 17};
//        int target = 12;
        int[] a = {3, 5, 8, 15, 19};
        int target = 9;
        int ind = lowerBond(a, target);
        System.out.println(ind);
    }

    public static int lowerBond(int a[],int target){
        int start=0,end = a.length-1;
        int ans=-1;
        while(start<= end){
            int mid = (start+end)/2;
            if(a[mid] >= target){ // upperBound condition change >= to >
                ans = mid;
                end =mid-1;
            }else{
                start= mid+1;
            }
        }
        return ans;
    }

}
