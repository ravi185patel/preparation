package career.interview.priceline;

import java.util.Arrays;

/*

1) only positive ? -> combine pos, neg, zero
2) limit n > 10^9


 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4}))); //1,2,6,24
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1,1,0,-3,3})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{0,1,1,1,0})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{0})));
    }
    public static int[] productExceptSelf(int[] nums) {
//        return productExceptSelfBf(nums);
//        return productExceptSelfPrefixMul(nums);
        return productExceptSelfPrefixMulOp(nums);
    }

    public static int[] productExceptSelfBf(int[]nums){
        int n = nums.length;
        int res[]=new int[n];
        if(nums.length == 1 && nums[0]== 0) return res;

        for(int i=0;i<nums.length;i++){
            int product=1;
            for(int j=0;j<nums.length;j++){
                if(i!=j){
                    if(nums[j] == 0){
                        product=0;
                        break;
                    }else{
                        product *= nums[j];
                    }
                }
            }
            res[i]=product;
        }
        return res;
    }

    public static int[] productExceptSelfPrefixMul(int[]nums){
        int n = nums.length;
        int res[]=new int[n];
        if(nums.length == 1 && nums[0]== 0) return res;

        int prefix[]=new int[n+1];
        int sufix[]=new int[n+1];
        for(int i=0;i<n;i++){
            if(i==0){
                prefix[i]=nums[i];
            }else {
                prefix[i] = prefix[i-1] * nums[i];
            }
        }

        for(int i=n-1;i>=0;i--){
            if(i==n-1){
                sufix[i]=nums[i];
            }else {
                sufix[i] = sufix[i+1] * nums[i];
            }
        }
        for(int i=0;i<n;i++){
            if(i==0){
                res[i]= sufix[i];
            }else if(i==n-1){
                res[i]=prefix[i-1];
            }else{
                res[i] = prefix[i-1]*sufix[i+1];
            }
        }
        return res;
    }

    public static int[] productExceptSelfPrefixMulOp(int[]nums){
        int n = nums.length;
        int res[]=new int[n];
        if(nums.length == 1 && nums[0]== 0) return res;

        int product=1;
        for(int i=0;i<n;i++){
            product *=nums[i];
            res[i]=product;
        }
        product=1;
        for(int i=n-1;i>=0;i--){
           if(i==0){
               res[i]=product;
           }else{
               res[i]=product * res[i-1];
               product *=nums[i];
           }
        }
        return res;
    }
}
