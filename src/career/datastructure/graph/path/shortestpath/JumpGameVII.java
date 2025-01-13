package career.datastructure.graph.path.shortestpath;

import java.util.Arrays;

/*
https://leetcode.com/problems/jump-game-vii/description/

Approach
1] dfs
2] bfs-> most of solve using bfs
3] dp memo
4] dp bottom up
5] dp sliding window
6] greedy
 */
public class JumpGameVII {
    public static void main(String[] args) {
//        String s = "011010";int  minJump = 2, maxJump = 3;
        String s = "01101110";int  minJump = 2, maxJump = 3;

        System.out.println(dfs(0,s.toCharArray(),minJump,maxJump));
        int memo[]=new int[s.length()];
        Arrays.fill(memo,-1);
        System.out.println(dfs(0,s.toCharArray(),minJump,maxJump,memo));
        System.out.println(dfsDp(s.toCharArray(),minJump,maxJump,memo));
    }

    private static boolean dfs(int index,char strChar[],int minJump,int maxJump){
        if(index > strChar.length){
            return false;
        }
        if(index == strChar.length-1){
            return true;
        }


        for(int position = minJump + index;position<=Math.min(index+maxJump,strChar.length-1);position++){
//            System.out.println(position);
            if(strChar[position] == '0') {
//                System.out.println(position);
                if (dfs(position, strChar,minJump,maxJump)) {
                    return true;
                }
            }
        }

        return false;

    }

    private static boolean dfs(int index,char strChar[],int minJump,int maxJump,int memo[]){
        if(index > strChar.length){
            return false;
        }
        if(index == strChar.length-1){
            return true;
        }

        if(memo[index] != -1){
            return memo[index]==1;
        }

        for(int position = minJump + index;position<=Math.min(index+maxJump,strChar.length-1);position++){
//            System.out.println(position);
            if(strChar[position] == '0') {
//                System.out.println(position);
                if (dfs(position, strChar,minJump,maxJump,memo)) {
                    memo[position]=1;
                    return true;
                }
            }
        }

        memo[index]=-1;
        return false;
    }

    private static boolean dfsDp(char strChar[],int minJump,int maxJump,int memo[]){
        Arrays.fill(memo,-1);
        memo[strChar.length-1]= strChar[strChar.length-1]=='0' ? 1 :-1;

        for(int index=strChar.length-2;index >=0;index--) {
            for (int position = minJump + index; position <= Math.min(index + maxJump, strChar.length - 1); position++) {

                if (strChar[index] == '0' && memo[position] == 1) {
                    memo[index]=1;
                    break;
                }
            }
        }

        return memo[0] == 1;
    }


    private static boolean dfsDpSw(char strChar[],int minJump,int maxJump,int memo[]){
        Arrays.fill(memo,-1);
        if(strChar[strChar.length-1] == '1'){
            return false;
        }
        memo[0]= 1;

        int cnt=0;
        for(int index=1;index < strChar.length;index++) {
            if(index >=minJump && memo[index-minJump] == 1){
                cnt++;
            }
            if(index > maxJump && memo[index-maxJump-1] == 1){
                cnt--;
            }

            if(cnt > 0 && strChar[index] == '0'){
                memo[index]=1;
            }
        }

        return memo[strChar.length-1] == 1;
    }
}
