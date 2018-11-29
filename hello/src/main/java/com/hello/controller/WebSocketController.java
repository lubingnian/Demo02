package com.hello.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.hello.pojo.AricMessage;
import com.hello.pojo.AricResponse;

/**
 * webSocket控制器
 */
@Controller
public class WebSocketController {

	 //通过simpMessagingTemplate向浏览器发送消息
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    //在springmvc中,可以直接在参数中获得principal,pinciple中包含当前用户信息
    public void handleChat(Principal principal,String msg){
        if ("james".equals(principal.getName())) {//硬编码,对用户姓名进行判断
            //向用户发送消息,第一个参数:接收消息的用户,第二个参数:浏览器订阅地址,第三个参数:消息
            simpMessagingTemplate.convertAndSendToUser("curry", 
                    "/queue/notifications", principal.getName() + "-send: " + msg);
        } else {
            simpMessagingTemplate.convertAndSendToUser("james", 
                    "/queue/notifications", principal.getName() + "-send: " + msg);
        }
    }

    @MessageMapping("/welcome") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/getResponse")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public AricResponse say(AricMessage message) {
        try {
            //睡眠3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AricResponse("welcome," + message.getName() + "!");
    }
}