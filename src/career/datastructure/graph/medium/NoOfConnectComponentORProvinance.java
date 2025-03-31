package career.datastructure.graph.medium;

import java.util.*;

public class NoOfConnectComponentORProvinance {
    public static void main(String args[] ) throws Exception {

//        Scanner s = new Scanner(System.in);
//        int m = s.nextInt(),n=s.nextInt();
//        int edges[][]= inputs(m,n,s);
//        List<List<Integer>> graph = createGraph(m,n,edges);
//        int ans = findNoOfConnectedComp(n,m,graph);
//        System.out.println(ans);

          int m= 5,n=4;
          int edges[][]=new int[5][2];
          edges[0][0]=1;edges[0][1]=0;
          edges[1][0]=2;edges[1][1]=1;
          edges[2][0]=3;edges[2][1]=4;
//          edges[3][0]=1;edges[3][1]=3;

          List<List<Integer>> adjList = createGraph(m,n,edges);

          boolean visited[]=new boolean[m];
          int noOfComponent=0;
          for(int i=0;i<m;i++){
              if(visited[i] == false){
                  noOfComponent++;
//                  findNoOfConnectedComp(i,adjList,visited);
                  findNoOfConnectedCompAd(i,adjList,visited);
              }
          }
          System.out.println(noOfComponent);
    }


    private static int[][] inputs(int m,int n,Scanner s){
        int edges[][]=new int[m][2];
        for(int i=0;i<m;i++){
            edges[i][0] = s.nextInt();
            edges[i][1] = s.nextInt();
        }
        return edges;
    }

    private static List<List<Integer>> createGraph(int m,int n,int[][]edges){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=1;i<=m;i++){ // node start from 1 to M to be very carefully to pick
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<n;i++){
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }

        return adjList;
    }
    private static void findNoOfConnectedComp(int start,
                                             List<List<Integer>> adjList,
                                             boolean visited[]){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(visited[node]){
                continue;
            }

            visited[node]=true;
            for(int nb:adjList.get(node)){
                if(visited[nb] == false){
                    queue.add(nb);
                }
            }
        }
    }

    private static void findNoOfConnectedCompAd(int start,
                                              List<List<Integer>> adjList,
                                              boolean visited[]){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start]= true;
        while(!queue.isEmpty()){
            int node= queue.poll();
            for(int nbNode:adjList.get(node)){
                if(visited[nbNode]== false){
                    visited[nbNode]= true;
                    queue.add(nbNode);
                }
            }
        }

    }
}
