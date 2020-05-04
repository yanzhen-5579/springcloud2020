package com.yanzhen.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String URL = "http://cloud-provider-payment";


    @GetMapping("/consul/get")
    public String getPort(){
        return restTemplate.getForObject(URL+"/payment/consul",String.class);
    }
}
