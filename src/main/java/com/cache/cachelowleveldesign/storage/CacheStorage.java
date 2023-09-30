package com.cache.cachelowleveldesign.storage;

import com.cache.cachelowleveldesign.exceptions.StorageFullException;
import com.cache.cachelowleveldesign.exceptions.ValueNotFoundException;

import java.util.Map;


public interface CacheStorage<K, V> {
    void put(K key, V value) throws StorageFullException;
    V delete(K key);
    V get(K key) throws ValueNotFoundException;

    Map<K, V> getMap();
}
