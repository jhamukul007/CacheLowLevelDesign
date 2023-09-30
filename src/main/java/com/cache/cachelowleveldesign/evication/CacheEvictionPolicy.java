package com.cache.cachelowleveldesign.evication;

import com.cache.cachelowleveldesign.exceptions.EvictionNotAllowedException;

public interface CacheEvictionPolicy<K> {
    K evictKey() throws EvictionNotAllowedException;
    void keyAccessed(K key);
}
