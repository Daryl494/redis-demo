package com.daryl;

import com.daryl.controller.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * author：Daryl
 * date: 2023/1/10
 */
@SpringBootTest
public class StringRedisTemplateTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "张三李四王五");
        String name = stringRedisTemplate.opsForValue().get("name");    // 默认返回string，无需转换
        System.out.println("name = " + name);
    }

    @Test
    void testObject() throws JsonProcessingException {
        User user = new User("超人强", 18);
        // 手动序列化并写入，也可以用其他序列化方式
        String userJson = objectMapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:200", userJson);

        // 读取数据，反序列化
        String userJsonValue = stringRedisTemplate.opsForValue().get("user:200");
        User user1 = objectMapper.readValue(userJsonValue, User.class);
        System.out.println(user1);
    }

    @Test
    void testHash() {
        // 存hash value
//        stringRedisTemplate.opsForHash().put("user:300", "name", "猪猪侠");
//        stringRedisTemplate.opsForHash().put("user:300", "age", "20");

        // 取值
//        Map<Object, Object> entry = stringRedisTemplate.opsForHash().entries("user:300");
//        System.out.println("entry = " + entry);
//        String name = (String) stringRedisTemplate.opsForHash().get("user:300", "name");
//        String age = (String) stringRedisTemplate.opsForHash().get("user:300", "age");
//        System.out.println("name = " + name);
//        System.out.println("age = " + age);

        // 直接存map
//        Map<String, Object> userMap = new HashMap<>();
//        userMap.put("name","菲菲公主");
//        userMap.put("age","15");
//        stringRedisTemplate.opsForHash().putAll("user:400",userMap);
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println(map);

    }

    @Test
    void testInsert() {
        String prefix = "test:";
        for (int i = 0; i < 100000; i++) {
            Random random = new Random();
            int num = random.nextInt();
            stringRedisTemplate.opsForValue().set(prefix + i, String.valueOf(num));

        }
        System.out.println("插入10w条数据成功");
    }

    @Test
    void testGet() {
        long begin = System.currentTimeMillis();
        Boolean insertFlag = stringRedisTemplate.opsForValue().setIfAbsent("test:998", "123456");
        System.out.println("insertFlag = " + insertFlag);
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - begin) + " ms");
    }

    @Test
    void getAllList() {
        Set<String> keys = stringRedisTemplate.keys("test:*");
        System.out.println(keys.size());
    }
}
