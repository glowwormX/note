package com.neo.controller;

import com.neo.pojo.Order;
import com.neo.remote.HelloRemote;
import com.neo.remote.RabbitMQSend;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class ConsumerController {

    @Autowired
    HelloRemote HelloRemote;

    @Autowired
    RabbitMQSend rabbitMQSend;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return HelloRemote.hello(name)+" and by consumer";
    }

    @RequestMapping("/getMap/{name}")
    public Map<String, Object> getMap(@PathVariable("name") String name) {
        Map<String, Object> map = HelloRemote.getMap(name);
        map.put("consum er", "9100");
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

    @RequestMapping("/rabbitmqSend")
    public Map<String, Object> rabbitmq(String name) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            rabbitMQSend.send(name + i);
        }
        map.put("status", "200");
        map.put("message", "已发送");
        return map;
    }

    @RequestMapping("/rabbitmqSendObj")
    public Map<String, Object> rabbitmqSendObj(Order order, String token) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            order.setId(i+"");
            rabbitMQSend.sendObj(order);
        }
        map.put("status", "200");
        map.put("message", "已发送10个order");
        return map;
    }

    @RequestMapping("/rabbitmqSendExchange")
    public Map<String, Object> rabbitmqSendExchange(Order order) {
        Map<String, Object> map = new HashMap<>();
        rabbitMQSend.sendExchange(order);
        map.put("status", "200");
        map.put("message", "已发送order-Exchange");
        return map;
    }

//    @RequestMapping("/timeTest")
//    public void contextLoads() throws ParseException {
//        Date date = calculateDistributionTimeByOrderCreateTime(new Date());
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String timeStr = df.format(new Date());
//        Date timeDate = df.parse(timeStr);
//    }
//    final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15,0,0,0);
//    private Date calculateDistributionTimeByOrderCreateTime(Date orderCreateTime){
//        DateTime orderCreateDateTime = new DateTime(orderCreateTime);
//        Date tomorrow = orderCreateDateTime.plusDays(1).toDate();
//        Date theDayAfterTomorrow = orderCreateDateTime.plusDays(2).toDate();
//        return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(theDayAfterTomorrow) : wrapDistributionTime(tomorrow);
//    }
//    private Date wrapDistributionTime(Date distributionTime){
//        DateTime currentDistributionDateTime = new DateTime(distributionTime);
//        DateTime plusOneDay = currentDistributionDateTime.plusDays(1);
//        boolean isSunday = (DateTimeConstants.SUNDAY == currentDistributionDateTime.getDayOfWeek());
//        return isSunday ? plusOneDay.toDate() : currentDistributionDateTime.toDate() ;
//    }

}