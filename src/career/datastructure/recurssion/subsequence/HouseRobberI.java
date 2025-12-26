package career.datastructure.recurssion.subsequence;

public class HouseRobberI {
    public static void main(String[] args) {
        int nums[] =
//                {2,3,2};
//                {1,2,3,1};
//        {1,2,3};
//        {1,2,1,1}; // 3
        {1,2,3,1,3};
        int res= robber(nums);
        res= recursive(nums,nums.length-1);
        System.out.println(res);
    }
    private static int recursive(int nums[],int index){
        // based condition
        // recursinve calls
        // return

        if(index < 0){
            return 0;
        }
        int noTaken = recursive(nums,index-1);
        int taken = nums[index]+recursive(nums,index-2);
        return Math.max(taken,noTaken);
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
