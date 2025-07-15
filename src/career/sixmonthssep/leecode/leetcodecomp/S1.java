package career.sixmonthssep.leecode.leetcodecomp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class S1 {
    public static void main(String[] args) {
        System.out.println(generateTag("Leetcode daily streak achieved"));
        System.out.println(generateTag("can I Go There"));
        System.out.println(generateTag("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"));
        System.out.println(generateTag("R"));
        System.out.println(generateTag("R k"));
        System.out.println(generateTag(" fPysaRtLQLiMKVvRhMkkDLNedQKffPnCjbITBTOVhoVjiKbfSawvpisDaNzXJctQkn"));

        System.out.println(specialTriplets(new int[]{6,3,6}));
        System.out.println(specialTriplets(new int[]{0,1,0,0}));
        System.out.println(specialTriplets(new int[]{8,4,2,8,4}));
        System.out.println(specialTriplets(new int[]{84,2,93,1,2,2,26}));
        System.out.println(specialTriplets(new int[]{53980,26990,53980}));
        System.out.println(specialTriplets(new int[]{200000,100000,200000}));
        System.out.println(specialTriplets(new int[]{2,2,1,2,2}));
        System.out.println(specialTriplets(new int[]{1,2,1,2,4}));
    }
    static final int MOD = 1000000007;
    public static String generateTag(String caption) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        boolean flag = false,ok=false;
        int index=0;
        String split[]=caption.split(" ");
        for(String str:split){
           if(str.length() ==0) continue;
           if(index==0){
               sb.append(str.toLowerCase());
           }else {
               sb.append(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
           }
           index++;
        }
        return (sb.length() < 100) ? sb.toString():sb.toString().substring(0,100);
    }

    public static int specialTriplets(int[] nums) {
        int length = nums.length;
        int count=0;
//        for(int i=0;i<length;i++){
//            for(int j=i+1;j<length;j++){
//                for(int k=j+1;k<length;k++){
//                    if(nums[i] == nums[j] * 2 && nums[k] == nums[j] * 2){
//                        count++;
//                    }
//                }
//            }
//        }

        int right[]=new int[200001];
        int left[]=new int[200001];
        for(int i=0;i<length;i++){
            right[nums[i]]++;
        }
//        System.out.println(Arrays.toString(right));
        for(int i=0;i<length;i++){
//            if(right[nums[i]] == 0) continue;
            right[nums[i]]--;
            int no = nums[i]*2;
            if(no < right.length) {
                if (right[no] != 0 && left[no] != 0) {
                    count += ((right[no] * left[no])%MOD);
                }
            }
            left[nums[i]]++;
        }
        return (int)(count%MOD);
    }
}
