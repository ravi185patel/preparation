package career.datastructure.recurssion.subsequence;

public class HouseRobberI {
    public static void main(String[] args) {
        int nums[] =
//                {2,3,2};
//                {1,2,3,1};
//        {1,2,3};
        {1,2,1,1}; // 3
        int res= robber(nums);
        System.out.println(res);
    }

    private static int robber(int nums[]){
        int prev2=0,prev=nums[0],cur=0;
        for(int i=1;i<nums.length;i++){
            int taken = nums[i];
            if(i > 1){
                taken += prev2;
            }
            int noTaken = prev;
            cur= Math.max(noTaken,taken);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}
