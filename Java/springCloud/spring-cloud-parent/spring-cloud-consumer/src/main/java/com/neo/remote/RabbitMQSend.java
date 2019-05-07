package com.neo.remote;

import com.neo.pojo.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 徐其伟
 * @Description: Rabbit 消息发送
 * @date 2018/6/14 15:02
 */
@Service
public class RabbitMQSend {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String name) {
        String msg = "hello " + name + ":" + new Date();
//        System.out.println("Sender:"+msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
    }

    /**
     * 发送对象
     *
     * @param order
     */
    public void sendObj(Order order) {
        this.rabbitTemplate.convertAndSend("order", order);
    }

    public void sendExchange(Order order) {
        order.setId("topic.message");
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", order);
        System.out.println("给topic.message发送一个order" + order);
        order.setId("topic.xxx");
        this.rabbitTemplate.convertAndSend("exchange", "topic.xxx", order);
        System.out.println("给topic.xxx发送一个order" + order);
    }
}
