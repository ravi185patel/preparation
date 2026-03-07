package career.sixmonthssep.graph.practice;

import java.util.*;

public class CourseScheduleI {
    public static void main(String[] args) {
        System.out.println(canFinish(2,new int[][]{{1,0}}));
        System.out.println(canFinish(2,new int[][]{{1,0},{0,1}}));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int inDegree[] = new int[numCourses];
        Map<Integer, List<Integer>> graph = createGraph(numCourses, prerequisites);
        return canFinishDfsBfs(numCourses,graph);
    }

    public static boolean canFinishDfsBfs(int numCourses, Map<Integer, List<Integer>> graph) {
        boolean visited[]=new boolean[numCourses+1];
        boolean pathVisited[]=new boolean[numCourses+1]; // very important detect cycle for directed graph as it is given in problem
        for(int course:graph.keySet()) {
            if(visited[course]== false &&
//                    dfs(course,visited,pathVisited,graph)
                    bfs(course,visited,pathVisited,graph)
            ){
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(int course,boolean visited[],boolean pathVisited[],Map<Integer, List<Integer>> graph){
        visited[course]=true;
        pathVisited[course]=true;
        for(int nbCourse:graph.get(course)){
            if(visited[nbCourse]==false){
                if(dfs(nbCourse,visited,pathVisited,graph)){
                    return true;
                }
            }else if(pathVisited[nbCourse]){
                return true;
            }
        }
        pathVisited[course]=false;
        return false;
    }

    public static boolean bfs(int course,boolean visited[],boolean pathVisited[],Map<Integer, List<Integer>> graph){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(course);
        visited[course]=true;
        pathVisited[course]=true;
        while(!queue.isEmpty()) {
            course = queue.poll();
            for (int nbCourse : graph.get(course)) {
                if (visited[nbCourse] == false) {
                    queue.add(nbCourse);
                    visited[nbCourse]=true;
                    pathVisited[nbCourse]=true;
                } else if (pathVisited[nbCourse]) {
                    return true;
                }
            }
        }
        pathVisited[course] = false;
        return false;
    }
    public static boolean canFinishTopoSort(int numCourses, int[][] prerequisites) {
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
