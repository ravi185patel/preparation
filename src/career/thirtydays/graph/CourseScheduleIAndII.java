package career.thirtydays.graph;

import java.util.*;

public class CourseScheduleIAndII {
    public static void main(String[] args) {
//        int numCourses = 2, prerequisites[][] = {{1,0},{0,1}};
//        int numCourses = 2, prerequisites[][] = {{1,0}};
        int numCourses = 4, prerequisites[][] = {{1,0},{2,0},{3,1},{3,2}};
        //[0,2,1,3]
        System.out.println(canFinish(numCourses,prerequisites));
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return topoSortBfs(numCourses,prerequisites);
    }

    private static boolean topoSortBfs(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0;i<numCourses;i++){
            graph.put(i,new ArrayList<>());
        }
        for(int prerequisite[]:prerequisites){
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        int inDegree[] = new int[numCourses];
        int parent[]=new int[numCourses];// course schedule ii;

        for(int i=0;i<numCourses;i++){
            parent[i]=i;
        }
        for(int key:graph.keySet()){
            for(int nbNode:graph.get(key)){
                inDegree[nbNode]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        int index=0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            parent[index++]=course;
            for(int nbCourse:graph.get(course)){
                inDegree[nbCourse]--;
                if(inDegree[nbCourse] == 0){
                    queue.add(nbCourse);
                }
            }
        }
        System.out.println(Arrays.toString(parent));
        return numCourses == 0;
    }
}
