package career.datastructure.graph.path.shortestpath;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/jump-game-iii/description/?envType=problem-list-v2&envId=depth-first-search
public class JumpGameIII {
    public static void main(String[] args) {
//        int nums[] = {4,2,3,0,3,1,2},start = 5;
        int nums[] = {4,2,3,0,3,1,2},start = 0;
//        int nums[] = {3,0,2,1,2},start = 2;

//        int memo[]=new int[10001];
//        Arrays.fill(memo,-1);
//        System.out.println(dfs(start,nums));
        System.out.println(bfs(start,nums));
//        System.out.println(dfsMemo(0,nums,memo));
//        System.out.println(dfsDp(nums,memo));
//        System.out.println(greedy(nums));
    }

    private static boolean dfs(int position,int nums[]){

        if(position >= nums.length || position < 0) return false;
        if(nums[position] < 0) return false;
        if(nums[position] == 0) return true;

        nums[position] = (-1) * nums[position];

        boolean forward =  dfs(position + nums[position],nums);
        boolean backward =  dfs(position - nums[position],nums);

        return forward || backward;
    }

    private static boolean bfs(int position,int nums[]){
        Queue<Integer> queue =new LinkedList<>();

        queue.add(position);
        while(!queue.isEmpty()){
            position = queue.poll();
            if(nums[position] == 0) return true;
            if(nums[position] < 0 ) continue;;
//            if(nums[position] > 0 ) {
            if (position + nums[position] < nums.length) {
                queue.offer(position + nums[position]);
            }
            if (position - nums[position] >= 0) {
                queue.offer(position - nums[position]);
            }
            nums[position] = -nums[position];

//            }
        }
        return false;
    }

}
