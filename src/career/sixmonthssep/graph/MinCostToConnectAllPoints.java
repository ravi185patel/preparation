package career.sixmonthssep.graph;

import java.util.*;

/*
https://leetcode.com/problems/min-cost-to-connect-all-points/description/

MST (minimum spanning tree)
1] krushkal (disjoint) -
Time complexity: O(N^2⋅log(N)).

First, we store N⋅(N−1)/2≈N^2 edges of our complete graph in the allEdges array
which will take O(N^2) time, and sorting this array will take O(N^2⋅log(N^2)) time.

Then, we iterate over the allEdges array, and for each element, we perform a union-find operation.
The amortized time complexity for union-find by rank and path compression is O(α(N)),
where α(N) is Inverse Ackermann Function, which is nearly constant, even for large values of N.

Thus, the overall time complexity is O(N^2+N^2⋅log(N^2)+N^2⋅α(N))≈O(N^2⋅log(N^2))≈O(N^2⋅log(N)).

Space complexity: O(N^2).

We use an array allEdges to store all N⋅(N−1)/2≈N
2
  edges of our graph.
UnionFind object uf uses two arrays each of size N to store the group id and rank of all the nodes.
Thus, the overall space complexity is O(N
2
 +N)≈O(N
2
 ).


2] prim  (pq + bfs)

Time complexity: O(N^2⋅log(N)).

In the worst-case, we push/pop N⋅(N−1)/2≈N^2  edges of our graph in the heap.
Each push/pop operation takes O(log(N^2))≈log(N) time.
Thus, the overall time complexity is O(N^2⋅log(N)).
Space complexity: O(N^2).

In the worst-case, we push N⋅(N−1)/2≈N^2  edges into the heap.
We use an array inMST of size N to mark which nodes are included in MST.
Thus, the overall space complexity is O(N^2+N)≈O(N^2).

3] prim optimize (bellmenford)
 */
public class MinCostToConnectAllPoints {
    public static void main(String[] args) {
        int points[][] = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(minCostConnectPoints(points));
    }

    static Map<Integer, List<int[]>> graph;
    public static int minCostConnectPoints(int[][] points) {
//        createGraph(points);
//        return findMinCost();
        return findMinCost(points);
    }

    private static int findMinCost(int[][] points){
        int n = points.length;
        int mstCost = 0;
        int edgesUsed = 0;

        // Track nodes which are visited.
        boolean[] inMST = new boolean[n];

        int[] minDist = new int[n];
        minDist[0] = 0;

        for (int i = 1; i < n; ++i) {
            minDist[i] = Integer.MAX_VALUE;
        }

        while (edgesUsed < n) {
            int currMinEdge = Integer.MAX_VALUE;
            int currNode = -1;

            // Pick least weight node which is not in MST.
            for (int node = 0; node < n; ++node) {
                if (!inMST[node] && currMinEdge > minDist[node]) {
                    currMinEdge = minDist[node];
                    currNode = node;
                }
            }

            mstCost += currMinEdge;
            edgesUsed++;
            inMST[currNode] = true;

            // Update adjacent nodes of current node.
            for (int nextNode = 0; nextNode < n; ++nextNode) {
                int weight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                        Math.abs(points[currNode][1] - points[nextNode][1]);

                if (!inMST[nextNode] && minDist[nextNode] > weight) {
                    minDist[nextNode] = weight;
                }
            }
        }

        return mstCost;
    }

    private static int findMinCost(){
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2)-> p1[1]-p2[1]);
//        System.out.println(graph);
        boolean visited[]=new boolean[graph.size()];
        pq.add(new int[]{0,0});
        int minDist = 0;
        while(!pq.isEmpty()){
            int point[] = pq.poll();
            if(visited[point[0]]) continue;

            minDist += point[1];
            visited[point[0]] = true;
            for(int nbPoint[]:graph.getOrDefault(point[0],new ArrayList<>())){
                if(visited[nbPoint[0]] == false){
                    pq.add(nbPoint);
                }
            }
        }
        return minDist;
    }

    private static void createGraph(int[][]points){
        graph = new HashMap<>();

        int n = points.length;
        for(int i=0;i<n;i++){
            graph.put(i,new ArrayList<>());
        }

        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int distance = Math.abs(points[i][0]-points[j][0]) + Math.abs(points[i][1]-points[j][1]);
                graph.get(i).add(new int[]{j,distance});
                graph.get(j).add(new int[]{i,distance});
            }
        }
    }
}
