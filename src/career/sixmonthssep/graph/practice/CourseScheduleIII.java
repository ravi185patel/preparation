package career.sixmonthssep.graph.practice;

import java.util.*;

public class CourseScheduleIII {
    public static void main(String[] args) {
        System.out.println(scheduleCourse(new int[][]{
                {100,200},{200,1300},{1000,1250},{2000,3200}
        }));
    }

    public static int scheduleCourse(int[][] courses) {
//        return scheduleCoursePq(courses);
        return scheduleCourseRec(0,courses,0);
    }
    public static int scheduleCoursePq(int[][] courses) {
        Arrays.sort(courses,(c1,c2) -> c1[1]-c2[1]);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int totalDuration =0;
        for(int course[]:courses){
            totalDuration += course[0];
            maxHeap.add(course[0]);

            if(totalDuration > course[1]){
                totalDuration-=maxHeap.poll();
            }
        }

        return maxHeap.size();
    }

    public static int scheduleCourseRec(int index,int[][] courses,int totalTime) {
       if(index == courses.length) return 0;

       int noTake = scheduleCourseRec(index+1,courses,totalTime);
       int take = Integer.MIN_VALUE;
       if(courses[index][0] + totalTime < courses[index][1]){
           take = 1 + scheduleCourseRec(index+1,courses,totalTime+courses[index][0]);
       }
       return Math.max(noTake,take);
    }

    public static int scheduleCourseDp(int index,int[][] courses,int totalTime) {
        int m = courses.length;
        // sort by deadline
        Arrays.sort(courses, (a,b) -> a[1] - b[1]);

        int maxDeadline = 0;
        for (int[] c : courses) {
            maxDeadline = Math.max(maxDeadline, c[1]);
        }

        int []dp = new int[maxDeadline + 1];
        for(int course[]:courses){
            int duration = dp[0];
            int lastDay = dp[1];
            for(int t = lastDay;t>=duration;t--){
                dp[t] = Math.max(dp[t],dp[t-duration]+1);
            }
        }

        int ans =0;
        for(int val:dp){
            ans = Math.max(val,ans);
        }
        return ans;
    }

}
