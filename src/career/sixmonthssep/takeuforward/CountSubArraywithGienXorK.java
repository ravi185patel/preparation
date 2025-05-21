package career.sixmonthssep.takeuforward;

import java.util.HashMap;

public class CountSubArraywithGienXorK {
    public static void main(String[] args) {

    }


    public int subarraysWithXorK(int[] nums, int k) {
        HashMap<Integer,Integer> hmap = new HashMap<>();
        int sum=0,cnt=0;
        hmap.put(0,1);
        for(int i=0;i<nums.length;i++){
            sum^=nums[i];

            int remove = sum ^ k;

            cnt += hmap.getOrDefault(remove,0);

            hmap.put(sum,hmap.getOrDefault(sum,0)+1);
        }
        return cnt;
    }
}
