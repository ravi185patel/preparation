package career.machineinterview.cache;

import career.machineinterview.cache.cacheinterface.CacheI;
import career.machineinterview.cache.factoryImpl.CacheFactory;

public class CacheImplementationDemo {
    public static void main(String[] args) {
        CacheI cacheI = CacheFactory.getCacheTypeImplementation("LRU",16);
        
    }
}
