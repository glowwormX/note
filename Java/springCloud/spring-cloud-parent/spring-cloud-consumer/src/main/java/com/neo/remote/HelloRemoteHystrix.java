package com.neo.remote;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徐其伟
 * @Description:
 * @date 2018/6/12 12:34
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(String name) {
        return "error1";
    }

    @Override
    public Map<String, Object> getMap(String name) {
        Map<String, Object> map =new HashMap<>();
        map.put("status","500");
        return map;
    }
}
