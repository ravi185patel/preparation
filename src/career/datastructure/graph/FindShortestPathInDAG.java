package career.datastructure.graph;

import java.util.*;
    class Pair{
        int node,wg;
        int px,dist;
        public Pair(int node,int wg){
            this.px=px;
            this.dist=dist;

            this.node = node;
            this.wg = wg;
        }
    }

public class FindShortestPathInDAG {
    public static void main(String[] args) {
        int n = 6, m = 7;
        int[][] edge = {{0,1,2},{0,4,1},{4,5,4},{4,2,2},{1,2,3},{2,3,6},{5,3,1}};
        FindShortestPathInDAG obj = new FindShortestPathInDAG();
        int[] distance = obj.shortestPath(n,m,edge);
        System.out.println(Arrays.toString(distance));

    }

    public ArrayList<ArrayList<Pair>> makeAdjList(int [][] edges, int n,int m){
        ArrayList<ArrayList<Pair>> adjList=new ArrayList<>();

        for(int i=0;i<n;i++){
            adjList.add(new ArrayList<>());
        }

        for(int j=0;j<m;j++){
            adjList.get(edges[j][0]).add(new Pair(edges[j][1],edges[j][2]));
        }
        return adjList;

    }

    public int[] shortestPath(ArrayList<ArrayList<Pair>> adjList,int src,int n,int m){

        int dist[]=new int[n];

        for(int i=0;i<n;i++){
            dist[i]=(int)(1e9);
        }

        Queue<Integer> queue=new LinkedList<>();
        queue.add(src);
        dist[src] = 0;

        while(!queue.isEmpty()){
            int cur=queue.remove();
            for(Pair pair:adjList.get(cur)){
                if(dist[cur] + pair.dist < dist[pair.px]){
                    dist[pair.px]=dist[cur]+ pair.dist;
                    queue.add(pair.px);
                }
            }
        }

        for(int i=0;i<n;i++){
            if(dist[i]==((int) 1e9)){
                dist[i]=-1;
            }
        }

        return dist;
    }
    public int[] shortestPath(int n,int m, int[][] edges) {
        List<List<Pair>> adj = getAdjList(n,m,edges);

        int distance[] = bfs(n,m,adj);

        return distance;

    }

    private int[] bfs(int n,int m,List<List<Pair>> adj){
        int distance[]=new int[n];
        int inDegree[]=new int[n];

        Arrays.fill(distance, (int)(1e9));
        for(int i=0;i<n;i++){
            for(Pair pair:adj.get(i)){
                inDegree[pair.node]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<n;i++){
            if(inDegree[i] == 0){
                distance[i] =0;
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int node = queue.remove();
            for(Pair nb:adj.get(node)){
                int dest = nb.node;
                int dwg = nb.wg;
                if(distance[node] + dwg < distance[dest]){
                    distance[dest] = distance[node] + dwg;
                }
                inDegree[dest]--;
                if(inDegree[dest] == 0){
                    queue.add(dest);
                }
            }
        }

        for(int i=0;i<n;i++){
            if(distance[i] == (int)(1e9)){
                distance[i] = -1;
            }
        }

        return distance;
    }

    private int[] bfs1(int n,int m,List<List<Pair>> adj){
        int visited[]=new int[n];
        int distance[]=new int[n];

        Arrays.fill(distance, (int)(1e9));

        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<n;i++){
            if(visited[i] == 0){
                topoSort(i,adj,visited,stack);
            }
        }

        distance[0] = 0;
        while(!stack.isEmpty()){
            int node = stack.pop();
            for(Pair nb:adj.get(node)){
                int dest = nb.node;
                int dwg = nb.wg;
                if(distance[node] + dwg < distance[dest]){
                    distance[dest] = distance[node] + dwg;
                }
            }
        }

        for(int i=0;i<n;i++){
            if(distance[i] == (int)(1e9)){
                distance[i] = -1;
            }
        }

        return distance;
    }


    private void topoSort(int node,List<List<Pair>> adj,int visited[],Stack<Integer> stack){
        visited[node] = 1;
        for(int i=0;i<adj.get(node).size();i++){
            int j= adj.get(node).get(i).node;
            if(visited[j] == 0){
                topoSort(j,adj,visited,stack);
            }
        }

        stack.push(node);
    }



    private List<List<Pair>> getAdjList(int n,int m,int[][]edges){
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
        }

        return adj;
    }
}
