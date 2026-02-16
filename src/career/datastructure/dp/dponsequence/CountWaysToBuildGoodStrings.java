package career.datastructure.dp.dponsequence;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CountWaysToBuildGoodStrings {
    public static void main(String[] args) {
        System.out.println(countGoodStrings(3,3,1,1));
        System.out.println(countGoodStrings(2,3,1,2));
    }
    public static int countGoodStrings(int low, int high, int zero, int one) {
//        return solveRec(0,low,high,zero,one);
        int memo[]=new int[high+1];
        Arrays.fill(memo,-1);
//        return solveRecMemo(0,low,high,zero,one,memo);
        return solveRecDp(0,low,high,zero,one);
    }

    public static int solveRec(int index,int low,int high,int zero,int one){

        if(index > high) return 0;
        int count= (index >= low && high >= index) ? 1 :0;
        count+=solveRec(index+zero,low,high,zero,one);
        count+=solveRec(index+one,low,high,zero,one);
        return count;
    }

    public static int solveRecMemo(int index,int low,int high,int zero,int one,int memo[]){

        if(index > high) return 0;
        if(memo[index] !=-1){
            return memo[index];
        }
        int count= (index >= low && high >= index) ? 1 :0;
        count+=solveRecMemo(index+zero,low,high,zero,one,memo);
        count+=solveRecMemo(index+one,low,high,zero,one,memo);
        return memo[index]=count;
    }

    public static int solveRecDp(int index,int low,int high,int zero,int one){
        int dp[]=new int[high+zero+one+1];

        for(int i=0;i<=high+zero+one;i++){
            int count= (i >= low && high >= i) ? 1 :0;
            count+=i-zero >= 0 ? dp[i-zero]:0;
            count+=i-one >= 0 ? dp[i-one]:0;
            dp[i]=count;
        }
        return dp[high];
    }
}
