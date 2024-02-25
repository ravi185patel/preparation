package career.website.leetcode.hard;

import career.website.leetcode.medium.PrintCombinationOfGivenArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KInversePairInArray {
    static int mode = 1000000000+7;

    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6};

//        List<int[]> res= new ArrayList<>();
//        PrintCombinationOfGivenArray.generateAllCombinations(arr,0,arr.length-1,res);
//        for(int []i:res){
//            System.out.println(Arrays.toString(i));
//        }
//
//        int count=0;
//        for(int i[]:res) {
//            count += findKInversePairs(i,1) ?1:0;
//        }
//        int count=0;
//        System.out.println(count);


        int n=3,k=1;
        int count=dfs(n,k);
        System.out.println(count);

        int dp[][]=new int[n+1][k+1];
        for(int i[]:dp){
            Arrays.fill(i,-1);
        }

        count = dfsMemo(n,k,dp);
        System.out.println(count);

    }

    public static int dfs(int n,int k){
        if(n == 0){
            return k == 0 ? 1:0;
        }
        if(k < 0){
            return 0;
        }
        int res=0;
        for(int i=0;i<n;i++){
            res = (res + dfs(n-1,k-i))%mode;
        }
        return res;
    }


    public static int dfsMemo(int n,int k,int dp[][]){
        if(n == 0){
            return k == 0 ? 1:0;
        }
        if(k < 0){
            return 0;
        }

        if(dp[n][k] != -1){
            return dp[n][k];
        }
        int res=0;
        for(int i=0;i<n;i++){
            res = (res + dfsMemo(n-1,k-i,dp))%mode;
        }
        return dp[n][k] = res%mode;
    }

    public static boolean findKInversePairs(int arr[],int k){
        System.out.println("------------------\n"+Arrays.toString(arr));
        int count =mergeSort(arr,0,arr.length-1);
        System.out.println(" <> "+count);
        return count == k;
    }

    public static int mergeSort(int arr[],int start,int end){
        int cnt=0;
        if(start < end){
            int mid = (start + end)/2;
            cnt += mergeSort(arr,start,mid);
            cnt += mergeSort(arr,mid+1,end);
            cnt += merge(arr,start,mid,end);
        }
        return cnt;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr

        //Modification 1: cnt variable to count the pairs:
        int cnt = 0;

        //storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                cnt += (mid - left + 1); //Modification 2
                right++;
            }
        }

        // if elements on the left half are still left //

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        //  if elements on the right half are still left //
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // transfering all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return cnt; // Modification 3
    }


}
