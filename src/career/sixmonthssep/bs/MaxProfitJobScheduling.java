package career.sixmonthssep.bs;

import java.util.*;

/*
https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/

 */
class Job{
    int start;
    int end;
    int profit;
    public Job(int start,int end,int profit){
        this.start = start;
        this.end = end;
        this.profit = profit;
    }
}
public class MaxProfitJobScheduling {
    public static void main(String[] args) {
        int []startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
//        int []startTime = {1,2,3,4,6}, endTime = {3,5,10,6,9}, profit = {20,20,100,70,60};
//        int []startTime = {1,1,1}, endTime = {2,3,4}, profit = {5,6,4};

        System.out.println(jobScheduling(startTime,endTime,profit));
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profits) {
        int length = startTime.length;
//        return getMaxProfit(-1,0,startTime,endTime,profits); /// recursion
        int memo[][]=new int[length+1][length];
        for(int row[]:memo) {
            Arrays.fill(row, -1);
        }
//        return getMaxProfit(-1,0,startTime,endTime,profits,memo);
        Job []jobs = new Job[length];
        for(int i=0;i<length;i++){
            jobs[i]=new Job(startTime[i],endTime[i],profits[i]);
        }

        Arrays.sort(jobs,(j1,j2) -> j1.start-j2.start);
        return getMaxProfitBs(0,jobs,memo);

//        List<List<Integer>> lst = initial(startTime,endTime,profits);
//        return jobSchedulingUsingPq(lst,0,lst.size());

    }

    private static int getMaxProfit(int prevIndex, int curr, int[] startTime, int[] endTime, int[] profit) {
        if (curr >= startTime.length) {
            return 0;
        }

        // Option 1: skip current job
        int skip = getMaxProfit(prevIndex, curr + 1, startTime, endTime, profit);

        // Option 2: take current job if it's non-overlapping
        int take = 0;
        if (prevIndex == -1 || startTime[curr] >= endTime[prevIndex]) {
            take = profit[curr] + getMaxProfit(curr, curr + 1, startTime, endTime, profit);
        }

        return Math.max(skip, take);
    }

    private static int getMaxProfit(int prevIndex, int curr, int[] startTime, int[] endTime, int[] profit,int memo[][]) {
        if (curr >= startTime.length) {
            return 0;
        }

        if(memo[prevIndex+1][curr] != -1){
            return memo[prevIndex+1][curr];
        }
        // Option 1: skip current job
        int skip = getMaxProfit(prevIndex, curr + 1, startTime, endTime, profit);

        // Option 2: take current job if it's non-overlapping
        int take = 0;
        if (prevIndex == -1 || startTime[curr] >= endTime[prevIndex]) {
            take = profit[curr] + getMaxProfit(curr, curr + 1, startTime, endTime, profit);
        }

        return memo[prevIndex+1][curr] = Math.max(skip, take);
    }

    private static int maxProfitFrom(int currentEndTime,Job jobs[]){
        int start=0,end = jobs.length-1;
        int nextIndex=jobs.length+1;
        while(start <= end){
            int mid = start + (end-start)/2;
            if(jobs[mid].start >= currentEndTime){
                end = mid-1;
                nextIndex = mid;
            }else{
                start = mid + 1;
            }
        }
        return nextIndex;
    }
    private static int getMaxProfitBs(int index,Job jobs[], int memo[][]) {
        if (index >= jobs.length) {
            return 0;
        }

        if(memo[index][0] != -1){
            return memo[index][0];
        }
        // Option 1: skip current job
        int skip = getMaxProfitBs(index+ 1,jobs,memo);

        int nextIndex = maxProfitFrom(jobs[index].end,jobs);
        // Option 2: take current job if it's non-overlapping
        int take = jobs[index].profit+getMaxProfitBs(nextIndex,jobs,memo);
        return memo[index][0]=Math.max(take,skip);
    }

    public static List<List<Integer>> initial(int[] startTime, int[] endTime, int[] profits) {
        List<List<Integer>> lst = new ArrayList<>();
        int length = startTime.length;
        for(int i=0;i<length;i++){
            lst.add(Arrays.asList(startTime[i],endTime[i],profits[i]));
        }

        Collections.sort(lst,(p1,p2) -> p1.get(0)-p2.get(0));
        return lst;
    }

    public static int jobSchedulingUsingPq(List<List<Integer>> lst) {

        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((p1,p2) -> p1.get(0)-p2.get(0));
        int maxProfit=0;

        for(int i=0;i<lst.size();i++){
            int start = lst.get(i).get(0);
            int end = lst.get(i).get(1);
            int profit = lst.get(i).get(2);

            while(!pq.isEmpty() && pq.peek().get(0) <= start){
                maxProfit =Math.max(maxProfit,pq.peek().get(1));
                pq.poll();
            }

            pq.add(Arrays.asList(end,profit + maxProfit));

        }

        while(!pq.isEmpty()){
            maxProfit =Math.max(maxProfit,pq.peek().get(1));
            pq.poll();
        }

        return maxProfit;
    }
}