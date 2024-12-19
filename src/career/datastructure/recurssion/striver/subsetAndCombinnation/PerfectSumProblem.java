package career.datastructure.recurssion.striver.subsetAndCombinnation;

public class PerfectSumProblem {
    public static void main(String[] args) {
        System.out.println(perfectSum(new int[]{5, 2, 3, 10, 6, 8},10));
        System.out.println(perfectSum(new int[]{2, 5, 1, 4, 3},10));
        System.out.println(perfectSum(new int[]{5, 7, 8},3));
        System.out.println(perfectSum(new int[]{35, 2, 8, 22},0));
        System.out.println(perfectSum(new int[]{0, 10,0},0));

    }
    public static int perfectSum(int[] nums, int target) {
        // code here
//        Arrays.sort(nums);
        return helper(0,target,nums);
    }

    private static int helper(int index,int target,int[]nums){
        if(index == nums.length && target == 0){
            return 1;
        }
        if(index == nums.length){
            return 0;
        }

        int noTaken = helper(index+1,target,nums);
        int taken = 0;
        if(target >= nums[index]){
            taken = helper(index+1,target-nums[index],nums);
        }

        return noTaken + taken;
    }
}
