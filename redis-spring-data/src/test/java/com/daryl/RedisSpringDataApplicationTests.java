package com.daryl;

import com.daryl.controller.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisSpringDataApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("location", "广北");
        Object location = redisTemplate.opsForValue().get("location");
        System.out.println("location = " + location);
    }

    @Test
    void testUser() {
//        redisTemplate.opsForValue().set("user:100",new User("Daryl",123));
        User user = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("user = " + user);
    }
}
