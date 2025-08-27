package career.sixmonthssep.leecode.leetcodecomp;

public class TornincArray {
    public static void main(String[] args) {
        System.out.println(isTrionic(new int[]{1,3,5,4,2,6}));
        System.out.println(isTrionic(new int[]{2,1,3}));
        System.out.println(isTrionic(new int[]{-2,-1,-3}));
        System.out.println(isTrionic(new int[]{4,1,5,2,3}));
        System.out.println(isTrionic(new int[]{1,2,3}));
    }
    public static boolean isTrionic(int[] nums) {
        boolean p=false,q=false;
        int i=1;
        while(i<nums.length){
            if(nums[i] > nums[i-1]){i++;}
            else{
                break;
            }
        }
        if(i==1 || i==nums.length){
            return false;
        }
        while(i < nums.length){
            if(nums[i] < nums[i-1]){i++;}
            else{
                break;
            }
        }
        if(i==1 || i==nums.length){
            return false;
        }
        while(i < nums.length){
            if(nums[i] > nums[i-1]){i++;}
            else{
                break;
            }
        }
        return i == nums.length;
    }
}
