package com.cache.cachelowleveldesign.storage;

import com.cache.cachelowleveldesign.exceptions.StorageFullException;
import com.cache.cachelowleveldesign.exceptions.ValueNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapCacheStorage<K, V> implements CacheStorage<K, V> {
    private final Map<K, V> elements;
    private static HashMapCacheStorage ins = null;
    private int capacity;

    private HashMapCacheStorage(int capacity) {
        this.elements = new HashMap<>();
        this.capacity = capacity;
    }

    public static <K, V> HashMapCacheStorage<K, V> getInstance(int capacity) {
        if (ins == null) {
            synchronized (HashMapCacheStorage.class) {
                if (ins == null)
                    ins = new HashMapCacheStorage(capacity);
            }
        }
        return ins;
    }

    @Override
    public void put(K key, V value) throws StorageFullException {
        if (this.elements.size() < capacity)
            elements.put(key, value);
        else
            throw new StorageFullException();
    }

    @Override
    public V delete(K key) {
        return elements.remove(key);
    }

    @Override
    public V get(K key) throws ValueNotFoundException {
        return Optional.ofNullable(this.elements.get(key)).orElseThrow(() -> new ValueNotFoundException());
    }

    @Override
    public Map<K, V> getMap() {
        return elements;
    }
}
