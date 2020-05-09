package com.yanzhen.springcloud.service.impl;

import com.yanzhen.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@EnableBinding(Source.class)  //定义消息的推送管道
public class MessageProvider implements IMessageProvider {

    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String seria = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(seria).build());
        System.out.println("*******seria" + seria);
        return  null;
    }
}
