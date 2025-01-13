package career.interview;
/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i],
obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays,
return the maximum profit you can take such that there are no two jobs
in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120

Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150

clarfication question
1] profict can be between 1 to n
2] start and end time between 1 to n

O(NlogN)
[1-3(50),2-4(10),3-5(40),3-6(70),4-5(120)]

[1-3(50),2-4(10),3-5(40),4-5(120),3-6(70)]

[(0,0),(3,50),(5,170),

[1-3(50),2-4(10),3-5(90),3-6(120)]



1-3 = 50, 2-4 = 10 =>
4-5 = 40, 3-6 = 70 => 3-6(70) => 120

queue[
1-3(50)
2-4(10) -> insert
]
class Node{
startTime,
endTime,
profit
}

PriorityQueue<Node> pq= new PriorityQueue<>((node1,node2) ->{
  return node1.profit - node2.profit
});


 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Node{
    int start;
    int end;
    int profit;
    public Node(int start,int end,int profit){
        this.start = start;
        this.end = end;
        this.profit = profit;
    }
}
public class Mock1 {

    static List<Node> nodes;
    static int maxProfit =0 ;
    public static void main(String[] args) {
        int []startTime = {1,2,3,4,6}, endTime = {3,5,10,6,9}, profit = {20,20,100,70,60};
//        int []startTime = {1,1,1}, endTime = {2,3,4}, profit = {5,6,4};
        generateData(startTime,endTime,profit);
        Collections.sort(nodes,(node1,node2)-> node1.start-node2.start);
        maxProfit = maxProfitDfs();
        System.out.println(maxProfit);
    }

    public static int maxProfitDfs(){
        PriorityQueue<int[]> pq = new PriorityQueue<>((node1,node2) -> node1[0]-node2[0]);
        int maxProfit=0;
        for(int i=0;i<nodes.size();i++){
            while(!pq.isEmpty() && pq.peek()[0] <= nodes.get(i).start){
                maxProfit = Math.max(maxProfit,pq.poll()[1]);
            }
            pq.add(new int[]{nodes.get(i).end,maxProfit+nodes.get(i).profit});
        }

        while(!pq.isEmpty()){
            int node[] = pq.poll();
            maxProfit = Math.max(maxProfit,node[1]);
        }
        return maxProfit;
    }

    public static void generateData(int start[],int end[],int profit[]){
        nodes = new ArrayList<>();
        for(int i=0;i<start.length;i++){
            nodes.add(new Node(start[i],end[i],profit[i]));
        }
    }
}
