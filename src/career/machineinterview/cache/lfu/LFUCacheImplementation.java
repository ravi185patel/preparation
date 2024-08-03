package career.machineinterview.cache.lfu;

import career.machineinterview.cache.cacheinterface.CacheI;
import career.machineinterview.cache.pojo.LFUCache;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LFUCacheImplementation implements CacheI {

    private LFUCache lfuCache;
    private final ReentrantReadWriteLock lock;

    public LFUCacheImplementation(){
        this(16);
    }

    public LFUCacheImplementation(int capacity){
        lfuCache = new LFUCache(capacity);
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public int get(int key) {
        this.lock.readLock().lock();

        try {
        }catch (Exception e){
            System.out.println("Exception during get element: "+e.getMessage());
        }finally {
            this.lock.readLock().unlock();
        }
       return -1;
    }

    @Override
    public void put(int key, int value) {
        this.lock.writeLock().lock();
        try{

        }catch (Exception e){
            System.out.println("Exception during put key and value pair in cache : "+e.getMessage());
        }finally {
            this.lock.writeLock().unlock();
        }
    }

}
