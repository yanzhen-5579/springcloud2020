package com.yanzhen.springcloud.controller;

import com.yanzhen.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider iMessageProvider;

    @GetMapping("/sendmessage")
    public String sendMessage(){
        System.out.println(iMessageProvider.getClass());
        return iMessageProvider.send();
    }
}
