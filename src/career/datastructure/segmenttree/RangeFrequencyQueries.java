package career.datastructure.segmenttree;

import java.util.HashMap;
import java.util.Map;

class RangeFreqQuery {

    // TreeMap<Integer, TreeMap<Integer, Integer>> map;
    // public RangeFreqQuery(int[] arr) {
    //     map = new TreeMap<>();
    //     for(int i = 0; i < arr.length; i++){
    //         map.putIfAbsent(arr[i], new TreeMap<>());
    //         map.get(arr[i]).put(i, map.get(arr[i]).size());
    //     }
    // }

    // public int query(int left, int right, int value) {
    //     if(!map.containsKey(value)) return 0;
    //     TreeMap<Integer, Integer> freq = map.get(value);
    //     Integer a = freq.floorKey(right), b = freq.ceilingKey(left);
    //     if(a == null || b == null) return 0;
    //     return freq.get(a) - freq.get(b) + 1;
    // }

    int[] ar;
    Map[] tree;
    public RangeFreqQuery(int[] arr) {
        ar = arr;
        tree = new Map[4 * arr.length];
        buildTree(1, 0, ar.length - 1);
    }
    public void buildTree(int node, int lo, int hi) {
        if(lo == hi) {
            tree[node] = new HashMap();
            tree[node].put(ar[lo], 1);
            return;
        }

        int mid = lo + ((hi - lo) >> 1);
        int leftNode = node << 1;
        int rightNode = 1 + (node << 1);

        buildTree(leftNode, lo, mid);
        buildTree(rightNode, mid + 1, hi);

        tree[node] = merge(leftNode, rightNode);
    }
    private Map<Integer, Integer> merge(int leftNode, int rightNode) {
        Map<Integer, Integer> left = tree[leftNode];
        Map<Integer, Integer> right = tree[rightNode];
        Map<Integer, Integer> map = new HashMap<>();

        for(Map.Entry<Integer, Integer> e : left.entrySet())
            map.put(e.getKey(),
                    map.getOrDefault(e.getKey(), 0) + e.getValue());

        for(Map.Entry<Integer, Integer> e : right.entrySet())
            map.put(e.getKey(),
                    map.getOrDefault(e.getKey(), 0) + e.getValue());

        return map;
    }
    public int query(int node, int lo, int hi, int start, int end, int val) {
        if(lo > end || hi < start)
            return 0;
        if(lo == hi) {
            return tree[node].containsKey(val) ? 1 : 0;
        }
        if(lo >= start && hi <= end) {
            if(tree[node].containsKey(val))
                return (int) tree[node].get(val);
            else
                return 0;
        }

        int mid = lo + ((hi - lo) >> 1);
        int leftNode = node << 1;
        int rightNode = 1 + (node << 1);

        int la = query(leftNode, lo, mid, start, end, val);
        int ra = query(rightNode, mid + 1, hi, start, end, val);
        return la + ra;
    }
    public int query(int left, int right, int value) {
        return query(1, 0, ar.length - 1, left, right, value);
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */

public class RangeFrequencyQueries {
    public static void main(String[] args) {
//        ["RangeFreqQuery", "query", "query"] -> output [null,1,2];
//[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
        RangeFreqQuery rangeFreqQuery=new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        int res=rangeFreqQuery.query(1,2,4);
        System.out.println(res);
        res=rangeFreqQuery.query(0, 11, 33);
        System.out.println(res);


    }
}
