package career.machineinterview.cache.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cache{
    public int capacity;
    public int size;
    public ListNode head;
    public ListNode tail;
    public Map<Integer, ListNode> cache;

    public Cache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new ListNode(0,0);
        this.tail = new ListNode(0,0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.cache = new HashMap<>();
    }
}
