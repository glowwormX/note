package com.neo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 徐其伟
 * @Description:
 * @date 2018/6/14 15:09
 */
@Component
@RabbitListener(queues = "hello")
public class RabbitMQReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("Receiver-producer:" + msg);
    }
}
