package com.smepublish.demo.conf;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;

/**
 * 〈Redis配置 〉
 *
 * @author mazhongrong@smeyun.com
 * 指明配置类、开启缓存
 * @date 2019/1/24
 */
@Configuration
@EnableCaching
public class MyRedisConfig{
    @Bean
    public RedisTemplate<String, String>  redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new  RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config =  RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(1));
        RedisCacheManager cacheManager =  RedisCacheManager.builder(factory).cacheDefaults(config).build();
        return cacheManager;
    }
}
