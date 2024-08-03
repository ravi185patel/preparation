package career.machineinterview.cache.lru;


import career.machineinterview.cache.cacheinterface.CacheI;
import career.machineinterview.cache.pojo.LRUCache;
import career.machineinterview.cache.pojo.ListNode;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCacheImplementation implements CacheI {

    private LRUCache lruCache;
    private final ReentrantReadWriteLock lock;

    public LRUCacheImplementation(){
        this(16);
    }

    public LRUCacheImplementation(int capacity){
        lruCache = new LRUCache(capacity);
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public int get(int key) {
        this.lock.readLock().lock();
        ListNode node = null;
        try {
            if (!lruCache.cache.containsKey(key)) {
                return -1;
            }
            node = lruCache.cache.get(key);
            moveToHead(node);
        }catch (Exception e){
            System.out.println("Exception during get element: "+e.getMessage());
        }finally {
            this.lock.readLock().unlock();
        }
        return node == null ? -1:node.value;
    }

    @Override
    public void put(int key, int value) {
        this.lock.writeLock().lock();
        try{
            if(lruCache.cache.containsKey(key)){
                ListNode node = lruCache.cache.get(key);
                node.value = value;
                moveToHead(node);
            }else{
                ListNode newNode = new ListNode(key,value);
                lruCache.cache.put(key,newNode);
                addToHead(newNode);
                lruCache.size++;
                if(lruCache.size > lruCache.capacity){
                    ListNode tailNode = removeTail();
                    lruCache.cache.remove(tailNode.key);
                    lruCache.size--;
                }
            }
        }catch (Exception e){
            System.out.println("Exception during put key and value pair in cache : "+e.getMessage());
        }finally {
            this.lock.writeLock().unlock();
        }
    }

    private void moveToHead(ListNode node){
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(ListNode node){
        node.next = lruCache.head.next;
        node.prev = lruCache.head;
        lruCache.head.next.prev = node;
        lruCache.head.next = node;
    }

    private void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private ListNode removeTail(){
        ListNode tailNode = lruCache.tail.prev;
        removeNode(tailNode);
        return tailNode;
    }
}
