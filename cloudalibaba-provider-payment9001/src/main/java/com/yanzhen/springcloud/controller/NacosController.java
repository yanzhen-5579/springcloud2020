package com.yanzhen.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosController {

    @Value("${server.port}")
    String port;

    @GetMapping("/payment/nacos/get/{id}")
    public String getPort(@PathVariable("id") Integer id){
        return "nacos  \t"+port+id;
    }
}
