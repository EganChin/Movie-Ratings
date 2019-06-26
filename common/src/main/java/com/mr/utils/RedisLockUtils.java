package com.mr.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author LiangYongjie
 * @date 2019-04-25
 */
@Component
public class RedisLockUtils {

    private static RedisTemplate<String, Object> template;

    @Autowired
    public RedisLockUtils(RedisTemplate<String, Object> template) {
        RedisLockUtils.template = template;
    }

    /**
     * 尝试获取锁，若获取失败立刻返回
     *
     * @param lock   锁关键字
     * @param expire 锁过期时间
     * @param unit   时间单位
     * @return 获取到锁返回 true，否则返回 false
     */
    public static boolean tryLock(String lock, long expire, TimeUnit unit) {
        Boolean b = template.opsForValue().setIfAbsent(lock, lock, expire, unit);
        return b != null && b;
    }

    /**
     * 尝试获取锁，在一定时间内未获取到锁则返回
     *
     * @param lock    锁关键字
     * @param expire  锁过期时间
     * @param unit    时间单位
     * @param timeout 尝试获取的时间, 单位：ms
     * @return 获取到锁返回 true，否则返回 false
     */
    public static boolean tryLock(String lock, long expire, TimeUnit unit, long timeout) {
        Boolean b;
        long now = System.currentTimeMillis();
        do {
            if (System.currentTimeMillis() - now > timeout) {
                return false;
            }
            b = template.opsForValue().setIfAbsent(lock, lock, expire, unit);
        } while (!(b != null && b));
        return true;
    }

    public static void unlock(String lock) {
        template.delete(lock);
    }

}
