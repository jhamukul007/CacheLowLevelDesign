package com.cache.cachelowleveldesign.cache;

import com.cache.cachelowleveldesign.evication.CacheEvictionPolicy;
import com.cache.cachelowleveldesign.exceptions.StorageFullException;
import com.cache.cachelowleveldesign.exceptions.ValueNotFoundException;
import com.cache.cachelowleveldesign.logging.Logger;
import com.cache.cachelowleveldesign.storage.CacheStorage;

public class Cache<K, V> {
    private final CacheStorage<K, V> cacheStorage;
    private final CacheEvictionPolicy cacheEvictionPolicy;

    private final Logger logger;

    public Cache(CacheStorage<K, V> cacheStorage, CacheEvictionPolicy cacheEvictionPolicy, Logger logger) {
        this.cacheStorage = cacheStorage;
        this.cacheEvictionPolicy = cacheEvictionPolicy;
        this.logger = logger;
    }

    public void put(K key, V val) {
        try {
            this.cacheStorage.put(key, val);
            cacheEvictionPolicy.keyAccessed(key);
        } catch (StorageFullException e) {
            logger.log("Cache is full, evict one key first and insert new values");
            K evictedKey = (K) cacheEvictionPolicy.evictKey();
            logger.log("Evicted key #" + evictedKey + "# from the cache");
            this.cacheStorage.delete(evictedKey);
            put(key, val);
            return;
        }
        logger.log("Added key value pair into cache [" + key + "," + val + "]");
    }

    public V get(K key) {
        try {
            V value = this.cacheStorage.get(key);
            cacheEvictionPolicy.keyAccessed(key);
            return value;
        } catch (ValueNotFoundException e) {
            throw e;
        }
    }

}
