package com.smepublish.demo.conf;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 〈ShiroSessionDAO〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
@Component
public class ShiroSessionDAO extends CachingSessionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSessionDAO.class);

    /**
     * session 在redis过期时间是30分钟30*60
     */
    @Value("${spring.redis.expireTime}")
    private static int expireTime;

    private static String prefix = "shiro-session:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doUpdate(Session session) {
        LOGGER.debug("获取session:{}", session.getId());
        String key = prefix + session.getId().toString();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, session);
        }
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    protected void doDelete(Session session) {
        LOGGER.debug("删除session:{}", session.getId());
        redisTemplate.delete(prefix + session.getId().toString());
    }

    @Override
    protected Serializable doCreate(Session session) {
        LOGGER.debug("创建session:{}", session.getId());
        // 这里绑定sessionId到session，必须要有
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(prefix + sessionId.toString(), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        LOGGER.info("获取session:{}", serializable);
        Session session = null;
        // 先从缓存中获取session，如果没有再去数据库中获取
        if (serializable != null) {
            session = (Session) redisTemplate.opsForValue().get(prefix + serializable.toString());
        }
        return session;
    }
}
