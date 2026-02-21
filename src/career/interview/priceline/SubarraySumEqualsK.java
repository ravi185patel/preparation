package career.interview.priceline;

import java.util.HashMap;
import java.util.Map;

/*

1) only positive or negative ?
2) any possibility for integer overflow
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
//        System.out.println(subarraySum(new int[]{1,1,1},2));
//        System.out.println(subarraySum(new int[]{1,2,3},3));
//        System.out.println(subarraySum(new int[]{-1,2,3},2));
//        System.out.println(subarraySum(new int[]{-1,1,0},0));// -1,1 | -1,1,0 | 0
//        System.out.println(subarraySum(new int[]{0,1,-1},0));// -1,1 | -1,1,0 | 0
        System.out.println(subarraySum(new int[]{-3,1,0,-1,5},0));// 0 | 1,0,-1
    }

    public static int subarraySum(int[] nums, int k) {
//        return subarraySumBf(nums,k);
        return subarraySumSw(nums,k);
    }

    public static int subarraySumBf(int []nums,int k){
        int n = nums.length;
        int count=0;
        for(int i=0;i<n;i++){
            int sum=0;
            for(int j=i;j<n;j++){
                sum+=nums[j];
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySumSw(int []nums,int k){
        int sum=0,count=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            int reminder = sum - k;
            if(map.containsKey(reminder)){
                count += map.get(reminder);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }

        return count;
    }
}
