package com.smepublish.demo.conf;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 〈ShiroRedisCache〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
@Component
@SuppressWarnings("unchecked")
public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRedisCache.class);
    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    private String cacheKey = "";
    private final static String PREFIX = "shiro-cache:";
    @Value("${spring.redis.expireTime}")
    private long expireTime;

    @Override
    public V get(K k) throws CacheException {
        LOGGER.debug("Shiro从缓存中获取数据KEY值[" + k + "]");
        redisTemplate.boundValueOps(getCacheKey(k)).expire(expireTime, TimeUnit.MINUTES);
        return redisTemplate.boundValueOps(getCacheKey(k)).get();
    }

    @Override
    public V put(K k, V v) throws CacheException {
        V old = get(k);
        redisTemplate.boundValueOps(getCacheKey(k)).set(v);
        return old;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = redisTemplate.opsForValue().get(k);
        redisTemplate.opsForValue().getOperations().delete(k);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }
}
