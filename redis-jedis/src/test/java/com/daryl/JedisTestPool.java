package com.daryl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * author：Daryl
 * date: 2023/1/8
 */
public class JedisTestPool {
    private Jedis jedis;

    /**
     * 从jedis连接池中取资源
     */
    @Before
    public void setUpJedis() {
        jedis = JedisConnectionFactory.getJedis();
        jedis.select(0);
    }

    /**
     * 关闭连接（底层做了判断，如果是连接池则直接归还）
     */
    @After
    public void shutDown() {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void testString() {
        // 存入数据
        String result = jedis.set("name", "达里尔2");
        System.out.println("result = " + result);
        // 取出数据
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    public void testHash() {
        // 插入hash数据
        jedis.hset("user:1","name","daryl1");
        jedis.hset("user:1","age","60");

        Map<String, String> hashResult = jedis.hgetAll("user:1");
        System.out.println("hashResult = " + hashResult);
    }
}
