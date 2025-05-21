package career.sixmonthssep.takeuforward;

import java.util.HashMap;
import java.util.Map;

public class CountSubArraywithGienSumK {
    public static void main(String[] args) {

    }


    public int subarraysWithSumK(int[] nums, int k) {
        Map<Integer,Integer> prevSum = new HashMap<>();
        int sum=0;
        int count=0;

        prevSum.put(0,1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(prevSum.containsKey(sum-k)){
                count+=prevSum.get(sum-k);
            }
            prevSum.put(sum,prevSum.getOrDefault(sum,0)+1);
        }

        return count;
    }
}
