package com.daryl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * author：Daryl
 * date: 2023/1/8
 */
public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);  //最大连接数
        poolConfig.setMaxIdle(8);   // 最大空闲
        poolConfig.setMinIdle(0);   // 最小空闲
        poolConfig.setMaxWaitMillis(1000);  // 空闲多长时间进行回收

        jedisPool = new JedisPool(poolConfig, "192.168.230.130", 6379, 1000, "123456");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
