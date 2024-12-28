package career.datastructure.graph.path.shortestpath;

import java.util.Arrays;


//https://leetcode.com/problems/jump-game-ii/description/
public class JumpGameII {
    public static void main(String[] args) {
//        int nums[] = {2,3,1,1,4};
//        int nums[] = {3,2,1,0,4};
//        int nums[] = {1,0,0};
//        int nums[] = {0};
//        int nums[]={2,0,0};
//        int nums[]={2,3,0,1,4};
        int nums[] = {5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5};

        int memo[]=new int[10001];
        Arrays.fill(memo,-1);
//        System.out.println(dfs(0,nums,0));
//        System.out.println(dfs(0,nums));
//        System.out.println(dfsMemo(0,nums,memo));
        System.out.println(dfsDp(nums,memo));
//        System.out.println(greedy(nums));
    }

    private static int dfs(int index,int nums[],int step){
        if(index > nums.length - 1) return Integer.MAX_VALUE;
        if(index == nums.length-1) return step;

        int taken = Integer.MAX_VALUE;
        for(int position=1;position<=nums[index] && position < nums.length ;position++){
            taken = Math.min(taken,dfs(position+index,nums,step+1));
        }
        return taken == Integer.MAX_VALUE ? 0 : taken;
    }

    private static int dfs(int index,int nums[]){
        if(index > nums.length - 1) return Integer.MAX_VALUE;
        if(index == nums.length-1) return 0;

        int taken = Integer.MAX_VALUE;
        for(int position=1;position<=nums[index] && position < nums.length ;position++){
            taken = Math.min(taken,dfs(position+index,nums));
        }
        return taken == Integer.MAX_VALUE ? taken : taken + 1;
    }


    private static int dfsMemo(int index,int nums[],int memo[]){
        if(index > nums.length){
            return Integer.MAX_VALUE;
        }
        if(index == nums.length-1){
            return 0;
        }

        if(memo[index] != -1){
            return memo[index];
        }


        int taken = Integer.MAX_VALUE;
        for(int position=1;position<=nums[index] && position < nums.length ;position++){
            taken = Math.min(taken,dfs(position+index,nums));
        }
        return memo[index] = taken == Integer.MAX_VALUE ? taken : taken + 1;

    }


    private static int dfsDp(int nums[],int memo[]){
        Arrays.fill(memo,Integer.MAX_VALUE);
        memo[nums.length-1]= 0;

        for(int index=nums.length-2;index >=0;index--) {
            int taken = Integer.MAX_VALUE;
            for (int position = 1; position <= nums[index]  && position < nums.length; position++) {
                taken = Math.min(taken, memo[position+index]);
            }
            memo[index]= taken == Integer.MAX_VALUE ? taken : taken + 1;
        }
//        System.out.println(Arrays.toString(memo));
        return memo[0];
    }


    private static int greedy(int nums[]){
        int furtherstPosition=0,currPosition=0;
        int count=0;

        for(int index=0;index<nums.length-1;index++) {
            furtherstPosition = Math.max(nums[index] + index, furtherstPosition);

            if (index == currPosition) {
                count++;
                currPosition = furtherstPosition;
            }
        }
        return count;
    }
}
