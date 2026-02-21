package career.interview.priceline;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInArray {
    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[]{1,1,1,2}));
        System.out.println(findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
    }
    public static List<Integer> findDuplicates(int[] nums) {
//        return findDuplicatesBf(nums);
        return findDuplicatesOp(nums);
    }

    public static List<Integer> findDuplicatesBf(int []nums){
        List<Integer> lst = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] == nums[j] && !lst.contains(nums[i])){
                    lst.add(nums[i]);
                }
            }
        }
        return lst;
    }
    public static List<Integer> findDuplicatesOp(int []nums){
        List<Integer> res = new ArrayList<>();
        int length = nums.length;
        for(int i=0;i<length;i++){
            int x= Math.abs(nums[i]);
            if(nums[x-1] < 0){
                res.add(x);
            }
            nums[x-1] *=-1;
        }

        return res;
    }
}
