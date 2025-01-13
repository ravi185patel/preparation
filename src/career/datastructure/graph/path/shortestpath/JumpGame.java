package career.datastructure.graph.path.shortestpath;

import java.util.Arrays;

/*
problem link :- https://leetcode.com/problems/jump-game/description/
Clarification Question
1] is there any negative jump ?
2] you can start from 0 and end at nums.length-1 index


Approach
1] DFS :- recursive - TLE -> tc = O(2^n) sc = O(n)
Complexity Analysis

Time complexity : O(2^n)
. There are 2^n  (upper bound) ways of jumping from the first position to the last,
where n is the length of array nums.
For a complete proof, please refer below.
To illustrate the worst case, where this optimization has no effect,
take the example below. Index 6 cannot be reached from any position,
but all combinations will be tried.

Index	0	1	2	3	4	5	6
nums	5	4	3	2	1	0	0
The first few steps of the backtracking algorithm for the example above are:
0 -> 4 -> 5 -> 4 -> 0 -> 3 -> 5 -> 3 -> 4 -> 5 -> etc.

Space complexity : O(n). Recursion requires additional memory for the stack frames.

2] memo:- recursive + memo (topdown) => TLE -> tc = O(n^2) sc = O(n) => it is not accepted.
Time complexity : O(n^2).

For every element in the array, say i,
we are looking at the next nums[i] elements to its right aiming to find a GOOD index.
nums[i] can be at most n, where n is the length of array nums.

Space complexity : O(2n)=O(n).
First n originates from recursion. Second n comes from the usage of the memo table.


3] dp (bottom-up) => tc = O(n^2) sc = O(n) ==> accepted
4] greedy => TC = O(N) ,sc = O(1) ==> accepted.

 */
public class JumpGame {
    public static void main(String[] args) {
//        int nums[] = {2,3,1,1,4};
        int nums[] = {3,2,1,0,4};
//        int nums[] = {1,0,0};
//        int nums[] = {0};
//        int nums[]={2,0,0};

        boolean memo[]=new boolean[10001];
        System.out.println(dfs(0,nums));
        System.out.println(dfsMemo(0,nums,memo));
        System.out.println(dfsDp(nums,memo));
        System.out.println(greedy(nums));
    }

    private static boolean dfs(int index,int nums[]){
        if(index > nums.length){
            return false;
        }
        if(index == nums.length-1){
            return true;
        }


        for(int position = 1;position<=nums[index];position++){
            if(dfs(position+index,nums)){
                return true;
            }
        }

        return false;

    }


    private static boolean dfsMemo(int index,int nums[],boolean memo[]){
        if(index > nums.length){
            return false;
        }
        if(index == nums.length-1){
            return true;
        }

        if(memo[index]){
            return true;
        }


        for(int position = 1;position<=nums[index];position++){
            if(dfsMemo(position+index,nums,memo)){
                return memo[index]=true;
            }
        }

        return memo[index]=false;

    }


    private static boolean dfsDp(int nums[],boolean memo[]){
        Arrays.fill(memo,false);
        memo[0]=true;
        for(int index=1;index<nums.length;index++) {
            for (int prevInd = index-1; prevInd >=0;prevInd--) {
                if(memo[prevInd]  && prevInd + nums[prevInd] >= index) {
                    memo[index] = true;
                    break;
                }
            }
        }
        return memo[nums.length-1];

    }


    private static boolean greedy(int nums[]){
        /*
           compare max with current index
           when current index is more than max which means it is not reachable
           from previous index.
         */
        int maxRes=0;
        for(int index=0;index < nums.length;index++){
            if(index > maxRes) return false;

            maxRes = Math.max(maxRes,index+nums[index]);
        }

        return true;
    }

}
