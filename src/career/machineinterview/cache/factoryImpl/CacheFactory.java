package career.machineinterview.cache.factoryImpl;

import career.machineinterview.cache.cacheinterface.CacheI;
import career.machineinterview.cache.lru.LRUCacheImplementation;

public class CacheFactory {

    public static CacheI getCacheTypeImplementation(String typeImplementation,int capacity){
        CacheI cacheI = null;
        switch (typeImplementation){
            case "LRU": cacheI = new LRUCacheImplementation(capacity);
                        break;
            default : cacheI = new LRUCacheImplementation(capacity);
                     break;
        }
        return cacheI;
    }
}
