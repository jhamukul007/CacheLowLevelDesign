package com.cache.cachelowleveldesign;

import com.cache.cachelowleveldesign.cache.Cache;
import com.cache.cachelowleveldesign.evication.CacheEvictionPolicy;
import com.cache.cachelowleveldesign.evication.LRUCacheEvictionPolicy;
import com.cache.cachelowleveldesign.logging.ConsoleLogger;
import com.cache.cachelowleveldesign.logging.Logger;
import com.cache.cachelowleveldesign.storage.CacheStorage;
import com.cache.cachelowleveldesign.storage.HashMapCacheStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CacheLowlevelDesignApplicationTests {
    private Logger logger;
    private Cache<String, String> cache;
    private CacheEvictionPolicy<String> cacheEvictionPolicy;
    private CacheStorage<String, String> cacheStorage;

    @BeforeAll
    public void init() {
        logger = new ConsoleLogger();
        this.cacheEvictionPolicy = new LRUCacheEvictionPolicy<>();
        this.cacheStorage = HashMapCacheStorage.getInstance(3);
        cache = new Cache<>(cacheStorage, cacheEvictionPolicy, logger);
    }

    @Test
    public void test() {
        cache.put("name", "Mukul");
        cache.put("age", "25");
        cache.put("education", "B.Tech");
        logger.log(cache.get("name"));
        cache.put("lastName", "Jha");
        logger.log(cacheStorage.getMap());
        logger.log(cache.get("education"));
        cache.put("Address", "Bangalore");
    }
}
