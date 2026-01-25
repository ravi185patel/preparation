package career.datastructure.dp.onedp;

public class HouseRobberII {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,3,2}));//3
        System.out.println(rob(new int[]{1,2,3,1}));//4
    }
    public static int rob(int[] nums) {
        return Math.max(solve(nums,0,nums.length-2),solve(nums,1,nums.length-1));
//        return Math.max(solveDp(nums,0,nums.length-2),solveDp(nums,1,nums.length-1));
    }

    public static int solve(int []nums,int start,int end){
        if(end < start) return 0;
        int noTake = solve(nums,start,end-1);
        int take = solve(nums,start,end-2)+nums[end];
        return Math.max(noTake,take);
    }

    public static int solveDp(int []nums,int start,int end){
        int prev1=0,prev=0;
        for(int i=start;i<=end;i++){
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
