package com.yanzhen.springcloud.controller;

import com.yanzhen.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String res = paymentService.service_ok(id);
        log.info("*******res"+res);
        return res;
    }

    @GetMapping("/payment/error/{id}")
    public String payment_error(@PathVariable("id") Integer id){
        String res = paymentService.service_error(id);
        log.info("*******res"+res);
        return res;
    }
}
