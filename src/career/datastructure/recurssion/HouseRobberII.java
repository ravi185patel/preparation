package career.datastructure.recurssion;

public class HouseRobberII {
    public static void main(String[] args) {
        int nums[] =
//                {2,3,2};
//                {1,2,3,1};
//        {1,2,3};
        {1,2,1,1}; // 3
        int resR= robberRight(nums);
        int resL= robberLeft(nums);
        System.out.println(Math.max(resL,resR));

        int res0Ton2= robber(nums,0,nums.length-1);
        int res0Ton1= robber(nums,1,nums.length);
        System.out.println(Math.max(res0Ton1,res0Ton2));
    }

    private static int robber(int nums[],int start,int end){
        int prev2=0,prev=nums[start],cur=0;
        for(int i=start+1;i<end;i++){
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
    private static int robberLeft(int nums[]){
        int prev2=0,prev=nums[0],cur=0;
        for(int i=1;i<nums.length;i++){
            if(nums.length-1 == i){
              break;
            }
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

    private static int robberRight(int nums[]){
        int prev2=0,prev=nums[nums.length-1],cur=0;
        for(int i=nums.length-2;i>=0;i--){
            if(i == 0){
                break;
            }
            int taken = nums[i];
            if(nums.length-1-i > 1){
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
