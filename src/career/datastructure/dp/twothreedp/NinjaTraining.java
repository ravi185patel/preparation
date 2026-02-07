package career.datastructure.dp.twothreedp;

public class NinjaTraining {
    public static void main(String[] args) {
        System.out.println(" ans "+maxPoints(new int[][]{
                {10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}
        }));
        System.out.println(" ans "+maxPoints(new int[][]{
                {70, 40, 10},
                {180, 20, 5},
                {200, 60, 30}
        }));
    }


    public static int maxPoints(int matrix[][]){
//        int n = matrix.length;
//        int m = matrix[0].length;
//        return solve(n-1,m,m,matrix);
//        return solveOp(matrix);
        return solveDp(matrix); //-- not working as expected
    }


    public static int solve(int n,int m ,int prev,int matrix[][]){
        if(n == 0){
            int max=0;
            for(int i=0;i<m;i++){
                if(prev != i) {
                    max = Math.max(matrix[n][i], max);
                }
            }
            return max;
        }

        int max=0;
        for(int i=0;i<m;i++){
            if(i != prev) {
                int activity =matrix[n][i] + solve(n - 1, m, i, matrix);
                max = Math.max(activity, max);
            }
        }
        return max;
    }

    public static int solveOp(int points[][]) {
        int n = points.length;
        int[][] dp = new int[n][4];


        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
//        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < points[day].length; last++) {
//                dp[day][last] = 0;
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        int activity = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], activity);
                    }
                }
            }
        }

        int max=0;
        for(int i:dp[n-1]){
            max = Math.max(max,i);
        }
        return max;
//        return dp[n - 1][3];
    }
    public static int solveDp(int points[][]) {

        int runningMax = 0;
        int learningMax = 0;
        int fightingMax = 0;

        for (int i = 0; i < points.length; i++) {
            if (i == 0) {
                runningMax = points[i][0];
                learningMax = points[i][1];
                fightingMax = points[i][2];
            } else {
                int currRunningMax = points[i][0] + Math.max(learningMax,fightingMax);
                int currLearningMax = points[i][1] + Math.max(runningMax,fightingMax);
                int currFightingMax = points[i][2] + Math.max(runningMax,learningMax);
//                int currRunningMax = Math.max(points[i][1], points[i][2]) + runningMax;
//                int currLearningMax = Math.max(points[i][0], points[i][2]) + learningMax;
//                int currFightingMax = Math.max(points[i][1], points[i][0]) + fightingMax;

                runningMax = currRunningMax;
                learningMax = currLearningMax;
                fightingMax = currFightingMax;
            }
        }

        return Math.max(runningMax, Math.max(learningMax, fightingMax));
    }
}
