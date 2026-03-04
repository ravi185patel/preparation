package career.sixmonthssep.graph.practice;

import java.util.*;

public class CourseScheduleII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(canFinish(2,new int[][]{{1,0}})));
        System.out.println(Arrays.toString(canFinish(2,new int[][]{{1,0},{0,1}})));
        System.out.println(Arrays.toString(canFinish(4,new int[][]{{1,0},{2,0},{3,1},{3,2}})));
        System.out.println(Arrays.toString(canFinish(1,new int[][]{})));
    }

    public static int[] canFinish(int numCourses, int[][] prerequisites) {
        int inDegree[] = new int[numCourses];
        Map<Integer, List<Integer>> createGraph = createGraph(numCourses, prerequisites);
        int parents[]=new int[numCourses];
        for (int parent : createGraph.keySet()) {
            for (int child : createGraph.get(parent)) {
                inDegree[child]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(inDegree[i] == 0){
                queue.add(i);
                parents[i]=i;
            }
        }


        int compeletCourse = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            parents[compeletCourse]=course;
            compeletCourse++;
            for (int childCourse : createGraph.get(course)) {
                inDegree[childCourse]--;
                if (inDegree[childCourse] == 0) {
                    queue.add(childCourse);
                }
            }
        }
        return compeletCourse == numCourses ? parents:new int[]{-1};
    }

    private static Map<Integer, List<Integer>> createGraph(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courseGraph = new HashMap<>();
        for (int course = 0; course <= numCourses; course++) {
            courseGraph.put(course, new ArrayList<>());
        }
        for (int prerequisite = 0; prerequisite < prerequisites.length; prerequisite++) {
            courseGraph.get(prerequisites[prerequisite][1]).add(prerequisites[prerequisite][0]);
        }
        return courseGraph;
    }

}
