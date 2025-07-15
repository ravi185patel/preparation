package career.sixmonthssep.neetcode.array;

import java.util.Arrays;

public class ProductOfArrayExceptItSelf {
    public static void main(String[] args) {
        int res[]=productExceptSelf(new int[]{-1,0,1,2,3});
        System.out.println(Arrays.toString(res));
//        res=productExceptSelf(new int[]{1,2,4,6});
        res=productExceptSelf(new int[]{4,3,2,1,2});
        System.out.println(Arrays.toString(res));
    }
    public static int[] productExceptSelf(int[] nums) {
        int product = 1;
        int res[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            product*=nums[i];
            res[i]=product;
        }
        System.out.println(Arrays.toString(res));
        product=1;
        for(int i=nums.length-1;i>=0;i--){
            if(i==0){
                res[i]=product;
            }else {
                res[i] = product * res[i - 1];
                product *= nums[i];
            }
        }

//        res[0] = product*nums[0];
        return res;
    }
}
