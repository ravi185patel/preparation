package career.datastructure.graph.cycle;

import java.util.*;

public class CourseSchedule1 {
    /*
     clarification
     0] it is directed graph.
     1] there may be two or more course which are not depend on each other. i.e a->b , c->d, e->f
     2] Is there any cycle between course. i.e a->b->a
     3] What output needed. true -> to complete all courses, path/order -> in which it is completed

     Approach
     1] DFS -> start with one course and complete single depend course each time. tc = O(N*N) sc= O(N)
     2] BFS -> start with one course and complete TC = O(N*N) sc=O(N)
     3] TOPOSORT + bfs/dfs -> calculate inDegree TC = O(N*N) sc=O(N+N)
     4] Union Disjoint -> connect each course and find connected component -> TC = O(N*N) sc=O(N+N+N)

     Approach
     1] dfs
     2] bfs
     3] toposort
     */

    static Map<Integer, List<Integer>> courseMap;
    static int noOfcourses=0;
    public static void main(String[] args) {
        int numCourses = 2, prerequisites[][] = {{1,0},{0,1}};
//        int numCourses = 4,prerequisites[][] = {{1, 0},{2, 0},{3, 1},{3, 2}};
        boolean visited[] = new boolean[numCourses];
        boolean pathVisited[]=new boolean[numCourses];
        createAdjLst(numCourses,prerequisites);
        System.out.println(canFinish(numCourses,visited,pathVisited));
        System.out.println(topoSort(numCourses,visited));
    }

    public static boolean canFinish(int numCourses,boolean visited[],boolean pathVisited[]) {
        for(int i=0;i<numCourses;i++){
            if(visited[i]==false
//                && dfs(i,visited,pathVisited)
                && bfs(i,visited,pathVisited)
            ){
                return false;
            }
        }
        return noOfcourses == 0;
    }

    public static boolean dfs(int course,boolean visited[],boolean pathVisited[]){
        visited[course]=true;
        pathVisited[course]=true;
        noOfcourses--;
        for(int nbCourse:courseMap.get(course)){
            if(visited[nbCourse]==false){
                if(dfs(nbCourse,visited,pathVisited)){
                    return true;
                }
            }else if(pathVisited[nbCourse]){
                return true;
            }
        }
        pathVisited[course]=false;
        return false;
    }

    public static boolean bfs(int course,boolean visited[],boolean pathVisited[]){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(course);
        visited[course]=true;
        pathVisited[course]=true;
        while(!queue.isEmpty()){
            course = queue.poll();
            noOfcourses--;
            for(int nbCourse:courseMap.get(course)){
                if(visited[nbCourse]==false){
                    visited[nbCourse]=true;
                    pathVisited[nbCourse]=true;
                    queue.add(nbCourse);
                }else if(pathVisited[nbCourse]){
                    return true;
                }
            }
        }
        pathVisited[course]=false;
        return false;
    }


    public static boolean topoSort(int numCourses, boolean visited[]){
        noOfcourses = numCourses;
        int inDegree[]=new int[noOfcourses];
        for(int course:courseMap.keySet()){
            for(int nbCourse:courseMap.get(course)){
                inDegree[nbCourse]++;
            }
        }
//        System.out.println(Arrays.toString(inDegree));
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<noOfcourses;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int course = queue.poll();
            noOfcourses--;
            for(int nbCourse:courseMap.get(course)){
                inDegree[nbCourse]--;
                if(inDegree[nbCourse]==0){
                    queue.add(nbCourse);
                }
            }
        }

        System.out.println(noOfcourses);
        return noOfcourses ==  0 ;
    }
    public static void createAdjLst(int numCourses, int[][] prerequisites){
        courseMap = new HashMap<>();
        for(int i=0;i<numCourses;i++){
            courseMap.put(i,new ArrayList<>());
        }

        for(int prereq[]:prerequisites){
            courseMap.get(prereq[1]).add(prereq[0]);
//            courseMap.get(prereq[0]).add(prereq[1]); // vimp ->  it will work but not give correct order
        }
        noOfcourses=numCourses;
    }
}
