package career.datastructure.arrays.easy;

import java.util.ArrayList;
import java.util.List;

public class SecondLargestAndSmallestElementInAnArray {
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(10);
        lst.add(9);
        lst.add(2);
        lst.add(1);

        System.out.println(print2largest(lst));
        System.out.println(print2Smallest(lst));

    }

    public static int print2largest(List<Integer> arr) {
        // Code Here
        int max1=0,max2=0;
        for(int i:arr){
            if(i > max1){
                max2=max1;
                max1=i;
            }else if(max2 < i && max1 != i){
                max2=i;
            }
        }
        return  max2;
    }

    public static int print2Smallest(List<Integer> arr) {
        // Code Here
        int max1=Integer.MAX_VALUE,max2=max1;
        for(int i:arr){
            if(i < max1){
                max2=max1;
                max1=i;
            }else if(max2 > i && max1 != i){
                max2=i;
            }
        }
        return  max2;
    }
}
