package career.datastructure.dp;

import java.util.Arrays;
import java.util.Stack;

public class MaximumProfitInJobScheduling {
    public static void main(String[] args) {
//        int [] startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
        System.out.println(jobScheduling(new int[]{1,2,3,3},
                new int[]{3,4,5,6},
                new int[]{50,10,40,70}));
        System.out.println(jobScheduling(new int[]{1,2,3,4,6},new int[]{3,5,10,6,9}
                ,new int[]{20,20,100,70,60}));
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int temp[][]=new int[n][3];
        for(int i=0;i<n;i++){
            temp[i][0]=startTime[i];
            temp[i][1]=endTime[i];
            temp[i][2]=profit[i];
        }

        Arrays.sort(temp,(i,j)-> i[0]-j[0]);
        // return solveRec(0,temp);
        int memo[]=new int[n+1];
        Arrays.fill(memo,-1);
        // return solveMemo(0,temp,memo);
        return solveDp(0,temp,memo);

    }

    public static int solveRec(int index,int temp[][]) {
        if(index >= temp.length ) return 0;

        int noTake = solveRec(index+1,temp);
        int nextIndex = index + 1;

        while (nextIndex < temp.length &&
                temp[nextIndex][0] < temp[index][1]) {
            nextIndex++;
        }
        int take = temp[index][2]+solveRec(nextIndex,temp);
        return Math.max(noTake,take);
    }


    public static int solveMemo(int index,int temp[][],int memo[]) {
        if(index >= temp.length ) return 0;

        if(memo[index] !=-1){
            return memo[index];
        }
        int noTake = solveMemo(index+1,temp,memo);
        int nextIndex = index + 1;

        while (nextIndex < temp.length &&
                temp[nextIndex][0] < temp[index][1]) {
            nextIndex++;
        }
        int take = temp[index][2]+solveMemo(nextIndex,temp,memo);
        return memo[index] = Math.max(noTake,take);
    }

    public static int solveDp(int index,int temp[][],int memo[]) {
        int n = temp.length;
        Arrays.sort(temp, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n];
        dp[0] = temp[0][2];

        for (int i = 1; i < n; i++) {

            int take = temp[i][2];
            int prevIndex = i - 1;

            while (prevIndex >= 0 && temp[prevIndex][1] > temp[i][0]) {
                prevIndex--;
            }

            if (prevIndex >= 0) {
                take += dp[prevIndex];
            }

            int noTake = dp[i - 1];
            dp[i] = Math.max(take, noTake);
        }

        return dp[n - 1];
    }


    public static int solveDpBs(int index,int temp[][],int memo[]) {
        int n = temp.length;
        Arrays.sort(temp, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n];
        dp[0] = temp[0][2];

        for (int i = 1; i < n; i++) {
            int take = temp[i][2];
            int prevIndex = findLastNonConflicting(temp, i);

            if (prevIndex != -1)
                take += dp[prevIndex];

            dp[i] = Math.max(dp[i - 1], take);
        }

        return dp[n - 1];
    }

    private static int findLastNonConflicting(int[][] temp, int index) {
        int low = 0, high = index - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (temp[mid][1] <= temp[index][0]) {
                if (mid + 1 <= high && temp[mid + 1][1] <= temp[index][0])
                    low = mid + 1;
                else
                    return mid;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
