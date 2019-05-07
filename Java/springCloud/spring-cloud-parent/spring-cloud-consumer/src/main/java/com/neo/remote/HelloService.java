package com.neo.remote;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

/**
 * @author 徐其伟
 * @Description:
 * @date 2018/6/13 9:25
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    //请求熔断注解，当服务出现问题时候会执行fallbackMetho属性的名为helloFallBack的方法
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService() throws ExecutionException, InterruptedException {
        return restTemplate.getForEntity("http://spring-cloud-producer/hello", String.class).getBody();
    }

    public String helloFallBack() {
        return "error1";
    }


}
