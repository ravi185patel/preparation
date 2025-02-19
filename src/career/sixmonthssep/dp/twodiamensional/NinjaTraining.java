package career.sixmonthssep.dp.twodiamensional;

import java.util.Arrays;

/*
https://www.naukri.com/code360/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos
https://www.geeksforgeeks.org/problems/geeks-training/1

Ninja is planing this ‘N’ days-long training schedule. E
ach day, he can perform any one of these three activities.
(Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day.
As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days.
 Can you help Ninja find out the maximum merit points Ninja can earn?

You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is to calculate the maximum number of merit points that Ninja can earn.

For Example
If the given ‘POINTS’ array is
[[1,2,5],
 [3,1,1] ,
 [3,3,3] ],

the answer will be 11 as 5 + 3 + 3.

 */
public class NinjaTraining {
    public static void main(String[] args) {
        int arr[][]=
//                {{1, 2, 5},{3, 1, 1},{3, 3, 3}};
        {{10,40,70},{20,50,80},{30,60,90}};
        System.out.println(ninjaTrainingDp(arr));
        System.out.println(ninjaTrainingDpOp(arr));
    }

    private static int ninjaTrainingDp(int points[][]) {

        int prev[]=new int[3];
        int m = points.length;
        int n = points[0].length;
        for(int i=0;i<3;i++){
            prev[i] = points[0][i];
        }
        System.out.println(Arrays.toString(prev));
        for(int i=1;i<m;i++){
            int curr[] = new int[3];
            for(int j=0;j<n;j++){
                int max=0;
                for(int k=0;k<3;k++){
                    if(j==k) continue;
                    max = Math.max(max,prev[k]);
                }
                curr[j] = max + points[i][j];
            }
            System.out.println(Arrays.toString(curr));
            prev = curr.clone();
        }
        int max =0;
        for(int val:prev){
            max = Math.max(max,val);
        }

        return max;
    }

    private static int ninjaTrainingDpOp(int points[][]) {

        int runningMax = 0;
        int learningMax = 0;
        int fightingMax = 0;

        for(int i=0;i<points.length;i++){
            if(i==0){
                runningMax = points[i][0];
                learningMax = points[i][1];
                fightingMax = points[i][2];
            }else{
                int currRunningMax  = Math.max(points[i][1],points[i][2]) + runningMax;
                int currLearningMax = Math.max(points[i][0],points[i][2]) + learningMax;
                int currFightingMax = Math.max(points[i][1],points[i][0]) + fightingMax;

                runningMax = currRunningMax;
                learningMax = currLearningMax;
                fightingMax = currFightingMax;
            }
        }

        return Math.max(runningMax,Math.max(learningMax,fightingMax));
    }
}