package career.datastructure.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*

https://algo.monster/liteproblems/2473
You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads, where roads[i] = [ai, bi, costi] indicates that there is a bidirectional road between cities ai and bi with a cost of traveling equal to costi.

You can buy apples in any city you want, but some cities have different costs to buy apples. You are given the array appleCost where appleCost[i] is the cost of buying one apple from city i.

You start at some city, traverse through various roads, and eventually buy exactly one apple from any city. After you buy that apple, you have to return back to the city you started at, but now the cost of all the roads will be multiplied by a given factor k.

Given the integer k, return an array answer of size n where answer[i] is the minimum total cost to buy an apple if you start at city i.

Input: n = 4, roads = {{1,2,4},{2,3,2},{2,4,5},{3,4,1},{1,3,4]], appleCost = [56,42,102,301], k = 2
Output: [54,42,48,51]
Explanation: The minimum cost for each starting city is the following:
- Starting at city 1: You take the path 1 -> 2, buy an apple at city 2, and finally take the path 2 -> 1. The total cost is 4 + 42 + 4 * 2 = 54.
- Starting at city 2: You directly buy an apple at city 2. The total cost is 42.
- Starting at city 3: You take the path 3 -> 2, buy an apple at city 2, and finally take the path 2 -> 3. The total cost is 2 + 42 + 2 * 2 = 48.
- Starting at city 4: You take the path 4 -> 3 -> 2 then you buy at city 2, and finally take the path 2 -> 3 -> 4. The total cost is 1 + 2 + 42 + 1 * 2 + 2 * 2 = 5

EXAMPLE
Let's consider a small example to help illustrate how the solution approach is applied.

Suppose there are n = 3 cities, with the following roads and appleCost:

roads = {{1, 2, 5], [2, 3, 5], [1, 3, 2]]
appleCost = [4, 2, 1]
k = 2
Firstly, we will prepare the graph representation. Cities are numbered starting from 1, hence we subtract 1 from each to convert to zero-based indexing:

The graph will be represented as an adjacency list: { 0: [(1, 5), (2, 2)], 1: [(0, 5), (2, 5)], 2: [(0, 2), (1, 5)] }
Now, we need to calculate the minimum total cost for each city using the Dijkstra's algorithm.

Running Dijkstra's from city 0:

Initially, the distance array is [0, inf, inf] (the cost to reach the starting city from itself is always 0).
The heap will start with only the starting city: [(0, 0)] (cost, city).
The distances to the neighboring cities are updated as we pop from the heap: dist[1] will become 5, and dist[2] will become 2.
Apple costs are then added to the shortest path costs, and the cost for the return is multiplied by k, resulting in total costs: [4, 10*2 + 2, 1*2 + 1] = [4, 22, 3].
Running Dijkstra's from city 1:

Distance array starts as [inf, 0, inf].
Heap starts as [(0, 1)].
The distances will be updated to [5, 0, 5].
Total costs will be: [5*2 + 4, 2, 5*2 + 1] = [14, 2, 11].
Running Dijkstra's from city 2:

Distance array starts as [inf, inf, 0].
Heap starts as [(0, 2)].
Distances are updated to [2, 5, 0].
Total costs: [2*2 + 4, 5*2 + 2, 1] = [8, 12, 1].
Finally, we take the minimum total cost for each starting city which is [4, 2, 1].

The final output array for the minimum total cost starting from each city i is therefore [4, 2, 1].
 */

class City{
    int no;
    int cost;

    public City(int no,int cost){
        this.no = no;
        this.cost = cost;
    }
}
public class MinimumCostToBuyApple {
    public static void main(String[] args) {
//        int n = 4, roads[][] = {{1,2,4},{2,3,2},{2,4,5},{3,4,1},{1,3,4}},
//        appleCost []= {56,42,102,301}, k = 2;
        
        int n = 3, roads[][] = {{1,2,5},{2,3,1},{3,1,2}}, appleCost[] = {2,3,1}, k = 3;

        List<List<City>> cityGraph = createGraph(n,roads);
        long res[]=new long[n];
        int distance[]=new int[n];
        for(int i=0;i<n;i++){
            res[i] = findMinCostPath(i,cityGraph,appleCost,distance,k);
        }

        System.out.println(Arrays.toString(res));
    }

    private static long findMinCostPath(int start,
                                        List<List<City>> cityGraph,int appleCost[],
                                        int distance[],int maxDistance){
        PriorityQueue<City> pq = new PriorityQueue<>((city1,city2) -> city1.cost- city2.cost);

        Arrays.fill(distance,Integer.MAX_VALUE);
        pq.add(new City(start,0));
        distance[start]=0;
        long minCost=Long.MAX_VALUE;

        while(!pq.isEmpty()){
            City city = pq.poll();
            minCost = Math.min(minCost,appleCost[city.no] + (long)(maxDistance+1)*city.cost);
            for(City nbCity:cityGraph.get(city.no)){
                if(distance[nbCity.no] > distance[city.no] + nbCity.cost) {
                    distance[nbCity.no] = distance[city.no] + nbCity.cost;
                    pq.add(new City(nbCity.no,distance[nbCity.no]));
                }
            }
        }

        return minCost;
    }

    private static List<List<City>> createGraph(int n,int roads[][]){
        List<List<City>> cityGraph = new ArrayList<>();
        for(int i=0;i<=n;i++){
            cityGraph.add(new ArrayList<>());
        }

        for(int i=0;i<roads.length;i++){
            cityGraph.get(roads[i][0]-1).add(new City(roads[i][1]-1,roads[i][2]));
            cityGraph.get(roads[i][1]-1).add(new City(roads[i][0]-1,roads[i][2]));
        }

        return cityGraph;
    }
}
