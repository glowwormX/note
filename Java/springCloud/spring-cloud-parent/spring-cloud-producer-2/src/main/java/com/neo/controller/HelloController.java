package com.neo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/hello")
    public String index(@RequestParam String name){
        logger.info("request two name is "+name);
//        try{
//            Thread.sleep(1000000);
//        }catch ( Exception e){
//            logger.error(" hello two error",e);
//        }
        String uuid = UUID.randomUUID().toString();
        logger.info("jedisPool uuid : " + uuid);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(uuid, 1000, name);
        }
        return "hello "+name+",by producer2";
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap(@RequestParam String name) {
        int a = 1/0;//熔断测试
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");
        map.put("message","getMap--hello2 "+name);
        return map;
    }
}