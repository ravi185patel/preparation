package career.datastructure.arrays.easy;

import java.util.ArrayList;

public class UnionOfTwoSortedArray {
    public static void main(String[] args) {
        int n = 5, arr1[] = {1, 2, 3, 4, 5},m = 5, arr2 [] = {1, 2, 3, 6, 7};
//        int n = 5, arr1[] = {2, 2, 3, 4, 5}, m = 5, arr2[] = {1, 1, 2, 3, 4};
        ArrayList<Integer> res = findUnion(arr1,arr2,n,m);
        System.out.println(res);
    }

    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m)
    {
        // add your code here
        int i1=0,i2=0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        int start=0,prev =0;
        while(i1 < n && i2 < m){

            if(arr1[i1] < arr2[i2]){
                if(prev != arr1[i1]) {
                    prev = arr1[i1];
                    res.add(arr1[i1]);
                }
                i1++;
            }else if(arr1[i1] > arr2[i2]){
                if(prev != arr2[i2]) {
                    prev = arr2[i2];
                    res.add(arr2[i2]);
                }
                i2++;
            }
            else{
                if(prev != arr1[i1]) {
                    prev = arr1[i1];
                    res.add(arr1[i1]);
                }
                i1++;
                i2++;
            }
        }

        while(i1 < n){
            if(prev != arr1[i1]) {
                prev = arr1[i1];
                res.add(arr1[i1]);
            }
            i1++;
        }
        while(i2 < m){
            if(prev != arr2[i2]) {
                prev = arr2[i2];
                res.add(arr2[i2]);
            }
            i2++;
        }

        return res;
    }
}
