package career.datastructure.arrays.easy;

import java.util.HashMap;
import java.util.Map;

public class LongesSubArrayWithGivenSumKPosAndNeg {
    public static void main(String[] args) {
//        int N = 3, k = 1, array[] = {-1, 1, 1}; // sliding window not work  ex : [-9,0,1] k=1;
        int N = 3, k = 1, array[] = {-9, 0, 1};
        // need storage to identify index
        int res = longestSubArrayWithGivenSumKPosAndNeg(array,N,k);
       System.out.println(res);

    }

    public static int longestSubArrayWithGivenSumKPosAndNeg(int arr[],int N,int K){
        Map<Integer,Integer> hmap = new HashMap<>();
        int max=0;
        int sum =0,j=0;
        for(int i=0;i<N;i++){
            sum+=arr[i];
            if(sum == K){
                max = Math.max(max,i+1);
            }

            int rem = sum - K;
            if(hmap.containsKey(rem)){
                int len = i - hmap.get(rem);
                max = Math.max(max,len);
            }

            if(!hmap.containsKey(rem)){
                hmap.put(sum,i);
            }

        }
        return max;
    }
}
