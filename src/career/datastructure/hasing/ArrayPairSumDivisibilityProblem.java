package career.datastructure.hasing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://www.geeksforgeeks.org/problems/array-pair-sum-divisibility-problem3257/1
  Company Tags                : Amazon, Microsoft, Goldman Sachs, Directi

  (a+b)%k == 0
  a%k = r1;
  b%k = r2
  r1 == r2
  k-r1 == r2;

  store reminder(r1) in set
  check next time other reminder( k- r1) for pair ( maths )
 */
public class ArrayPairSumDivisibilityProblem {
    public static void main(String[] args) {
        System.out.println(countPairDivByK(new int[]{9, 5, 7, 3},6));
        System.out.println(countPairDivByK(new int[]{4,4,4},4));
        System.out.println(countPairDivByK(new int[]{4,4},4));
    }

    public static boolean countPairDivByK(int arr[],int k){
        int length = arr.length;
        if(length%2 != 0){
            return false;
        }
        Set<Integer> st = new HashSet<>();
        for(int i:arr){
            int reminder = i%k;
            int otherReminder = k - reminder;
            if(st.contains(otherReminder) || (reminder == 0 && st.contains(0))){
                st.remove(otherReminder);
                st.remove(reminder);
            }else{
                st.add(reminder);
            }
        }
        return st.size() == 0;
    }

}
