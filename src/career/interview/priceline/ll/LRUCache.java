package career.interview.priceline.ll;

import career.common.UrlNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<String, UrlNode> map = new HashMap<>();
    int capacity=0;
    UrlNode head,tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public String get(String key) {
        if (!map.containsKey(key)) {
            return "no URL found";
        }

        UrlNode node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(String key, String value) {

        if (map.containsKey(key)) {
            UrlNode node = map.get(key);
            node.value = value;  // update value
            moveToHead(node);
        } else {

            UrlNode newNode = new UrlNode(key, value);

            if (map.size() == capacity) {
                map.remove(tail.key);
                removeNode(tail);
            }

            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    private void moveToHead(UrlNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(UrlNode node) {

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private void addToHead(UrlNode node) {

        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }

        head = node;

        if (tail == null) {
            tail = node;
        }
    }
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put("ravi", "patel");
        cache.put("rahul", "sharma");

        System.out.println(cache.get("ravi")); // patel

        cache.put("amit", "singh"); // removes rahul (LRU)

        System.out.println(cache.get("rahul")); // no URL found;
    }


}
