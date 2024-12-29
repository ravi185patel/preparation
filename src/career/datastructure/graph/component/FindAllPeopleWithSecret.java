package career.datastructure.graph.component;

import java.util.*;

/*
https://leetcode.com/problems/find-all-people-with-secret/description/?envType=problem-list-v2&envId=depth-first-search

It is graph problem
Approach
Approach-1:
Description: The most basic approach uses a map to store meetings at each time,
iterates through meetings in increasing order of time,
and performs BFS to spread the secret.

Time Complexity (T.C): ~O(M*(M+N)), where M = number of meetings,
N = number of people.
Space Complexity (S.C): O(M+N).

Approach-2:
Description: Uses BFS graph traversal,
maintains a queue with persons and their respective times,
and updates the earliest known secret time for each person.
Time Complexity (T.C): O(M * (M+N)), where M = number of meetings,
N = number of people.
Space Complexity (S.C): O(M+N).

Approach-3:
Description: Utilizes DFS graph traversal to update the earliest known secret time
for each person. Revisits some nodes with better secret knowing time.
Time Complexity (T.C): O(M * (M+N)), where M = number of meetings,
N = number of people.
Space Complexity (S.C): O(M+N).

Approach-4:
Description: Uses a min-heap to fetch the earliest time,
updates the earliest known secret time for each person,
and maintains a visited set to avoid revisiting.

Time Complexity (T.C): ~O((N+M) * (log(M+N) + M)).
Space Complexity (S.C): O(N+M).

 */
public class FindAllPeopleWithSecret {
    public static void main(String[] args) {
//        int  n = 4, meetings[][] = {{3,1,3}, {1,2,2}, {0,3,3}}, firstPerson = 3;
        int n = 6, meetings[][] = {{1,2,5}, {2,3,8}, {1,5,10}}, firstPerson = 1;
//        int n = 5, meetings [][]= {{3,4,2}, {1,2,1}, {2,3,1}}, firstPerson = 1;
        System.out.println(findAllPeople(n,meetings,firstPerson));
    }

    static Map<Integer,List<int[]>> graph;
    static boolean visited[];

    static int[] earliest;


    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        createGraph(n,meetings);
        visited = new boolean[n];
        Arrays.fill(visited,false);
        earliest = new int[n];
        Arrays.fill(earliest, Integer.MAX_VALUE);
        earliest[0] = 0;
        earliest[firstPerson] = 0;
        return bfs(firstPerson);
    }

    private static List<Integer> bfs(int firstPerson){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        q.add(new int[]{firstPerson,0});

        while(!q.isEmpty()){
            int[] personTime = q.poll();
            int person = personTime[0],time = personTime[1];
            for(int [] nextPersonTime:graph.get(person)){

                int t = nextPersonTime[1],nextPerson = nextPersonTime[0];

                if( t >= time && earliest[nextPerson] > t){
                    earliest[nextPerson]=t;
                    q.add(new int[]{nextPerson,t});
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i=0;i<earliest.length;i++){
            if(earliest[i] != Integer.MAX_VALUE){
                res.add(i);
            }
        }

        return res;
    }

    private static void createGraph(int n,int meetings[][]){
        graph = new HashMap<>();
        for(int i=0;i<n;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int meeting[]:meetings){
            graph.get(meeting[0]).add(new int[]{meeting[1],meeting[2]});
            graph.get(meeting[1]).add(new int[]{meeting[0],meeting[2]});
        }
    }

    // private static void dfs(int n,int index,List<Integer> res,int wt){
    //     if(index == graph.size()){
    //         return;
    //     }

    //     visited[index]=true;
    //     if(!res.contains(index)) res.add(index);
    //     for(int nbNode[]:graph.get(index)){
    //         int node = nbNode[0];
    //         int wg = nbNode[1];
    //         if(visited[node] == false && wt <= wg){
    //             dfs(n,node,res,Math.max(wt,wg));
    //         }
    //     }
    // }
}
