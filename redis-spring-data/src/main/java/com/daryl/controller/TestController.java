package com.daryl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author：Daryl
 * date: 2023/1/10
 */
@RestController
public class TestController {
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/test")
    public String test() {
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        return name;
    }

}
