package career.sixmonthssep.leecode.leetcodecomp;

public class TrionicArrayII {
    public static void main(String[] args) {
//        System.out.println(maxSumTrionic(new int[]{1,3,5,4,2,6}));
//        System.out.println(maxSumTrionic(new int[]{2,1,3}));
//        System.out.println(maxSumTrionic(new int[]{-2,-1,-3}));
//        System.out.println(maxSumTrionic(new int[]{4,1,5,2,3}));
//        System.out.println(maxSumTrionic(new int[]{1,2,3}));
//        System.out.println(maxSumTrionic(new int[]{0,-2,-1,-3,0,2,-1}));
//        System.out.println(maxSumTrionic(new int[]{1,4,2,7}));
        System.out.println(maxSumTrionic(new int[]{2,993,-791,-635,-569}));
    }
    public static long maxSumTrionic(int[] nums) {
        int length = nums.length;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<length;i++){
            max = Math.max(max,isTrionic(i,nums));
        }
        return max;
    }

    public static int isTrionic(int i,int[] nums) {
        boolean first=false,second=false,third = false;
        int sum=nums[i];
        i++;
        while(i<nums.length){
            if(nums[i] > nums[i-1]){
                sum+=nums[i];
                i++;
                first = true;
            }
            else{
                break;
            }
        }
        if(i==1 || i==nums.length || first == false){
            return Integer.MIN_VALUE;
        }
        while(i < nums.length){
            if(nums[i] < nums[i-1]){
                sum+=nums[i];
                i++;
                second=true;
            }
            else{
                break;
            }
        }
        if(i==1 || i==nums.length || second == false){
            return Integer.MIN_VALUE;
        }
        while(i < nums.length){
            if(nums[i] > nums[i-1]){
                sum+=nums[i];
                i++;
                third = true;
            }
            else{
                break;
            }
        }
        return first && second && third ? sum : Integer.MIN_VALUE;
    }
}
