package career.datastructure.binarysearch;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
https://leetcode.com/problems/most-profit-assigning-work/
You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:

difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
Return the maximum profit we can achieve after assigning the workers to the jobs.



Example 1:

Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100
Explanation: Workers are assigned jobs of difficulty [4,4,6,6]
and they get a profit of [20,20,30,30] separately.
Example 2:

Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
Output: 0


Constraints:

n == difficulty.length
n == profit.length
m == worker.length
1 <= n, m <= 104
1 <= difficulty[i], profit[i], worker[i] <= 105
 */
public class MostProfitAssigningWork {
    public static void main(String[] args) {
//        int [] difficulty = {2,4,6,8,10}, profit = {10,20,30,40,50}, worker = {4,5,6,7};
        int [] difficulty = {85,47,57}, profit = {24,66,99}, worker = {40,25,25};
        System.out.println(findMaxProfitAssPQ(difficulty,profit,worker));
    }
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        return -1;
    }

    public static int findMaxProfitAssPQ(int[] difficulty, int[] profit, int[] worker) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((i,j) -> {
            int d = j[1]-i[1];
            return d;
//            int p = j[0]-i[0];
//            return d == 0 ? p : d;
        });
        int len = difficulty.length;
        for(int i=0;i<len;i++){
            pq.add(new int[]{profit[i],difficulty[i]});
        }
        Arrays.sort(worker);
        int total =0;
        for(int w=worker.length-1;w>=0;w--){
            while(!pq.isEmpty() && pq.peek()[1] > worker[w]){
                pq.poll();
            }
            if(!pq.isEmpty() && pq.peek()[1] <= worker[w]){
                total += pq.peek()[0];
            }
        }
        return total;
    }

    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(0,0);
        for(int i=0;i<difficulty.length;i++){
            map.put(difficulty[i],Math.max(profit[i],
                    map.getOrDefault(difficulty[i],0)));
        }
        int best=0,res=0;
        for(int key:map.keySet()){
            best = Math.max(map.get(key),best);
            map.put(key,best);
        }

        for(int i=0;i<worker.length;i++){
            res += map.floorEntry(worker[i]).getValue();
        }

        return res;
    }
}
