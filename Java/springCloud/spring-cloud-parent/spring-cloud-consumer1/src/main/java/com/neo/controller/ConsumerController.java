package com.neo.controller;

import com.neo.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConsumerController {

    @Autowired
    HelloRemote HelloRemote;
	
    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return HelloRemote.hello(name)+" and by consumer1";
    }

    @RequestMapping("/getMap/{name}")
    public Map<String, Object> getMap(@PathVariable("name") String name) {
        Map<String, Object> map = HelloRemote.getMap(name);
        map.put("consumer","9100");
        return map;
    }

    @Value("${neo.hello.dev}")
    private String hello;

    @Value("${neo.hello.test}")
    private String hello1;

    @RequestMapping("/config")
    public String from() {
        return this.hello;
    }

}