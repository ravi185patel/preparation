package career.interview.priceline;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3},2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1},1)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,2,1,2,1,2,3,1,3,2},2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
//        return topKFreElementBf(nums,k);
        return topKFreElementMap(nums,k);
    }

    public static int[] topKFreElementBf(int []nums,int k){
        int max = Arrays.stream(nums).max().getAsInt();
        int freq[]=new int[max+1];
        for(int i:nums){
            freq[i]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((p1,p2)->freq[p1]-freq[p2]);

        for(int i=0;i<=max;i++){
            pq.add(i);
            if(pq.size() > k){
                pq.remove();
            }
        }

        int index=0;
        int res[]=new int[k];
        while(!pq.isEmpty()){
            int pqInd = pq.remove();
            res[index]=pqInd;
            index++;
        }
        return res;
    }

    public static int[] topKFreElementMap(int []nums,int k){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        List<Integer>[] buckets = new List[nums.length+1];
        for(int i=0;i<buckets.length;i++){
            buckets[i]=new ArrayList<>();
        }

        for(int key:map.keySet()){
            buckets[map.get(key)].add(key);
        }

        List<Integer> res = new ArrayList<>();
        for(int i=buckets.length-1;i>=0;i--){
            res.addAll(buckets[i]);
            if(res.size()>=k){
                break;
            }
        }
        int resArr[]=new int[k];
        int index=0;
        for(int i:res){
            resArr[index++]=i;
        }
        return resArr;
    }

}
