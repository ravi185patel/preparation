package career.machineinterview.cache.pojo;

import java.util.HashMap;
import java.util.Map;

public class LRUCache extends Cache{
    public ListNode head;
    public ListNode tail;
    public Map<Integer, ListNode> cache;

    public LRUCache(int capacity) {
        super(capacity);
        this.head = new ListNode(0,0);
        this.tail = new ListNode(0,0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.cache = new HashMap<>();
    }
}
