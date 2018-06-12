package org.sang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sang on 16-12-22.
 */
@Controller
@EnableScheduling
public class WsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new ResponseMessage("welcome," + message.getName() + " !",df.format(new Date()));
    }

    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/callback")
    public Object callback() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
        return "callback";
    }
}
