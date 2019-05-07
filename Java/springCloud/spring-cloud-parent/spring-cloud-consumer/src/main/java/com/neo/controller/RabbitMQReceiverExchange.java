package com.neo.controller;

import com.neo.pojo.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 徐其伟
 * @Description:
 * @date 2018/6/14 15:09
 */
@Component
@RabbitListener(queues = "topic.message")
public class RabbitMQReceiverExchange {
    @RabbitHandler
    public void process(Order order) {
        System.out.println("topic.message 配置为 topic.message 收到了" + order);
    }
}
