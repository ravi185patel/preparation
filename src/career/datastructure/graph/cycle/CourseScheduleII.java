package career.datastructure.graph.cycle;

import java.util.*;

public class CourseScheduleII {
    /*
     clarification
     0] it is directed graph.
     1] there may be two or more course which are not depend on each other.
     i.e a->b , c->d, e->f
     2] Is there any cycle between course. i.e a->b->a
     3] What output needed. true -> to complete all courses, path/order -> in which it is completed

     Approach
     1] DFS -> start with one course and complete single depend course each time.
     tc = O(N*N) sc= O(N)
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
//        int numCourses = 2, prerequisites[][] = {{1,0},{0,1}};
        int numCourses = 4,prerequisites[][] = {{1, 0},{2, 0},{3, 1},{3, 2}};
//        int  numCourses = 1, prerequisites[][] = {};
//        int numCourses = 2, prerequisites[][] = {{1,0}};
//        int numCourses = 25 , prerequisites[][] = {{10, 18},{0, 18},{10, 6},{16, 0},{8, 7},{19, 15},{24, 16},{20, 14},{1, 17},{14, 13},{21, 21},{19, 22},{23, 20},{10, 5}};
        boolean visited[] = new boolean[numCourses];
        boolean pathVisited[]=new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        createAdjLst(numCourses,prerequisites);

        int path[]=new int[numCourses];
        path=canFinish(numCourses,visited,pathVisited,stack);
        System.out.println(Arrays.toString(path));
        Arrays.fill(path,0);
        path = topoSort(numCourses,visited);
        System.out.println(Arrays.toString(path));

    }

    public static int[] canFinish(int numCourses,boolean visited[],boolean pathVisited[],Stack<Integer> stack) {
        for(int i=0;i<numCourses;i++){
            if(visited[i]==false
                && dfs(i,visited,pathVisited,stack)
//                && bfs(i,visited,pathVisited,stack)
            ){
                return new int[]{-1,-1};
            }
        }
        int index=0;

        int path[]=new int[numCourses];
        while(!stack.isEmpty()){
            path[index++]=stack.pop();
        }
        return noOfcourses == 0 ? path : new int[]{-1,-1};
    }

    public static boolean dfs(int course,boolean visited[],boolean pathVisited[],Stack<Integer> stack){
        visited[course]=true;
        pathVisited[course]=true;
        noOfcourses--;
        for(int nbCourse:courseMap.get(course)){
            if(visited[nbCourse]==false){
                if(dfs(nbCourse,visited,pathVisited,stack)){
                    return true;
                }
            }else if(pathVisited[nbCourse]){
                return true;
            }
        }
        stack.push(course);
        pathVisited[course]=false;
        return false;
    }

    public static boolean bfs(int course,boolean visited[],boolean pathVisited[],Stack<Integer> stack){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(course);
        visited[course]=true;
        pathVisited[course]=true;
        while(!queue.isEmpty()){
            course = queue.poll();
            noOfcourses--;
            stack.push(course);
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


    public static int[] topoSort(int numCourses, boolean visited[]){
        int []path = new int[numCourses];
        noOfcourses = numCourses;
        int inDegree[]=new int[noOfcourses];
        for(int course:courseMap.keySet()){
            for(int nbCourse:courseMap.get(course)){
                inDegree[nbCourse]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<noOfcourses;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        int index=0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            path[index++]=course;
            noOfcourses--;
            for(int nbCourse:courseMap.get(course)){
                inDegree[nbCourse]--;
                if(inDegree[nbCourse]==0){
                    queue.add(nbCourse);
                }
            }
        }
        return noOfcourses ==  0 ? path:new int[]{-1,-1};
    }
    public static void createAdjLst(int numCourses, int[][] prerequisites){
        courseMap = new HashMap<>();
        for(int i=0;i<numCourses;i++){
            courseMap.put(i,new ArrayList<>());
        }

        for(int prereq[]:prerequisites){
//            courseMap.get(prereq[0]).add(prereq[1]);
            courseMap.get(prereq[1]).add(prereq[0]);
        }
        noOfcourses=numCourses;
    }
}
