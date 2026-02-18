package career.datastructure.dp.dponsequence;

import java.util.Arrays;

public class RussianDollEnvelopes {
    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
        System.out.println(maxEnvelopes(new int[][]{{1,1},{1,1},{1,1},{1,1}}));
    }

    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(e1,e2)->e1[0]-e2[0]);
//        return solveRec(0,new int[]{-1,-1},envelopes);
//        return solveDp(envelopes);
        return solveDpBs(envelopes);
    }


    public static int solveRec(int index,int[] prev,int[][]envelopes){
        if(index >= envelopes.length) return 0;

        int noTake = solveRec(index+1,prev,envelopes);
        int take=Integer.MIN_VALUE;
        if(prev[0] < envelopes[index][0] && prev[1] < envelopes[index][1]){
            take = 1 + solveRec(index+1,envelopes[index],envelopes);
        }
        return Math.max(noTake,take);
    }

    public static int solveDp(int [][]envelopes){
        int n = envelopes.length;
        int dp[]=new int[n+1];
        Arrays.fill(dp,1);
        int max = 0;

        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                int noTake = dp[i];
                int take=Integer.MIN_VALUE;
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    take = 1 + dp[j];//solveRec(index+1,envelopes[index],envelopes);
                }
                dp[i]= Math.max(noTake,take);
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public static int solveDpBs(int [][]envelopes){
        int n = envelopes.length;
        int tail[]=new int[n+1];
        int size=0;
        for(int i=0;i<n;i++){
            int height = envelopes[i][1];
            int left=0,right=size;
            while(left <right){// find upper bound
                int mid = left + (right-left)/2;
                if(height > tail[mid]){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }
            tail[left]=height;
            if(left == size){
                size++;
            }
        }
        return size;
    }

    public static int bs(int [][]envelopes,int current[]){
        int start=0,end=envelopes.length;
        int index=0;
        while(start < end){
            int mid = end + (start-end)/2;
            if(envelopes[mid][0] > current[0] && envelopes[mid][1] > current[1]){
                    index = mid;
                    start = mid+1;
            }else{
                end = mid -1;
            }
        }
        return index;
    }

    public static int solveDp1(int [][]envelopes){
        int n = envelopes.length;
        int dp[]=new int[n+1];
        Arrays.fill(dp,1);

        for(int i=n-1;i>=0;i--){
            for(int j=i+1;j<n;j++){
                int noTake = dp[i];
                int take=Integer.MIN_VALUE;
                if(envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > envelopes[i][1]){
                    take = 1 + dp[j];//solveRec(index+1,envelopes[index],envelopes);
                }
                dp[i]= Math.max(noTake,take);
            }
        }
        return dp[0];
    }
}
