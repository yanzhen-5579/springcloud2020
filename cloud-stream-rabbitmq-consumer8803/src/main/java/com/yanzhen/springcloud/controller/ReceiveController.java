package com.yanzhen.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Sink.class)
public class ReceiveController {
    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void getMessage(Message<String> message){
        System.out.println("2号消费者接收到的消息"+port+"\t"+message.getPayload());
    }
}
