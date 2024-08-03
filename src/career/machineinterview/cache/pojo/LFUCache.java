package career.machineinterview.cache.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache extends Cache{
    public Map<Integer,Pair> cache;
    public PriorityQueue<Pair> minHeap;
    public LFUCache(int capacity) {
        super(capacity);
        cache = new HashMap<>();
        minHeap = new PriorityQueue<>((a,b) -> a.frequency - b.frequency);
    }


}
