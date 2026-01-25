package career.datastructure.dp.onedp;

public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,7,9,3,1}));
        System.out.println(rob(new int[]{1,2,3,1}));
    }
    public static int rob(int[] nums) {
//        return solve(nums,nums.length-1);
        return solveDp(nums,nums.length-1);
    }

    public static int solve(int []nums,int index){
        if(index < 0) return 0;
        int noTake = solve(nums,index-1);
        int take = solve(nums,index-2)+nums[index];
        return Math.max(noTake,take);
    }

    public static int solveDp(int []nums,int index){
        int prev1=0,prev=0;
        for(int i=0;i<nums.length;i++){
            int robbed = nums[i];
            if(i > 0){
                robbed += prev1;
            }

            int notRobbed = prev;
            int curr = Math.max(robbed,notRobbed);
            prev1 = prev;
            prev = curr;
        }
        return prev;
    }
}
