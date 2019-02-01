package com.smepublish.demo.conf;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈ShiroRedisCacheManager 〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
@Component
public class ShiroRedisCacheManager implements CacheManager {
    @Autowired
    private Cache shiroRedisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return shiroRedisCache;
    }
}
