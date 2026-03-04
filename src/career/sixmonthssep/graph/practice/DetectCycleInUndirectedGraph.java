package career.sixmonthssep.graph.practice;

import java.util.*;

public class DetectCycleInUndirectedGraph {
    public static void main(String[] args) {
        System.out.println(canFinish(2,new int[][]{{1,0}}));
        System.out.println(canFinish(2,new int[][]{{1,0},{0,1}}));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int inDegree[] = new int[numCourses];
        Map<Integer, List<Integer>> createGraph = createGraph(numCourses, prerequisites);

        for (int parent : createGraph.keySet()) {
            for (int child : createGraph.get(parent)) {
                inDegree[child]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }


        int compeletCourse = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            compeletCourse++;
            for (int childCourse : createGraph.get(course)) {
                inDegree[childCourse]--;
                if (inDegree[childCourse] == 0) {
                    queue.add(childCourse);
                }
            }
        }
        return compeletCourse == numCourses;
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
