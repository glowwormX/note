package com.neo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        logger.info("request one  name is "+name);
        return "hello "+name+", by producer";
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap(@RequestParam String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");
        map.put("message","getMap--hello1 "+name);
        return map;
    }
}