package career.datastructure.recurssion.pattern.linerrecursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/jump-game-iii/description/

 */
public class JumpGameIII {
    public static void main(String[] args) {
//        System.out.println(canJump(new int[]{4,2,3,0,3,1,2},5));
        System.out.println(canJump(new int[]{4,2,3,0,3,1,2},0));
        System.out.println(canJump(new int[]{3,0,2,1,2},2));

    }
    public static boolean canJump(int[] nums,int start) {

//        return canJumpRec(nums,start);

        boolean visited[]=new boolean[50001];
//        return canJumpRecMemo(nums,start,visited);

        return canJumpRecDp(nums,start,visited);
//        return canJumpRecNormal(nums,memo);
    }

    public static boolean canJumpRec(int[] nums,int index){
        if(index < 0 || index >= nums.length) return false;
        if(nums[index] ==0 ) return true;
        return canJumpRec(nums,index+ nums[index]) || canJumpRec(nums,index-nums[index]);
    }

    public static boolean canJumpRecMemo(int[] nums,int index,boolean visited[]){
        if(index < 0 || index >= nums.length) return false;
        if(visited[index]) return false;
        if(nums[index] ==0 ) return true;
        visited[index] = true;
        return  canJumpRecMemo(nums,index+ nums[index],visited) || canJumpRecMemo(nums,index-nums[index],visited);// tried both -1 and +1 child
        // why it is not assigned as you can see before assign you need to compute and there may be same index again and again visited
        // ex 1,2,3,4 index -> start form 2 to 4 and 4 to again 2 now it is infinite loop and you never able to set true.
        // so to stop this loop we need to introduce /set true before call same methods.
    }

    public static boolean canJumpRecDp(int[] nums,int start,boolean visited[]){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int point=queue.poll();
            visited[point]=true;
            int nextPoin1 = point - nums[point];
            int nextPoin2 = point + nums[point];

            if(nums[point] == 0) return true;
//            if(nextPoin1 < 0 ) continue;
            if(nextPoin1 >=0 && visited[nextPoin1] == false){
                queue.add(nextPoin1);
            }
            if(nextPoin2 <= nums.length-1 && visited[nextPoin2] == false){
                queue.add(nextPoin2);
            }
        }
        return false;
    }

    public static boolean canJumpRecNormal(int[] nums,boolean memo[]){
        int n = nums.length;
        int maxReachable=0;
        for (int i = 0; i < n; i++) {
            if(maxReachable < i) return false; // 3,2,1,0,4 -> 0 at position 4 => now if you check all previous one (index+value) => 3,3,3,4,9 -> no previous has same value.
            maxReachable = Math.max(maxReachable,nums[i]+i);
        }

        return true;
    }
}
