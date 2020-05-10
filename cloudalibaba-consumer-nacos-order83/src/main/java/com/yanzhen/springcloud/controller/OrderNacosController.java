package com.yanzhen.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderNacosController {

    @Value("${service-url.nacos-user-service}")
    String url;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/nacos/{id}")
    public String getInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(url+"/payment/nacos/get/"+id,String.class);
    }

}
