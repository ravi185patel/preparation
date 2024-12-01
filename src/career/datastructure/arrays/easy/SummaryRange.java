package career.datastructure.arrays.easy;

import java.util.ArrayList;
import java.util.List;

public class SummaryRange {
    public static void main(String[] args) {
        int nums [] = {0,1,2,4,5,7};
//        int nums [] = {0,2,3,4,6,8,9};
           List<String> res = summaryRanges(nums);
        System.out.println(res);
    }
    public static List<String> summaryRanges(int[] nums) {
        List<String> summaryRange = new ArrayList<>();
        int start=0;
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=nums.length;i++){
            if(i == nums.length || nums[i-1] != nums[i]-1 ){
                sb.append(nums[start]);
                if(start != i-1) {
                    sb.append("->").append(nums[i - 1]);
                }
                summaryRange.add(sb.toString());
                sb.setLength(0);
                start = i;
            }
        }
        return summaryRange;
    }
}
